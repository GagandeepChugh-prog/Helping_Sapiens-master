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
import java.util.List;

public class View_All extends AppCompatActivity {

    Button viewseeker;
    Button viewprovider;
    public List<Ratinglist> mRatingList;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayList<String> ratingList = new ArrayList<>();
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__all);

        viewprovider=(Button)findViewById(R.id.viewprovider);


        viewprovider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dun();
           //     display();
            }
        });



    }
    public void dun()
    {
        databaseReference=FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Rating").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot e: dataSnapshot.getChildren())
                {
                    seekerandprovider sp=new seekerandprovider();
                    String temp="";
                    temp=e.getKey();
                    sp=e.getValue(seekerandprovider.class);
                    ratingList.add(sp.providerrating);

                    temp=temp+" - "+sp.providerrating;
                    arrayList.add(temp);
                }
                display();

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void display()
    {
        int size=arrayList.size();
        String ar[]=new String[size];
        Double br[]=new Double[size];
        for(int i=0;i<size;i++)
        {
            ar[i]=arrayList.get(i);
            br[i]=Double.parseDouble(ratingList.get(i));
        }


        int n=(size);
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (br[j] <br[j+1])
                {
                    // swap temp and arr[i]
                    double temp = br[j];
                    String s=ar[j];
                    ar[j]=ar[j+1];
                    ar[j+1]=s;

                    br[j] = br[j+1];

                    br[j+1] = temp;
                }





        Intent intent=new Intent(View_All.this,Listprovide.class);
        intent.putExtra("array",ar);

        startActivity(intent);
    }

}
