package com.example.administrator.calorietracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Databasehelper helper = new Databasehelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onbuttonclick(View view){

        if(view.getId()== R.id.Register)
        {
            Intent i = new Intent(MainActivity.this,register.class);
            startActivity(i);

        }

        if(view.getId()==R.id.login)
        {
            EditText et = (EditText)findViewById(R.id.username);
            String struser = et.getText().toString();
            EditText et1 = (EditText)findViewById(R.id.password);
            String pass1  = et1.getText().toString();
             String pwd = helper.searchPass(struser);

            if(pass1.equals(pwd)){
                Intent i = new Intent(MainActivity.this,customer.class);
                startActivity(i);


            }
            else {
                Toast msg = Toast.makeText(MainActivity.this,"Wrong Credentials",Toast.LENGTH_SHORT);
                msg.show();
            }
        }

    }
}
