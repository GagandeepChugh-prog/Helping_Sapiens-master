package com.example.mukulsharma.helping_sapiens;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Pending_Request extends AppCompatActivity {

    ArrayList<request> arrayList=new ArrayList<>();
    DatabaseReference databaseReference;
    String arr[],uid[];
    Button showrequest;
    Button showfinal;
    String EMail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending__request);
        showrequest=(Button)findViewById(R.id.showrequest);
        showfinal=(Button)findViewById(R.id.showfinal);
        Bundle bundle = getIntent().getExtras();
        EMail=bundle.getString("Email");
        showrequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showing();
            }
        });
        showfinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String backlist="Pending Rating";
                if(uid!=null) {
                    Intent i = new Intent(Pending_Request.this, List.class);
                    i.putExtra("backlist", backlist);
                    i.putExtra("AllRequest", arr);
                    i.putExtra("uid", uid);

                    i.putExtra("Email", EMail);
                    startActivity(i);
                }
            }
        });
    }

    public void showing() {
        databaseReference=FirebaseDatabase.getInstance().getReference();
        databaseReference.child("PendingRating").child(EMail.replace("."," ")).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot e: dataSnapshot.getChildren())
                {

                    request req=e.getValue(request.class);
                    if(req.open!=false)
                        arrayList.add(req);
                }

                arr=new String[arrayList.size()];
                uid=new String[arrayList.size()];
                request res=new request();
                for(int i=0;i<arr.length;i++)
                {
                    res=arrayList.get(i);
                    arr[i]=res.detail;
                    uid[i]=res.uniqueID;
                }

                Toast.makeText(Pending_Request.this, "Data is Read", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
