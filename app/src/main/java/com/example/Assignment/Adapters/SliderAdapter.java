package com.example.Assignment.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.Assignment.ModelData.OnlyProductData;
import com.example.Assignment.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.List;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.ViewHolder> {
    Context context;
    List<OnlyProductData> data;

    public SliderAdapter(Context context, List<OnlyProductData> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_slider,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
       viewHolder.img.setImageResource(Integer.parseInt(data.get(position).getProductphoto()));
       viewHolder.title.setText(data.get(position).getProductname());
        viewHolder.desc.setText(data.get(position).getProductdescription());
        viewHolder.price.setText(data.get(position).getProductprice());



    }


    @Override
    public int getCount() {
        return data.size();
    }
    public class ViewHolder extends  SliderViewAdapter.ViewHolder{

        ImageView img;
        TextView title,desc,price;

        public ViewHolder(View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.image);
            title=itemView.findViewById(R.id.header);
            desc=itemView.findViewById(R.id.sliderdesc);
            price=itemView.findViewById(R.id.sliderprice);
        }
    }
}