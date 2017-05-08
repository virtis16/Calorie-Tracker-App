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

public class suggestExercise extends AppCompatActivity implements View.OnClickListener {

    Button suggest;
    DBHandler handler = new DBHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggest_exercise);
        suggest = (Button) findViewById(R.id.suggest);
        suggest.setOnClickListener(this);
    }


    public String getusername()
    {
        SharedPreferences shared = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String username = shared.getString("username","No user");
        return username;
    }

    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.suggest:
                //get bmi first
                String username = getusername();
                double bmi = handler.getbmi(username);

                //suggest exercise based on bmi 3
                Cursor res = handler.getAllExerciseData();
                if (res.getCount() == 0) {
                    //show error message
                    showMessage("Error", "Nothing found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                int bb;
                while (res.moveToNext()) {
                    bb = Integer.parseInt(res.getString(3));
                    if(bb > (bmi - 5) && bb < (bmi + 5)) {
                        buffer.append("" + res.getString(0) + "\n");
                        buffer.append("\n");
                    }
                }

                //show all data

                showMessage("Suggested Exercises", buffer.toString());
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
