package com.example.mukulsharma.helping_sapiens;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Submit_Request extends AppCompatActivity implements LocationListener {

    LocationManager locationManager;
    EditText category, details;
    private DatabaseReference databaseReference;
    Button sub;
    double latitude, longitude;
    String email;
    Spinner spinnerss;
    Location location;
    String categry;
    ArrayList<String> list=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit__request);

        details = (EditText) findViewById(R.id.detail);
        spinnerss=(Spinner)findViewById(R.id.spinnerss);

        categry="Entertainment";
        list.add("Entertainment");
        list.add("Grocery");
        list.add("Traveling");
        list.add("Miscellaneous");

        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerss.setAdapter(arrayAdapter);

        spinnerss.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerss.setSelection(position);
                TextView tv=(TextView)view;
                categry=tv.getText().toString();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        Bundle bundle = getIntent().getExtras();
        email = bundle.getString("Email");

        sub = (Button) findViewById(R.id.button);
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getloc();

                submit(email);
            }
        });

    }


    public void getloc() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        ActivityCompat.requestPermissions(Submit_Request.this,
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

    public void submit(String email) {

        databaseReference = FirebaseDatabase.getInstance().getReference();

        final String detail=details.getText().toString();
        Calendar cal=Calendar.getInstance();
        Date date=cal.getTime();

        request req=new request(email,categry,detail,date.toString(),String.valueOf(latitude),String.valueOf(longitude));
        databaseReference.child("Request").child(req.uniqueID).setValue(req);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Categories").child(req.category).child(req.uniqueID).setValue(req);

        Toast.makeText(Submit_Request.this, "Request Inserted", Toast.LENGTH_LONG).show();

        details.setText("");

    }

    public void onLocationChanged(Location location) {
        latitude=location.getLatitude();
        longitude=location.getLongitude();
    }

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
