package com.example.karthikvarma.profile;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class deleteExercise extends AppCompatActivity implements View.OnClickListener {
    Button Viewall, deleteExercise;
    EditText exerciseName;
    DBHandler helper = new DBHandler(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_exercise);
        exerciseName = (EditText) findViewById(R.id.exercisename);
        deleteExercise = (Button) findViewById(R.id.deleteexercise);
        Viewall = (Button) findViewById(R.id.viewall);
        deleteExercise.setOnClickListener(this);
        Viewall.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch(v.getId()){
            case R.id.deleteexercise:
                    helper.deleteExerciseData(exerciseName.getText().toString());
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
                    buffer.append("exerciseNumber : " + res.getString(0) + "\n");
                    buffer.append("exerciseName : " + res.getString(1) + "\n");
                    buffer.append("calories : " + res.getString(2) + "\n");
                    buffer.append("exerciseType : " + res.getString(3) + "\n");
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
