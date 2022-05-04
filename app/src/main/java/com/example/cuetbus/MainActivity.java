package com.example.cuetbus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button button1,button2;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1=findViewById(R.id.buttonid1);
        button2=findViewById(R.id.buttonid2);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.buttonid1)
        {
            //where the user will go
            intent=new Intent(MainActivity.this, User_Login.class);
            intent.putExtra("name","User");
            startActivity(intent);
        }
        if(v.getId()==R.id.buttonid2)
        {
            //where the user will go
            intent=new Intent(MainActivity.this, Driver_Login.class);
            intent.putExtra("name","Driver");
            startActivity(intent);
        }
    }
}
