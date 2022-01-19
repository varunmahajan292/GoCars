package com.example.gocars;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserRegFragment extends Fragment {
    EditText fname,lname,email,pass,mobile;
    Button login,signup;
    Db_userRegd db;

    public UserRegFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.fragment_user_reg, container, false);
        // Inflate the layout for this fragment
        fname=view.findViewById(R.id.fname);
        lname=(EditText)view.findViewById(R.id.lname);
        email=(EditText)view.findViewById(R.id.email);
        pass=(EditText)view.findViewById(R.id.pass);
        mobile=(EditText)view.findViewById(R.id.mobile);
        login=(Button) view.findViewById(R.id.login);
        signup=(Button)view.findViewById(R.id.signup);
        db = new Db_userRegd(faltu_context.context);
         login.setOnClickListener(new View.OnClickListener() {
          @Override
         public void onClick(View v) {
         StartingActivity.fm.beginTransaction().replace(R.id.frag_cont_page,new UserLoginFragment(), null).commit();
                }
        });
         signup.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if ((fname.getText().toString()).equals("")||(lname.getText().toString()).equals("")||(email.getText().toString()).equals("")||(mobile.getText().toString()).equals("")||(pass.getText().toString()).equals("")) {

                     Toast.makeText(faltu_context.context, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                     return;
                 }
                 //calling already exist email function
                 Cursor res =  db.alreadyExist(email.getText().toString());

                 if(res.moveToFirst())
                 {

                     Toast.makeText(faltu_context.context,"Email : "+res.getString(0).toString()+" already Exists.",Toast.LENGTH_SHORT).show();
                     return;
                 }
                 else {

                     Toast.makeText(faltu_context.context,"Please enter valid email",Toast.LENGTH_SHORT).show();
                 }




                 //Db_userRegd db = new Db_userRegd();
                boolean check  = db.insertData(fname.getText().toString(),lname.getText().toString(),email.getText().toString(), mobile.getText().toString(),pass.getText().toString(),"0");

                 if(check==true) {
                     Toast.makeText(faltu_context.context, "You are Registered Now you can login ", Toast.LENGTH_SHORT).show();
                     fname.setText("");
                     lname.setText("");
                     email.setText("");
                     mobile.setText("");
                     pass.setText("");
                     StartingActivity.fm.beginTransaction().replace(R.id.frag_cont_page,new UserLoginFragment(), null).commit();
                 }
                 else{
                     Toast.makeText(faltu_context.context, "Please Try again Registration failed", Toast.LENGTH_SHORT).show();

                 }
                     }

         });

        return view;
    }

}
