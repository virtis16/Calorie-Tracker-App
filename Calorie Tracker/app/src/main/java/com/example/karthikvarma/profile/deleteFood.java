package com.example.karthikvarma.profile;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class deleteFood extends AppCompatActivity implements View.OnClickListener{

    Button VIEWALL,deleteFood;
    EditText foodName;
    DBHandler helper = new DBHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_food);
        foodName = (EditText) findViewById(R.id.foodName);
        deleteFood = (Button) findViewById(R.id.deleteFood);
        VIEWALL = (Button) findViewById(R.id.viewAll);
        deleteFood.setOnClickListener(this);
        VIEWALL.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.deleteFood:
                    helper.deleteFoodData(foodName.getText().toString());
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
                    buffer.append("foodNumber : " + res.getString(0) + "\n");
                    buffer.append("foodName : " + res.getString(1) + "\n");
                    buffer.append("calories : " + res.getString(2) + "\n");
                    buffer.append("foodType : " + res.getString(3) + "\n");
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
