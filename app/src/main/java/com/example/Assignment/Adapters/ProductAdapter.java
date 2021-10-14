package com.example.Assignment.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Assignment.ModelData.ProductData;
import com.example.Assignment.ProductDetails;
import com.example.Assignment.R;

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

        holder.img.setImageResource(Integer.parseInt(dataList.get(position).getProductphoto()));

holder.itemView.setOnClickListener(new View.OnClickListener() {
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
        TextView title,desc,provider,price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.productimg);
            title=itemView.findViewById(R.id.producttitle);
            desc=itemView.findViewById(R.id.productdescription);
            price=itemView.findViewById(R.id.productprice);
            provider=itemView.findViewById(R.id.productprovider);
        }
    }
}
