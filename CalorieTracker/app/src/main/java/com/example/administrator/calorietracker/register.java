package com.example.administrator.calorietracker;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Navya on 4/7/2017.
 */

public class register extends Activity {

    Databasehelper helper = new Databasehelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg);
    }

    public void onregisterclick(View v)
    {
     if(v.getId()==R.id.register){
         EditText name = (EditText)findViewById(R.id.name);
         EditText pass = (EditText)findViewById(R.id.pass);
         EditText cpass = (EditText)findViewById(R.id.cpass);

         String user = name.getText().toString();
         String pass1 = pass.getText().toString();
         String pass2 = cpass.getText().toString();

         if(!(pass1.equals(pass2)))
         {
             Toast msg = Toast.makeText(register.this,"Passwords dont match",Toast.LENGTH_SHORT);
             msg.show();
         }
        else
            {
            //insert values
                User u = new User();
                u.setname(user);
                u.setPass(pass1);
                helper.insertUser(u);
         }
     }
    }

}
