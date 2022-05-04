package com.example.cuetbus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class Track_Location extends AppCompatActivity implements View.OnClickListener
{

//    private EditText t1;
    private Button send,send1;
    private Spinner s1;
    String[] BusId;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        this.setTitle("Find Bus Location");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track__location);
        BusId=getResources().getStringArray(R.array.Busid);

        s1=(Spinner) findViewById(R.id.find_bus_id);
        send=(Button)findViewById(R.id.track_btn);
        send1=(Button)findViewById(R.id.track1_btn);

        ArrayAdapter<String> adapter1=new ArrayAdapter<>(this,R.layout.spiner,R.id.spinner_id,BusId);
        s1.setAdapter(adapter1);

        send.setOnClickListener(this);
        // for all map viewing
        send1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Track_Location.this,ALL_MapsActivity2.class);
                startActivity(intent);
            }
        });
    }

    // after button for a particular location viewing is clicked
    @Override
    public void onClick(View v)
    {
        String bus_id1=s1.getSelectedItem().toString().trim();
//        String track_bus_id1=t1.getText().toString().trim();

        Intent intent =new Intent(Track_Location.this,Retrive_MapsActivity.class);
        intent.putExtra("Track_Bus_Id",bus_id1);
        startActivity(intent);
    }
}