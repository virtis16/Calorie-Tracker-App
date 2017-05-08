package com.example.karthikvarma.profile;

import android.content.Context;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;

public class dailyChallenge extends AppCompatActivity implements View.OnClickListener{

    EditText challenge;
    Button setchallenge;
    DBHandler handler = new DBHandler(this);
    Calendar c = Calendar.getInstance();
    SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
    String current_date = date.format(c.getTime());


    public String getusername()
    {
        SharedPreferences shared = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String username = shared.getString("username","No user");
        return username;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_challenge);

        challenge = (EditText) findViewById(R.id.challenge);
        setchallenge = (Button) findViewById(R.id.addchallenge);
        setchallenge.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.addchallenge:
                String username = getusername();
                int goal = Integer.parseInt(challenge.getText().toString());
                long res = handler.addDailyChallenge(username,goal,current_date);
                Toast.makeText(this,"added to daily progress " + res,Toast.LENGTH_SHORT).show();

                break;
        }
    }

}
