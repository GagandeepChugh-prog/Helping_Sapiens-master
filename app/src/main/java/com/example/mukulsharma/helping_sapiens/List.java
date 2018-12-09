package com.example.mukulsharma.helping_sapiens;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class List extends AppCompatActivity implements AdapterView.OnItemClickListener {
ListView lst;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        lst=(ListView)findViewById(R.id.lst);
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1);
        lst.setAdapter(arrayAdapter);
        lst.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TextView tv=(TextView)view;
        Toast.makeText(this,"You click on"+tv.getText(),Toast.LENGTH_SHORT).show();
    }
}
