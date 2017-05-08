package com.example.karthikvarma.profile;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class dailyProgress extends AppCompatActivity implements View.OnClickListener{
    EditText calories,caloriesburnt,waterintake,dailychallenge,goal;
    DBHandler helper = new DBHandler(this);
    Button update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_progress);

        calories = (EditText) findViewById(R.id.calories);
        caloriesburnt = (EditText)findViewById(R.id.caloriesburnt);
        waterintake = (EditText) findViewById(R.id.waterintake);
        dailychallenge = (EditText)findViewById(R.id.dailychallenge);
        goal = (EditText) findViewById(R.id.goal);
        update = (Button) findViewById(R.id.update);
        update.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId()){
            case R.id.update:
                String uname = getusername();
                int cal = helper.checkDailyFood(uname);
                calories.setText("Calorie Intake : "+ cal);

                int cal1 = helper.checkDailyExercise(uname);
                caloriesburnt.setText("Calories Burnt : " + cal1);

                int cal2 = helper.checkDailyWater(uname);
                waterintake.setText("Water Intake : " + cal2);

                int cal3 = helper.checkDailyChallenge(uname);
                dailychallenge.setText("Daily Challenge : " + cal3);

                if(cal3 == 0)
                {
                    cal3 = 1;
                }

                long cal4 = (cal2 / cal3) * 100;
                goal.setText("Goal Reached : " + cal4 + "%");

                break;


        }
    }


    public String getusername()
    {
        SharedPreferences shared = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String username = shared.getString("username","No user");
        return username;
    }
}
