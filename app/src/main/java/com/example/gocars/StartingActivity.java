package com.example.gocars;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class StartingActivity extends AppCompatActivity {
    public static FragmentManager fm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        faltu_context.context=getApplicationContext();
        fm =  getSupportFragmentManager();
        if (findViewById(R.id.frag_cont_page)!=null)
        {
            if(savedInstanceState != null)
            { return;}

            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            UserLoginFragment lg = new UserLoginFragment();
          //  Upload_Car_Frag2 lg= new Upload_Car_Frag2();
            fragmentTransaction.add(R.id.frag_cont_page, lg, null);
            fragmentTransaction.commit();
        }

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menuuser, menu);

        return true;

    }

    public boolean onOptionsItemSelected(MenuItem item) {
//respond to menu item selection
        switch (item.getItemId()) {
            case R.id.admlog:
                StartingActivity.fm.beginTransaction().replace(R.id.frag_cont_page,new AdminLoginFragment(), null).commit();
                // startActivity(new Intent(this,AdminTasks.class));
                return true;
            case R.id.Userlog:
                StartingActivity.fm.beginTransaction().replace(R.id.frag_cont_page,new UserLoginFragment(), null).commit();
                // startActivity(new Intent(this,AdminTasks.class));
                return true;
            case R.id.Logout:
                finish();
                System.exit(0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
