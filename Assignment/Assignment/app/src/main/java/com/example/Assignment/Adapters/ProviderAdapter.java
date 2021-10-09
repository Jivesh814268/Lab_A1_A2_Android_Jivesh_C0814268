package com.example.Assignment.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Assignment.ModelData.ProviderData;
import com.example.Assignment.R;
import com.example.Assignment.SupplierDetail;

import java.util.List;

public class ProviderAdapter extends RecyclerView.Adapter<ProviderAdapter.ViewHolder> {
Context context;
List<ProviderData> dataList;

    public ProviderAdapter(Context context, List<ProviderData> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ProviderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
     View view= LayoutInflater.from(context).inflate(R.layout.item_providers,parent,false);
     return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProviderAdapter.ViewHolder holder, int position) {
        holder.title.setText(dataList.get(position).getProvidername());
        holder.email.setText(dataList.get(position).getProvideremail());
        holder.phone.setText(dataList.get(position).getProviderphone());
        holder.itemsize.setText("No. of Products: "+dataList.get(position).getTotalitems());



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(context, SupplierDetail.class);
                String provider=dataList.get(holder.getAdapterPosition()).getProvidername();
                intent.putExtra("providername",provider);
                context.startActivity(intent);
                ((Activity)context).finish();



            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void filterlist(List<ProviderData> exampledata2) {
        dataList=exampledata2;
        notifyDataSetChanged();
    }

    public class ViewHolder  extends RecyclerView.ViewHolder {
        TextView title,email,phone,itemsize;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.suppliername);
            email=itemView.findViewById(R.id.supplieremail);
            phone=itemView.findViewById(R.id.supplierphone);
            itemsize=itemView.findViewById(R.id.suppliersize);
        }
    }
}
