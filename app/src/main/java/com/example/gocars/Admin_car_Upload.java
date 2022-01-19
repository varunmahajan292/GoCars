package com.example.gocars;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class Admin_car_Upload extends Fragment {
     EditText addName,meter,plate,description,cost;
     TextView imgname;
     Spinner  spinner_economy, spinner_modelyear,spinner_model,spinner_doors,spinner_seater,spinner_mode,spinner_cool;
     ImageView addImage;
     Button car_submit;
    private Uri filePath;
    private static final int PICK_IMAGE_REQUEST = 71;
    private ByteArrayOutputStream byteArrayOutputStream;
    private byte[] imageinbtye;
    String encodedImage;
    //String encodedImage;

    private Bitmap imagetostore;
    Db_userRegd db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_admin_car__upload, container, false);
        addName=view.findViewById(R.id.addName);
        meter=view.findViewById(R.id.meter);
        imgname=view.findViewById(R.id.imgname);
        plate=view.findViewById(R.id.plate);
        description=view.findViewById(R.id.description);
        cost=view.findViewById(R.id.cost);
        spinner_economy=view.findViewById(R.id.spinner_economy);
        spinner_modelyear=view.findViewById(R.id.spinner_modelyear);
        spinner_model=view.findViewById(R.id.spinner_model);
        spinner_doors=view.findViewById(R.id.spinner_doors);
        spinner_seater=view.findViewById(R.id.spinner_seater);
        spinner_mode=view.findViewById(R.id.spinner_mode);
        spinner_cool=view.findViewById(R.id.spinner_cool);

        car_submit=view.findViewById(R.id.car_submit);
         addImage=view.findViewById(R.id.addImage);
        db = new Db_userRegd(faltu_context.context);
         //image code
addImage.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
});

         // button to submit details in database
         car_submit.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                 String strDate = sdf.format(new Date());

                 boolean check  = db.insertUploadData(addName.getText().toString(), encodedImage,spinner_economy.getSelectedItem().toString(),spinner_modelyear.getSelectedItem().toString(),spinner_model.getSelectedItem().toString(),spinner_doors.getSelectedItem().toString(),spinner_seater.getSelectedItem().toString(), spinner_mode.getSelectedItem().toString(),spinner_cool.getSelectedItem().toString(),meter.getText().toString(),plate.getText().toString(), description.getText().toString(),cost.getText().toString(),"admin",strDate,"0");

                 if(check==true) {
                     Toast.makeText(faltu_context.context, "You are Registered Now you can login ", Toast.LENGTH_SHORT).show();
                   //  fname.setText("");
                     //lname.setText("");
                     //email.setText("");
                     //mobile.setText("");
                     //pass.setText("");
                     StartingActivity.fm.beginTransaction().replace(R.id.frag_cont_page,new UserLoginFragment(), null).commit();
                 }
                 else{
                     Toast.makeText(faltu_context.context, "Please Try again Registration failed", Toast.LENGTH_SHORT).show();

                 }
          }
         });
        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                imagetostore = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), filePath);
                addImage.setImageBitmap(imagetostore);
                addImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
                Bitmap bitmap=imagetostore;
                byteArrayOutputStream =new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG,71,byteArrayOutputStream);
                imageinbtye =byteArrayOutputStream.toByteArray();
                encodedImage= Base64.encodeToString(imageinbtye, Base64.DEFAULT);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}





