package com.example.mukulsharma.helping_sapiens;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Solver extends AppCompatActivity {

    EditText anser;
    DatabaseReference databaseReference;
    TextView prolem;
    Button submm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solver);
        anser=(EditText)findViewById(R.id.answer);
        prolem=(TextView) findViewById(R.id.problem);
        submm=(Button)findViewById(R.id.submitt);

        Bundle bundle = getIntent().getExtras();
        final String uni = (String) bundle.get("id");
        final request re = new request();
        re.open=false;
        submm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference = FirebaseDatabase.getInstance().getReference();
                re.open=false;

                databaseReference.child("Categories").child("Grocery").child(uni).setValue(re);



            }
        });

    }
}
