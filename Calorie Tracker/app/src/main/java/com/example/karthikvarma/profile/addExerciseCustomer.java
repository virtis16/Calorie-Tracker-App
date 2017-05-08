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

import java.text.SimpleDateFormat;

public class addExerciseCustomer extends AppCompatActivity implements View.OnClickListener{
    EditText EXERCISE,CALORIES;
    Button CHECK, ADD;
    DBHandler handler = new DBHandler(this);
    int cal;

    Calendar c = Calendar.getInstance();
    SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
    String current_date = date.format(c.getTime());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise_customer);

        EXERCISE = (EditText) findViewById(R.id.exercisename);
        CHECK = (Button) findViewById(R.id.checkcalories);
        ADD = (Button) findViewById(R.id.addexercisecustomer);
        CALORIES = (EditText) findViewById(R.id.calories);
        CHECK.setOnClickListener(this);
        ADD.setOnClickListener(this);
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
        switch(v.getId()){
            case R.id.addexercisecustomer:
                String username = getusername();
                cal = handler.checkCaloriesExercise(EXERCISE.getText().toString());
                long success = handler.addDailyExercise(username,EXERCISE.getText().toString(),cal,current_date);
                Toast.makeText(this,"added to daily progress " + success,Toast.LENGTH_SHORT).show();
                break;

            case R.id.checkcalories:
                Cursor res = handler.getAllExerciseData();
                if (res.getCount() == 0) {
                    //show error message
                    showMessage("Error", "Nothing found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("Exercise Number : " + res.getString(0) + "\n");
                    buffer.append("Exercise Type : " + res.getString(1) + "\n");
                    buffer.append("Exercise Name : " + res.getString(2) + "\n");
                    buffer.append("Calories : " + res.getString(3) + "\n");
                    buffer.append("\n");
                }

                //show all data

                showMessage("Food List", buffer.toString());
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
