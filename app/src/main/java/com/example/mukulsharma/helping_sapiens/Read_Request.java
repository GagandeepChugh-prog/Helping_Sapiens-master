package com.example.mukulsharma.helping_sapiens;


import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.io.Serializable;
import java.util.ArrayList;

public class Read_Request extends AppCompatActivity implements LocationListener {

    EditText categry,distance;
    Location location;
    LocationManager locationManager;
    DatabaseReference databaseReference;
    Button subm,fal;
    String cat,dist;
    ArrayList<request> arrayList=new ArrayList<request>();
    Double latitudecurrent,longitudecurrent;
    String arr[],uid[],key[];
    Spinner spinner;
    String EMAIL;
    ArrayList<String> list=new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read__request);
        categry=(EditText)findViewById(R.id.category);
        distance=(EditText)findViewById(R.id.distance);
        subm=(Button)findViewById(R.id.submit1);
        fal=(Button)findViewById(R.id.fal);
        spinner=(Spinner)findViewById(R.id.spinner);
        cat="Entertainment";
        list.add("Entertainment");
        list.add("Grocery");
        list.add("Traveling");
        list.add("Miscellaneous");

        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner.setSelection(position);
                TextView tv=(TextView)view;
                cat=tv.getText().toString();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        latitudecurrent=0.0;
        longitudecurrent=0.0;
        Bundle bundle=getIntent().getExtras();
        EMAIL=bundle.getString("Email");

        subm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getloc();
                fun();
   //             shun();
            }
        });
        fal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                print();
            }
        });


       // listiii.setOnItemClickListener(this);

    }


    public void getloc() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        ActivityCompat.requestPermissions(Read_Request.this,
                new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,android.Manifest.permission.ACCESS_COARSE_LOCATION},1 );

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.



        }

        location = locationManager.getLastKnownLocation(locationManager.GPS_PROVIDER);

        onLocationChanged(location);
    }
    public void print()
    {
        Intent i = new Intent(getApplicationContext(),List.class);
        i.putExtra("AllRequest",arr);
        i.putExtra("uid",uid);
        i.putExtra("cat",cat);
        i.putExtra("backlist","backlist");
        i.putExtra("Email",EMAIL);
      //  i.putExtra("All",arrayList);
        startActivity(i);
    }

    public void fun() {

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
                Toast.makeText(Read_Request.this, "Data Inserted", Toast.LENGTH_LONG).show();
                arr=new String[arrayList.size()];
                uid=new String[arrayList.size()];
                request res=new request();
                for(int i=0;i<arr.length;i++)
                {
                    res=arrayList.get(i);
                    arr[i]=res.detail;
                    uid[i]=res.uniqueID;
                }

                Toast.makeText(Read_Request.this, "Data is Inserted", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onLocationChanged(Location location) {
        latitudecurrent=location.getLatitude();
        longitudecurrent=location.getLongitude();
    }

  /*  public void shun() {
        databaseReference=FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Categories").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int i=0;
                for(DataSnapshot e: dataSnapshot.getChildren())
                {
                    key[i]=e.getKey();
                    i++;

                }


                Toast.makeText(Read_Request.this, "Data is Inserted", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,key);
        listiii.setAdapter(arrayAdapter);
    }*/

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }


}
