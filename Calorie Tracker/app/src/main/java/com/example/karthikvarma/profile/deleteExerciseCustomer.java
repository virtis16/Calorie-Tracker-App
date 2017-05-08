package com.example.karthikvarma.profile;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class deleteExerciseCustomer extends AppCompatActivity implements View.OnClickListener {
    Button viewall,deleteexercise;
    DBHandler handler = new DBHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_exercise_customer);

        viewall = (Button)findViewById(R.id.viewall);
        deleteexercise = (Button) findViewById(R.id.deleteexercise);
        viewall.setOnClickListener(this);
        deleteexercise.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.viewall:
                Cursor res = handler.getAllCustomerExercise();
                String username = getusername();
                if (res.getCount() == 0) {
                    //show error message
                    showMessage("Error", "Nothing found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    if(username.equals(res.getString(0))) {
                        buffer.append("ExerciseName : " + res.getString(1) + "\n");
                        buffer.append("calories : " + res.getString(2) + "\n");
                        buffer.append("date : " + res.getString(3) + "\n");
                        buffer.append("\n");
                    }
                }

                //show all data

                showMessage("Daily Food", buffer.toString());
                break;

            case R.id.deleteexercise:
                username = getusername();
                int ret = handler.deleteCustomerExercise(username);
                Toast.makeText(this,"" + ret,Toast.LENGTH_SHORT).show();
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

    public String getusername()
    {
        SharedPreferences shared = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String username = shared.getString("username","No user");
        return username;
    }
}
