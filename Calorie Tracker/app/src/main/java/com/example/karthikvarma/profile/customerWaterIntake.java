package com.example.karthikvarma.profile;

import android.content.Context;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import java.text.SimpleDateFormat;

public class customerWaterIntake extends AppCompatActivity implements View.OnClickListener{

    RadioButton b1,b2,b3;
    Button addwater;
    int size = 0;
    DBHandler handler = new DBHandler(this);

    Calendar c = Calendar.getInstance();
    SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
    String current_date = date.format(c.getTime());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_water_intake);

        b1 = (RadioButton)findViewById(R.id.small);
        b2 = (RadioButton) findViewById(R.id.medium);
        b3 = (RadioButton) findViewById(R.id.large);
        addwater = (Button) findViewById(R.id.addwater);
        addwater.setOnClickListener(this);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
    }
    //to get username
    public String getusername()
    {
        SharedPreferences shared = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String username = shared.getString("username","No user");
        return username;
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.small:
                    size = 8;
                break;

            case R.id.medium:
                    size = 12;
                break;

            case R.id.large:
                    size = 16;
                break;

            case R.id.addwater:
                String username = getusername();
                int total = 0;
                int c = handler.checkDailyWater(username);
                total  = total + c + size;
                long ret = handler.addDailywater(username,total,current_date);
                Toast.makeText(this,"" + size + " oz",Toast.LENGTH_SHORT).show();
                break;
        }
    }

}
