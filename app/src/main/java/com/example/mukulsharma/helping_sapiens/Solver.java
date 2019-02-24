package com.example.mukulsharma.helping_sapiens;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Solver extends AppCompatActivity {

    EditText anser;
    int position;
    String cat,EMAIL;
    request requestclosed;
    DatabaseReference databaseReference;
    TextView prolem;
    String[] arr,uid;
    Button submm;
    ArrayList<request> arrayList;
    request res=new request();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solver);
        anser=(EditText)findViewById(R.id.answer);
        prolem=(TextView) findViewById(R.id.problem);
        submm=(Button)findViewById(R.id.submitt);

        Bundle bundle = getIntent().getExtras();
        cat=bundle.getString("cat");
        arr=bundle.getStringArray("All");
        uid=bundle.getStringArray("uid");
        //arrayList=(ArrayList<request>) getIntent().getSerializableExtra("All");
        position=bundle.getInt("Position");

        EMAIL=bundle.getString("Email");



        prolem.setText(arr[position]);

        final String uni = (String) bundle.get("id");
        final request re = new request();
        re.open=false;
        sub();
        submm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference=FirebaseDatabase.getInstance().getReference();
                databaseReference.child("Categories").child(cat).child(uid[position]).setValue(requestclosed);
                Intent intent=new Intent(Solver.this,Rating.class);
                intent.putExtra("Email",requestclosed.email);
                databaseReference=FirebaseDatabase.getInstance().getReference();
                String em=requestclosed.email;
                requestclosed.email=EMAIL;
                requestclosed.open=true;
                databaseReference.child("PendingRating").child(em.replace("."," ")).child(requestclosed.uniqueID).setValue(requestclosed);
                startActivity(intent);




            }
        });

    }
    public void sub()
    {
        databaseReference=FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Categories").child(cat).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot e: dataSnapshot.getChildren())
                {
                    request req=e.getValue(request.class);
                    String s1=req.uniqueID;
                    String s2=uid[position];
                    if(s1.equals(s2))
                    {
                        requestclosed=req;
                        requestclosed.open=false;
                        Toast.makeText(Solver.this,"Data DOne",Toast.LENGTH_LONG).show();
                        break;

                    }
                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });


    }


}
