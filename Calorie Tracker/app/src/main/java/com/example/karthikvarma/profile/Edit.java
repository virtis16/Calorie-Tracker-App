package com.example.karthikvarma.profile;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Edit extends AppCompatActivity implements View.OnClickListener {
    DBHandler helper = new DBHandler(this);

    String username;
    EditText etname, etage, etheight, etweight,etbmi;
    Button edit,bmi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        etname = (EditText) findViewById(R.id.etName);
        etage = (EditText) findViewById(R.id.etAge);
        etheight = (EditText) findViewById(R.id.etheight);
        etweight = (EditText) findViewById(R.id.etweight);
        etbmi = (EditText) findViewById(R.id.etbmi);
        edit = (Button) findViewById(R.id.etupdate);
        bmi = (Button) findViewById(R.id.getbmi);
        bmi.setOnClickListener(this);
        edit.setOnClickListener(this);
        username = getusername();

    }

    public String getusername()
    {
        SharedPreferences shared = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String username = shared.getString("username","No user");
        return username;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.etupdate:
                    int age = Integer.parseInt(etage.getText().toString());
                    int height = Integer.parseInt(etheight.getText().toString());
                    int weight = Integer.parseInt(etweight.getText().toString());
                    double bmi1 = 703 * ((double)weight / (height * height))  ;
                    etbmi.setText("bmi : " + username + height + weight);
                long ret = helper.editProfile(username,etname.getText().toString(),age,height,weight,bmi1);
                Toast.makeText(this,"" + bmi1, Toast.LENGTH_SHORT).show();
                break;

            case R.id.getbmi:
                double bmi = helper.searchBMI(username);
                Toast.makeText(this,"" + bmi,Toast.LENGTH_SHORT).show();
                etbmi.setText("bmi : " +  bmi);
                break;
        }
    }
}
