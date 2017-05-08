package com.example.karthikvarma.profile;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class add_exercise extends AppCompatActivity implements View.OnClickListener{
    EditText ETYPE, ENAME, CALORIES,BMI1;
    Button ADDEXERCISE, VIEWALL;
    DBHandler helper = new DBHandler(this);

    RadioButton b1,b2,b3,b4;
    String etype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise);

        ETYPE = (EditText) findViewById(R.id.exercisetype);
        ENAME = (EditText) findViewById(R.id.exercisename);
        CALORIES = (EditText) findViewById(R.id.exercisecalories);
        ADDEXERCISE = (Button) findViewById(R.id.addexercise);
        BMI1 = (EditText)findViewById(R.id.bmi1);
        b1 = (RadioButton)findViewById(R.id.light);
        b2 = (RadioButton) findViewById(R.id.heavy);
        b3 = (RadioButton) findViewById(R.id.moderate);
        VIEWALL = (Button) findViewById(R.id.viewall);
        ADDEXERCISE.setOnClickListener(this);
        VIEWALL.setOnClickListener(this);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.addexercise:
                int f2 = Integer.parseInt(CALORIES.getText().toString());
                int bm1 = Integer.parseInt(BMI1.getText().toString());
                long test = helper.addExercise(etype,ENAME.getText().toString(),f2,bm1);
                if(test == -1)
                {
                    Toast.makeText(this,"" + etype + ENAME.getText().toString() + f2,Toast.LENGTH_LONG).show();

                }else
                {
                    Toast.makeText(this,"Data Inserted",Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.light:
                etype = "light";
                break;

            case R.id.heavy:
                etype = "heavy";
                break;

            case R.id.moderate:
                etype = "moderate";
                break;

            case R.id.viewall:
                Cursor res = helper.getAllExerciseData();
                if (res.getCount() == 0) {
                    //show error message
                    showMessage("Error", "Nothing found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("exerciseName : " + res.getString(0) + "\n");
                    buffer.append("calories : " + res.getString(2) + "\n");
                    buffer.append("exerciseType : " + res.getString(1) + "\n");
                    buffer.append("bmi : " + res.getString(3) + "\n");
                    buffer.append("\n");
                }

                //show all data

                showMessage("Exercise List", buffer.toString());

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
