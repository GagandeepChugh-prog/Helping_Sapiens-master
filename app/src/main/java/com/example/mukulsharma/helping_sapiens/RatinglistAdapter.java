package com.example.mukulsharma.helping_sapiens;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class RatinglistAdapter extends BaseAdapter {

    private Context mContext;
    private List<Ratinglist> mRatinglist;

    public RatinglistAdapter(Context mContext, List<Ratinglist> mRatinglist) {
        this.mContext = mContext;
        this.mRatinglist = mRatinglist;
    }

    public int getCount() {
        return mRatinglist.size();
    }

    @Override
    public Object getItem(int position) {
        return mRatinglist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v=View.inflate(mContext,R.layout.item_product_list,null);
        TextView tvName=(TextView)v.findViewById(R.id.tv_name);
        TextView tvRating=(TextView)v.findViewById(R.id.tv_rating);

        tvName.setText(mRatinglist.get(position).getName());
         tvRating.setText(String.valueOf(mRatinglist.get(position).getRating()));

        return null;
    }
}
