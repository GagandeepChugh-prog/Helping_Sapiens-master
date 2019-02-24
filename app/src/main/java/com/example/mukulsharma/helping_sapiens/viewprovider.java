package com.example.mukulsharma.helping_sapiens;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class viewprovider extends AppCompatActivity {

    private ListView lvProduct;
    private RatinglistAdapter adapter;
    public List<Ratinglist> mRatingList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewprovider);

        lvProduct=(ListView)findViewById(R.id.list_product);

        mRatingList=new ArrayList<>();

        mRatingList.add(new Ratinglist(1,"gairrychugh99",5.0));
        mRatingList.add(new Ratinglist(2,"gairrycgh99",2.0));
        mRatingList.add(new Ratinglist(3,"gairhugh99",3.0));
        mRatingList.add(new Ratinglist(4,"grychugh99",4.0));

        fun();






    }

    public void fun(){
        mRatingList=new ArrayList<>();
        mRatingList.add(new Ratinglist(1,"gairrychugh99",5.0));
        mRatingList.add(new Ratinglist(2,"gairrycgh99",2.0));
        mRatingList.add(new Ratinglist(3,"gairhugh99",3.0));
        mRatingList.add(new Ratinglist(4,"grychugh99",4.0));

        adapter=new RatinglistAdapter(getApplicationContext(),mRatingList);
        lvProduct.setAdapter(adapter);
    }
}
