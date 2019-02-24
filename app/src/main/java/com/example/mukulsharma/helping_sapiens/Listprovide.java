package com.example.mukulsharma.helping_sapiens;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Listprovide extends AppCompatActivity {

    String[] ar;
    ListView lstvi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listprovide);
        Bundle bundle=getIntent().getExtras();
        ar=bundle.getStringArray("array");
        lstvi=(ListView)findViewById(R.id.lstvi);
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,ar);
        lstvi.setAdapter(arrayAdapter);

    }
}
