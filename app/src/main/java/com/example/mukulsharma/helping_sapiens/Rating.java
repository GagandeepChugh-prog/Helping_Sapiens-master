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

public class Rating extends AppCompatActivity {

    DatabaseReference databaseReference;
    Button ratingSubmit;
    EditText ratingBar;
    seekerandprovider seekerandproviderss=new seekerandprovider();
    String rat;
    String seekeremail;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        ratingBar=(EditText)findViewById(R.id.ratingbar);
        ratingSubmit=(Button)findViewById(R.id.ratingSubmission);
        Bundle bundle=getIntent().getExtras();
        seekeremail= (String) bundle.get("Email");

        ratingSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getrating();
            }
        });


    }
    public void  getrating() {
        int j = 0;
        rat = ratingBar.getText().toString();
        double n = Double.parseDouble(rat);
        if (n <0 || n >5) {
            Toast.makeText(this, "You entered an invalid value so please re-enter", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, Rating.class);
            intent.putExtra("Email", seekeremail);
            startActivity(intent);

        } else {


            databaseReference = FirebaseDatabase.getInstance().getReference();
            databaseReference.child("Rating").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot e : dataSnapshot.getChildren()) {
                        seekerandprovider sp = e.getValue(seekerandprovider.class);
                        String s1 = e.getKey();

                        if (s1.equals(seekeremail.replace(".", " "))) {

                            seekerandproviderss = e.getValue(com.example.mukulsharma.helping_sapiens.seekerandprovider.class);
                            Toast.makeText(Rating.this, "Read", Toast.LENGTH_LONG).show();
                            break;

                        }
                    }


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }

            });
            seekerandproviderss.seekerrating = Double.toString((Double.parseDouble(rat) * 0.75f + Double.parseDouble(seekerandproviderss.seekerrating) * 1.25) / 2);
            databaseReference = FirebaseDatabase.getInstance().getReference();
            databaseReference.child("Rating").child(seekeremail.replace(".", " ")).setValue(seekerandproviderss);


        }
    }

}
