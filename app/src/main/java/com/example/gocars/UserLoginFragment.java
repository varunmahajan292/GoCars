package com.example.gocars;

import android.app.Activity;
import android.content.Context;
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
public class UserLoginFragment extends Fragment {
Context context;
Db_userRegd db;
public EditText email,pass;
    public Button bt_login_login, bt_login_signup,bt_forgotpass;


    public UserLoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_user_login, container, false);
        bt_login_login = view.findViewById(R.id.bt_login_login);
        bt_login_signup = view.findViewById(R.id.bt_login_signup);
        bt_forgotpass = view.findViewById(R.id.bt_forgotpass);
        email = view.findViewById(R.id.email);
        pass = view.findViewById(R.id.pass);
        db= new Db_userRegd(faltu_context.context );

        //  bt_login_login.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //  public void onClick(View v) {
        //    MainActivity.fm.beginTransaction().replace(R.id.frag_cont_page,new login_frag(), null).commit();
        //}
        //});
        bt_login_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartingActivity.fm.beginTransaction().replace(R.id.frag_cont_page,new UserRegFragment(), null).commit();
            }
        });
        bt_login_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((email.getText().toString()).equals("")||(pass.getText().toString()).equals("")) {

                    Toast.makeText(faltu_context.context, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                // get track of userlogin id of userregd table
                Cursor res2 =  db.userLoginID(email.getText().toString());
                if(res2.moveToFirst())
                {
                  // Toast.makeText(faltu_context.context,"Email : "+res2.getString(0).toString()+" already Exists.",Toast.LENGTH_SHORT).show();
                    ((global_vars) getActivity().getApplication()).setLoginUserID(Integer.parseInt(res2.getString(0)));
                  // int a= ((global_vars) getActivity().getApplication()).getLoginUserID();
                    //Toast.makeText(faltu_context.context,"Email : "+res2.getString(0).toString()+" already Exists."+a,Toast.LENGTH_SHORT).show();
                }
                else {

                    Toast.makeText(faltu_context.context,"Error While getting user id",Toast.LENGTH_SHORT).show();
                }
                             //end tracking
                Cursor res =  db.checkLogin(email.getText().toString(),pass.getText().toString());

                if(res.moveToFirst())
                {
                    Toast.makeText(faltu_context.context,"Work ",Toast.LENGTH_SHORT).show();
                    Intent myIntent = new Intent(getActivity(),NavigationActivity.class);
                    startActivity(myIntent);

                }
                else {
                    Toast.makeText(faltu_context.context,"Please try again login failed",Toast.LENGTH_SHORT).show();
                }


                // showMes("Data",buff.toString());
            }
        });
        bt_forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartingActivity.fm.beginTransaction().replace(R.id.frag_cont_page,new ForgotPasswordFragment(), null).commit();
            }
        });
        return view;




    }




}
