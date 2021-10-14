package com.example.Assignment.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.Assignment.Database.DBHelper;
import com.example.Assignment.ModelData.ProductData;
import com.example.Assignment.ProductDetails;
import com.example.Assignment.R;
import com.example.Assignment.UpdateProduct;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    Context context;
    List<ProductData> dataList;
    public ProductAdapter(Context context, List<ProductData> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_product,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {
        holder.title.setText(dataList.get(position).getProductname());
        holder.desc.setText(dataList.get(position).getProductdesc());
        holder.price.setText(dataList.get(position).getProductprice());
        holder.provider.setText(dataList.get(position).getProvidername());
        try{
            if(dataList.get(position).getProductname().equalsIgnoreCase("iPhone 7 Plus")||dataList.get(position).getProductname().equalsIgnoreCase("MacBook Pro 2020")||dataList.get(position).getProductname().equalsIgnoreCase("Intel Core i7")||dataList.get(position).getProductname().equalsIgnoreCase("Google Pixel 4xl")||dataList.get(position).getProductname().equalsIgnoreCase("Apple Watch Series 7")||dataList.get(position).getProductname().equalsIgnoreCase("Google Chromecast")||dataList.get(position).getProductname().equalsIgnoreCase("Google Pixelbook Go")||dataList.get(position).getProductname().equalsIgnoreCase("Samsung Galaxy A32")||dataList.get(position).getProductname().equalsIgnoreCase("Galaxy Watch 4")||dataList.get(position).getProductname().equalsIgnoreCase("Galaxy Book Pro 360")){
                holder.img.setImageResource(Integer.parseInt(dataList.get(position).getProductphoto()));

            }
            else{
                Glide
                        .with(context)
                        .load(Uri.parse(dataList.get(position).getProductphoto()))
                        .centerCrop()
                        .placeholder(R.drawable.imageerror)
                        .into(holder.img);
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        holder.optdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBHelper dbHelper=new DBHelper(context);
dbHelper.deleteRowFromProducts(dataList.get(holder.getAdapterPosition()).getProductname());
                dataList.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
            }
        });
        holder.optupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, UpdateProduct.class);
                intent.putExtra("name",dataList.get(holder.getAdapterPosition()).getProductname());
                intent.putExtra("desc",dataList.get(holder.getAdapterPosition()).getProductdesc());
                intent.putExtra("price",dataList.get(holder.getAdapterPosition()).getProductprice());
                intent.putExtra("pname",dataList.get(holder.getAdapterPosition()).getProvidername());
                intent.putExtra("pemail",dataList.get(holder.getAdapterPosition()).getProvideremail());
                intent.putExtra("pphone",dataList.get(holder.getAdapterPosition()).getProviderphone());



                context.startActivity(intent);

            }
        });


holder.main.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
            Intent intent=new Intent(context, ProductDetails.class);
        Bundle bundle=new Bundle();
        bundle.putString("productid",dataList.get(holder.getAdapterPosition()).getProductid());
        bundle.putString("productimg",dataList.get(holder.getAdapterPosition()).getProductphoto());
        bundle.putString("producttitle",dataList.get(holder.getAdapterPosition()).getProductname());
        bundle.putString("productdescription",dataList.get(holder.getAdapterPosition()).getProductdesc());
        bundle.putString("productprice",dataList.get(holder.getAdapterPosition()).getProductprice());
        bundle.putString("providername",dataList.get(holder.getAdapterPosition()).getProvidername());
        bundle.putString("provideremail",dataList.get(holder.getAdapterPosition()).getProvideremail());
        bundle.putString("providerphone",dataList.get(holder.getAdapterPosition()).getProviderphone());
        bundle.putString("providerlocation",dataList.get(holder.getAdapterPosition()).getProviderlocation());
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
});

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void filterlist(List<ProductData> exampledata) {
        dataList=exampledata;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView title,desc,provider,price,optupdate,optdelete;
        RelativeLayout main;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            optupdate=itemView.findViewById(R.id.optupdate);
            optdelete=itemView.findViewById(R.id.optdelete);
main=itemView.findViewById(R.id.productmainlayout);
            img=itemView.findViewById(R.id.productimg);
            title=itemView.findViewById(R.id.producttitle);
            desc=itemView.findViewById(R.id.productdescription);
            price=itemView.findViewById(R.id.productprice);
            provider=itemView.findViewById(R.id.productprovider);
        }
    }
}
