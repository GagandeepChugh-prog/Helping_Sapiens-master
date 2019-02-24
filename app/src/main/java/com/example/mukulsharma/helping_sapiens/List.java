package com.example.mukulsharma.helping_sapiens;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class List extends AppCompatActivity implements AdapterView.OnItemClickListener {
ListView lst;
String arr[],uid[];
String cat,EMAIL;
String backlist;
ArrayList<request> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        lst=(ListView)findViewById(R.id.lst);
        Bundle bundle=getIntent().getExtras();
        arr=bundle.getStringArray("AllRequest");
        try{
            cat=bundle.getString("cat");
        }catch (Exception e)
        {
            cat="";
        }

        uid=bundle.getStringArray("uid");
        EMAIL=bundle.getString("Email");
        try{
            backlist=bundle.getString("backlist");
        }catch (Exception e)
        {
            backlist="";
        }

        // arrayList=(ArrayList<request>) getIntent().getSerializableExtra("All");
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,arr);
        lst.setAdapter(arrayAdapter);
        lst.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TextView tv=(TextView)view;
        Toast.makeText(this,"You click on"+tv.getText()+position,Toast.LENGTH_SHORT).show();
        if(backlist.equals("Pending Rating"))
        {
            Intent intent=new Intent(this,Provider_Rating.class);
            intent.putExtra("Position",position);
            intent.putExtra("uid",uid);
            intent.putExtra("All",arr);
            intent.putExtra("Email",EMAIL);
            startActivity(intent);
        }
        else {
            Intent intent=new Intent(this,Solver.class);
            intent.putExtra("Position",position);
            intent.putExtra("uid",uid);
            intent.putExtra("All",arr);
            intent.putExtra("cat",cat);
            intent.putExtra("Email",EMAIL);
            startActivity(intent);
        }
    }
}
