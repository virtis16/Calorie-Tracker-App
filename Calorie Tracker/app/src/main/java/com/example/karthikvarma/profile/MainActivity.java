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

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button bLogout,bedit,addFood,dailyprogress,deletefood,addexercise,deleteexercise,addwater,dailychallenge,suggest;
    EditText etName;
    DBHandler db = new DBHandler(this);
    UserLocalStore userLocalStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bLogout = (Button) findViewById(R.id.bLogout);
        bedit = (Button) findViewById(R.id.bedit);
        etName = (EditText) findViewById(R.id.username);
        userLocalStore = new UserLocalStore(this);
        addFood = (Button) findViewById(R.id.addfoodcustomer);
        dailyprogress = (Button) findViewById(R.id.dailyprogress);
        deletefood = (Button) findViewById(R.id.deletefoodcustomer);
        addexercise = (Button) findViewById(R.id.addexercisecustomer);
        deleteexercise = (Button) findViewById(R.id.deleteexercisecustomer);
        dailychallenge = (Button) findViewById(R.id.dailychallenge);
        suggest = (Button) findViewById(R.id.suggest);
        addwater = (Button) findViewById(R.id.addwater);
        bLogout.setOnClickListener(this);
        suggest.setOnClickListener(this);
        dailyprogress.setOnClickListener(this);
        bedit.setOnClickListener(this);
        addFood.setOnClickListener(this);
        deletefood.setOnClickListener(this);
        addexercise.setOnClickListener(this);
        deleteexercise.setOnClickListener(this);
        addwater.setOnClickListener(this);
        dailychallenge.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayUsername();
    }

    public void displayUsername()
    {
        SharedPreferences shared = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String username = shared.getString("username","No user");
        Toast.makeText(this,"Welcome " + username,Toast.LENGTH_LONG).show();
    }

    public void deleteUsername()
    {
        SharedPreferences shared = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        shared.edit().remove("username");
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.bLogout:
                    deleteUsername();
                    startActivity(new Intent(this,login.class));
                break;
            case R.id.bedit:
                    startActivity(new Intent(this,Edit.class));
                break;

            case R.id.deletefoodcustomer:
                    startActivity(new Intent(this,deleteFoodCustomer.class));
                break;

            case R.id.addfoodcustomer:
                startActivity(new Intent(this,addFoodCustomer.class));
                break;

            case R.id.deleteexercisecustomer:
                startActivity(new Intent(this,deleteExerciseCustomer.class));
                break;

            case R.id.addwater:
                startActivity(new Intent(this,customerWaterIntake.class));
                break;

            case R.id.dailychallenge:
                startActivity(new Intent(this,dailyChallenge.class));
                break;

            case R.id.dailyprogress:
                    startActivity(new Intent(this,dailyProgress.class));
                break;

            case R.id.suggest:
                startActivity(new Intent(this,suggestExercise.class));
                break;

            case R.id.addexercisecustomer:
                startActivity(new Intent(this,addExerciseCustomer.class));
                break;

        }
    }
}
