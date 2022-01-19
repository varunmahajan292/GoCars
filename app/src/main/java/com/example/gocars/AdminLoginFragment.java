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
public class AdminLoginFragment extends Fragment {
    EditText pass, email;
    Button bt_login_login;
    Db_userRegd db;
    public AdminLoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_admin_login, container, false);
        email= view.findViewById(R.id.email);
        pass=view. findViewById(R.id.pass);
        bt_login_login= view.findViewById(R.id.bt_login_login);
        db= new Db_userRegd(faltu_context.context);

        db.deladmdata();
        db.ADMdata();
     bt_login_login.setOnClickListener(new View.OnClickListener() {
    @Override
      public void onClick(View v) {
        if ((email.getText().toString()).equals("")||(pass.getText().toString()).equals("")) {

            Toast.makeText(faltu_context.context, "Please enter all the fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Cursor res =  db.checkLogin2(email.getText().toString(),pass.getText().toString());

        if(res.moveToFirst())
        {
            //Toast.makeText(faltu_context.context,"Login ",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getActivity(),AdminTasks.class);
            // intent.putExtra("name",res.getString(1));
            startActivity(intent);
        }
        else {
            Toast.makeText(getActivity(),"Please Admin try again login failed",Toast.LENGTH_SHORT).show();
        }
             }
          });

        return view;
    }
}
