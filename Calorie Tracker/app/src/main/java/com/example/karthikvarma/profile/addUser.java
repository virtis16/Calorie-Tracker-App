package com.example.karthikvarma.profile;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class addUser extends AppCompatActivity implements View.OnClickListener{
    DBHandler helper = new DBHandler(this);
    Button ADDUSER,VIEWALL;
    EditText USERNAME,PASSWORD;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        ADDUSER = (Button) findViewById(R.id.adduser);
        USERNAME = (EditText) findViewById(R.id.username);
        PASSWORD = (EditText) findViewById(R.id.password);
        VIEWALL = (Button) findViewById(R.id.viewall);
        ADDUSER.setOnClickListener(this);
        VIEWALL.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.adduser:
                String username = USERNAME.getText().toString();
                String password = PASSWORD.getText().toString();

                helper.insertUser(username,password);
                break;

            case R.id.viewall:
                Cursor res = helper.getAllUsers();
                if (res.getCount() == 0) {
                    //show error message
                    showMessage("Error", "Nothing found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                int count = 1;
                while (res.moveToNext()) {
                    if(res.getString(0).equals("admin"))
                    {

                    }else {
                        buffer.append(count + " : " + res.getString(0) + "\n");
                        count++;

                        //buffer.append("password : " + res.getString(1) + "\n");
                        buffer.append("\n");
                    }
                }

                //show all data

                showMessage("User List", buffer.toString());
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
