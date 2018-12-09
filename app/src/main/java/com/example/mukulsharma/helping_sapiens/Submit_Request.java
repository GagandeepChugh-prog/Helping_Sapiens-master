package com.example.mukulsharma.helping_sapiens;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;

public class Submit_Request extends AppCompatActivity {

    EditText category,details;
    private DatabaseReference databaseReference;
    Button sub;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit__request);
        category=(EditText)findViewById(R.id.category);
        details=(EditText)findViewById(R.id.detail);
        Bundle bundle=getIntent().getExtras();
        email=bundle.getString("Email");

        sub=(Button)findViewById(R.id.button);
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit(email);
            }
        });

    }

    public void submit(String email) {

        databaseReference = FirebaseDatabase.getInstance().getReference();
        final String categry=category.getText().toString();
        final String detail=details.getText().toString();
        Calendar cal=Calendar.getInstance();
        Date date=cal.getTime();

        request req=new request(email,categry,detail,date.toString(),"0","0");
        databaseReference.child("Request").child(req.uniqueID).setValue(req);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Categories").child(req.category).child(req.uniqueID).setValue(req);

        Toast.makeText(Submit_Request.this, "Request Inserted", Toast.LENGTH_LONG).show();
        category.setText("");
        details.setText("");

    }
}
