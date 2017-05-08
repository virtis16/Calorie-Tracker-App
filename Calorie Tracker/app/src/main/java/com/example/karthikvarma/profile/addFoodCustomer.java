package com.example.karthikvarma.profile;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.icu.util.Calendar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class addFoodCustomer extends AppCompatActivity implements View.OnClickListener{
    EditText FOOD;
    Button CHECK, ADD;
    DBHandler handler = new DBHandler(this);
    int cal;

    Calendar c = Calendar.getInstance();
    SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
    String current_date = date.format(c.getTime());

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food_customer);

        FOOD = (EditText) findViewById(R.id.foodname);
        CHECK = (Button) findViewById(R.id.checkcalories);
        ADD = (Button) findViewById(R.id.addfood);
        CHECK.setOnClickListener(this);
        ADD.setOnClickListener(this);
    }

    public String getusername()
    {
        SharedPreferences shared = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String username = shared.getString("username","No user");
        return username;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.checkcalories:
                Cursor res = handler.getFoodCalories();
                if (res.getCount() == 0) {
                    //show error message
                    showMessage("Error", "Nothing found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    if(FOOD.getText().toString().equals(res.getString(0))) {
                        buffer.append("calories : " + res.getString(1) + "\n");
                        buffer.append("\n");
                    }
                }

                //show all data

                showMessage("Food List", buffer.toString());

                break;

            case R.id.addfood:
                String username = getusername();
                cal = handler.checkCalories(FOOD.getText().toString());
                long success = handler.addDailyFood(username,FOOD.getText().toString(),cal,current_date);
                Toast.makeText(this,"added to daily progress " + current_date,Toast.LENGTH_SHORT).show();
                break;
        }

    }

    public void showMessage(String title, String Message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }


}
