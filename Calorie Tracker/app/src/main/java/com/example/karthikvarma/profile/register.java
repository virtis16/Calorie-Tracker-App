package com.example.karthikvarma.profile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class register extends AppCompatActivity implements View.OnClickListener {
    DBHandler helper = new DBHandler(this);

    Button bregister;
    EditText etUsername, etPassword, CONFIRM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        CONFIRM = (EditText) findViewById(R.id.cpassword);
        bregister = (Button) findViewById(R.id.bRegister);
        bregister.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.bRegister:
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                String confirm = CONFIRM.getText().toString();
                if(!(password.equals(confirm)))
                {
                    Toast msg = Toast.makeText(this,"Passwords do not match",Toast.LENGTH_SHORT);
                    msg.show();
                    break;
                }else {
                    //INSERT DETAILS IN DATABASE
                    startActivity(new Intent(this,login.class));
                    helper.insertUser(username, password);
                    break;
                }
        }
    }
}
