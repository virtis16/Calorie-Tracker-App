package com.example.karthikvarma.profile;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class addFood extends AppCompatActivity implements View.OnClickListener{
    EditText FNAME,CALORIES;
    Button ADDFOOD,VIEWALL;
    DBHandler helper = new DBHandler(this);
    RadioButton b1,b2,b3,b4;
    String ftype;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        FNAME = (EditText) findViewById(R.id.foodname);
        CALORIES = (EditText) findViewById(R.id.foodcalories);
        ADDFOOD = (Button) findViewById(R.id.addFood);
        VIEWALL = (Button) findViewById(R.id.viewAll);
        b1 = (RadioButton)findViewById(R.id.snacks);
        b2 = (RadioButton) findViewById(R.id.breakfast);
        b3 = (RadioButton) findViewById(R.id.lunch);
        b4 = (RadioButton) findViewById(R.id.dinner);

        ADDFOOD.setOnClickListener(this);
        VIEWALL.setOnClickListener(this);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addFood:
                int f2 = Integer.parseInt(CALORIES.getText().toString());

                long test = helper.addFood( ftype, FNAME.getText().toString(), f2);
                if (test == -1) {
                    Toast.makeText(this,ftype + FNAME.getText().toString() + f2, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Data Inserted", Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.snacks:
                ftype = "snacks";
                break;

            case R.id.breakfast:
                ftype = "breakfast";
                break;

            case R.id.lunch:
                ftype = "lunch";
                break;

            case R.id.dinner:
                ftype = "dinner";
                break;

            case R.id.viewAll:
                Cursor res = helper.getAllFoodData();
                if (res.getCount() == 0) {
                    //show error message
                    showMessage("Error", "Nothing found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("foodName : " + res.getString(0) + "\n");
                    buffer.append("calories : " + res.getString(1) + "\n");
                    buffer.append("foodType : " + res.getString(2) + "\n");
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
