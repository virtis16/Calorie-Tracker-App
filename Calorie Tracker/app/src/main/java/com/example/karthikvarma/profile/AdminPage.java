package com.example.karthikvarma.profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class AdminPage extends AppCompatActivity implements View.OnClickListener {

    Button AddFood;
    Button AddExercise;
    Button DeleteFood;
    Button DeleteExercise;
    Button AddUser;
    Button DeleteUser;
    Button bLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);
        AddFood = (Button) findViewById(R.id.addfood);
        AddExercise = (Button) findViewById(R.id.addexercise);
        DeleteFood = (Button) findViewById(R.id.deletefood);
        DeleteExercise = (Button) findViewById(R.id.deleteexercise);
        AddUser = (Button) findViewById(R.id.adduser);
        DeleteUser = (Button) findViewById(R.id.deleteuser);
        bLogout = (Button) findViewById(R.id.bLogout);

        //listeners
        AddFood.setOnClickListener(this);
        AddExercise.setOnClickListener(this);
        DeleteFood.setOnClickListener(this);
        DeleteExercise.setOnClickListener(this);
        AddUser.setOnClickListener(this);
        DeleteUser.setOnClickListener(this);
        bLogout.setOnClickListener(this);
        }
    @Override
    public void onClick (View v){
        switch (v.getId()) {
            case R.id.addfood:
                    startActivity(new Intent(this,addFood.class));
                break;
            case R.id.addexercise:
                    startActivity(new Intent(this,add_exercise.class));
                break;

            case R.id.deletefood:
                    startActivity(new Intent(this,deleteFood.class));
                break;

            case R.id.deleteexercise:
                    startActivity(new Intent(this,deleteExercise.class));
                break;

            case R.id.adduser:
                    startActivity(new Intent(this,addUser.class));
                break;

            case R.id.deleteuser:
                    startActivity(new Intent(this,deleteUser.class));
                break;

            case R.id.bLogout:
                startActivity(new Intent(this,login.class));
                break;
        }
    }
}
