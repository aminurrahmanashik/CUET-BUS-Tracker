package com.example.cuetbus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class send_bus_location extends AppCompatActivity implements View.OnClickListener {
    private Spinner t1,t2;
    private boolean b;
    private Button send;
    String[] BusId,BusName;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        this.setTitle("Send Bus Location");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_bus_location);

        // taking data from string.xml file
        BusId=getResources().getStringArray(R.array.Busid);
        BusName=getResources().getStringArray(R.array.BusName);

        t1=(Spinner) findViewById(R.id.bus_id);
        t2=(Spinner) findViewById(R.id.bus_name);
        send=(Button)findViewById(R.id.location_id);

        // array adapter for spinners
        ArrayAdapter<String> adapter1=new ArrayAdapter<>(this,R.layout.spiner,R.id.spinner_id,BusId);
        ArrayAdapter<String> adapter2=new ArrayAdapter<>(this,R.layout.spiner,R.id.spinner_id,BusName);
        t1.setAdapter(adapter1);
        t2.setAdapter(adapter2);

        send.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        String bus_id1=t1.getSelectedItem().toString().trim();
        String bus_name1=t2.getSelectedItem().toString().trim();

        Intent intent =new Intent(send_bus_location.this,MapsActivity.class);
        intent.putExtra("Bus_Id",bus_id1);
        intent.putExtra("Bus_Name",bus_name1);
        startActivity(intent);

    }
}