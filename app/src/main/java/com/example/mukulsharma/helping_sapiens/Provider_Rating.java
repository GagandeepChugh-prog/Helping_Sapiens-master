package com.example.mukulsharma.helping_sapiens;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Provider_Rating extends AppCompatActivity {
    String EMail,submitratingemailid;
    String[] arr,uid;
    int position;
    EditText providerrating;
    Button submii;
    String RequestId, providerrat;
    DatabaseReference databaseReference;
    seekerandprovider ss=new seekerandprovider();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider__rating);
        providerrating=(EditText)findViewById(R.id.providerrating);
        submii=(Button)findViewById(R.id.submii);
        Bundle bundle = getIntent().getExtras();

        EMail=bundle.getString("Email");
        arr=bundle.getStringArray("All");
        uid=bundle.getStringArray("uid");
        position=bundle.getInt("position");
        RequestId=uid[position];

        subm();

        submii.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getpreviousrating();

                submitcurrentrating();


            }
        });

    }
    public void subm()
    {
        databaseReference=FirebaseDatabase.getInstance().getReference();
        databaseReference.child("PendingRating").child(EMail.replace("."," ")).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot e: dataSnapshot.getChildren())
                {
                    if((e.getKey().toString()).equals(RequestId))
                    {
                        request req=e.getValue(request.class);
                        submitratingemailid=req.email;
                        req.open=false;
                        databaseReference=FirebaseDatabase.getInstance().getReference();
                        databaseReference.child("PendingRating").child(EMail.replace("."," ")).child((req.email).replace("."," ")).setValue(req);
                        break;
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void getpreviousrating()
    {
        databaseReference=FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Rating").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot e: dataSnapshot.getChildren())
                {
                    String s=submitratingemailid.replace("."," ");
                    if((e.getValue().toString()).equals(s))
                    {
                        ss =e.getValue(seekerandprovider.class);

                        break;
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void submitcurrentrating() {
        providerrat = providerrating.getText().toString();
        double n = Double.parseDouble(providerrat);
        if (n < 0 || n > 5) {
            Toast.makeText(this, "You entered an invalid value so please re-enter", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, Provider_Rating.class);
            intent.putExtra("Position", position);
            intent.putExtra("uid", uid);
            intent.putExtra("All", arr);
            intent.putExtra("Email", EMail);

            startActivity(intent);

        } else {
            ss.providerrating = Double.toString((Double.parseDouble(providerrat) * 0.75f + Double.parseDouble(ss.providerrating) * 1.25) / 2);
            ss.providerrating = providerrat;
            databaseReference = FirebaseDatabase.getInstance().getReference();
            databaseReference.child("Rating").child(submitratingemailid.replace(".", " ")).setValue(ss);

        }
    }
}
