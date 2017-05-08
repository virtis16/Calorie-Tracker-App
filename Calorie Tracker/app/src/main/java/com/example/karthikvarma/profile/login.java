package com.example.karthikvarma.profile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class login extends AppCompatActivity implements View.OnClickListener{
    DBHandler helper = new DBHandler(this);
    private static final String ADMIN = "admin";

    Button bLogin;
    EditText etUsername,etPassword;
    TextView registerLink;
    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bLogin = (Button) findViewById(R.id.blogin);
        registerLink = (TextView)  findViewById(R.id.dregister);
        bLogin.setOnClickListener(this);
        registerLink.setOnClickListener(this);
        userLocalStore = new UserLocalStore(this);


    }
    //save login info
    public void saveInfo()
    {
        SharedPreferences shared = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shared.edit();
        editor.putString("username",etUsername.getText().toString());
        editor.apply();
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.blogin:
                String uname = etUsername.getText().toString();

                String pass = helper.searchPass(uname);
                if(pass.equals(etPassword.getText().toString()))
                {
                    if(etUsername.getText().toString().equals(ADMIN))
                    {
                        startActivity(new Intent(this,AdminPage.class));
                    }
                    else {
                        saveInfo();
                        double bmi = helper.searchBMI(etUsername.getText().toString());
                        if(bmi == -1)
                        {
                            startActivity(new Intent(this,Edit.class));
                        }else {
                            startActivity(new Intent(this, MainActivity.class));
                        }
                    }
                }
                else{
                    Toast msg = Toast.makeText(this,"Wrong Credentials",Toast.LENGTH_SHORT);
                    msg.show();
                }

                break;
            case R.id.dregister:
                    startActivity(new Intent(this,register.class));
                break;
        }
    }
}
