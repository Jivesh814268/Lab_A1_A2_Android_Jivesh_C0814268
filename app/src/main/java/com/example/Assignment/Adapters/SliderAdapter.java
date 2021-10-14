package com.example.Assignment.Adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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
       viewHolder.title.setText(data.get(position).getProductname());
        viewHolder.desc.setText(data.get(position).getProductdescription());
        viewHolder.price.setText(data.get(position).getProductprice());

        try{
            if(data.get(position).getProductname().equalsIgnoreCase("iPhone 7 Plus")||data.get(position).getProductname().equalsIgnoreCase("MacBook Pro 2020")||data.get(position).getProductname().equalsIgnoreCase("Intel Core i7")||data.get(position).getProductname().equalsIgnoreCase("Google Pixel 4xl")||data.get(position).getProductname().equalsIgnoreCase("Apple Watch Series 7")||data.get(position).getProductname().equalsIgnoreCase("Google Chromecast")||data.get(position).getProductname().equalsIgnoreCase("Google Pixelbook Go")||data.get(position).getProductname().equalsIgnoreCase("Samsung Galaxy A32")||data.get(position).getProductname().equalsIgnoreCase("Galaxy Watch 4")||data.get(position).getProductname().equalsIgnoreCase("Galaxy Book Pro 360")){
                viewHolder.img.setImageResource(Integer.parseInt(data.get(position).getProductphoto()));

            }
            else{
                Glide
                        .with(context)
                        .load(Uri.parse(data.get(position).getProductphoto()))
                        .centerCrop()
                        .placeholder(R.drawable.imageerror)
                        .into(viewHolder.img);
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }


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