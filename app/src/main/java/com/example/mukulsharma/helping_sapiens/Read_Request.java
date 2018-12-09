package com.example.mukulsharma.helping_sapiens;

import android.app.DownloadManager;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.io.Serializable;
import java.util.ArrayList;

public class Read_Request extends AppCompatActivity {

    EditText categry,distance;
    DatabaseReference databaseReference;
    Button subm,fal;
    String cat,dist;
    ArrayList<request> arrayList=new ArrayList<request>();
    Double latitudecurrent,longitudecurrent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read__request);
        categry=(EditText)findViewById(R.id.category);
        distance=(EditText)findViewById(R.id.distance);
        subm=(Button)findViewById(R.id.submit1);
        fal=(Button)findViewById(R.id.fal);
        latitudecurrent=0.0;
        longitudecurrent=0.0;
        subm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fun();
            }
        });
        fal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                print();
            }
        });

    }

    public void print()
    {
        request temp = new request();
        temp = arrayList.get(0);
        temp.open=false;
        Intent i = new Intent(getApplicationContext(),Solver.class);
        i.putExtra("id",temp.uniqueID);
        startActivity(i);
    }

    public void fun() {
        cat=categry.getText().toString();
        dist=distance.getText().toString();
        databaseReference=FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Categories").child(cat).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot e: dataSnapshot.getChildren())
                {
                    Double latituderequest,longituderequest;
                    request req=e.getValue(request.class);
                    latituderequest=Double.parseDouble(req.latitude);
                    longituderequest=Double.parseDouble(req.longitude);
                    Double distance=Math.sqrt((latituderequest-latitudecurrent)*(latituderequest-latitudecurrent)+(longituderequest-longitudecurrent)*(longituderequest-longitudecurrent));
                    if(distance<=Double.parseDouble(dist) && req.open!=false)
                    arrayList.add(req);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
