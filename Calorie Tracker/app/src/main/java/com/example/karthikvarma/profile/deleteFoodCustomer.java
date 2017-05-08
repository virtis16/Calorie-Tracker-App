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
import android.widget.Toast;

import java.text.SimpleDateFormat;

public class deleteFoodCustomer extends AppCompatActivity implements View.OnClickListener{

    Button viewall,deletefood;
    Calendar c = Calendar.getInstance();
    SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
    String current_date = date.format(c.getTime());
    DBHandler handler = new DBHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_food_customer);
        viewall = (Button)findViewById(R.id.viewall);
        deletefood = (Button) findViewById(R.id.deletefood);
        viewall.setOnClickListener(this);
        deletefood.setOnClickListener(this);

    }

    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.viewall:
                Cursor res = handler.getAllCustomerFood();
                String username = getusername();
                if (res.getCount() == 0) {
                    //show error message
                    showMessage("Error", "Nothing found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    if(username.equals(res.getString(0))) {
                        buffer.append("username: "+ res.getString(0) + "\n");
                        buffer.append("foodName : " + res.getString(1) + "\n");
                        buffer.append("calories : " + res.getString(2) + "\n");
                        buffer.append("date : " + res.getString(3) + "\n");
                        buffer.append("\n");
                    }
                }

                //show all data

                showMessage("Daily Food", buffer.toString());

                break;
            case R.id.deletefood:
                username = getusername();
                int ret = handler.deleteCustomerFood(username);
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
