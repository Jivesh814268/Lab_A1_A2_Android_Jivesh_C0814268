package com.example.Assignment.Adapters;

import static com.example.Assignment.Fragments.Products.productAdapter;
import static com.example.Assignment.Fragments.Providers.providerAdapter;
import static com.example.Assignment.MainActivity.productDataList;
import static com.example.Assignment.MainActivity.providerDataList;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Assignment.Database.DBHelper;
import com.example.Assignment.ModelData.ProductData;
import com.example.Assignment.ModelData.ProviderData;
import com.example.Assignment.R;
import com.example.Assignment.SupplierDetail;
import com.example.Assignment.UpdateProvider;

import java.util.List;

public class ProviderAdapter extends RecyclerView.Adapter<ProviderAdapter.ViewHolder> {
Context context;
List<ProviderData> dataList;
static public String provider;
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
        DBHelper dbHelper=new DBHelper(context);
      int size=  dbHelper.getProviderssize(dataList.get(position).getProvidername());
        if(size!=0){
            holder.itemsize.setText("No. of Products: "+size);

        }
holder.poptupdate.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent=new Intent(context, UpdateProvider.class);
        intent.putExtra("name",dataList.get(holder.getAdapterPosition()).getProvidername());
        intent.putExtra("email",dataList.get(holder.getAdapterPosition()).getProvideremail());
        intent.putExtra("phone",dataList.get(holder.getAdapterPosition()).getProviderphone());
        context.startActivity(intent);
    }
});
holder.compose.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Uri sms_uri = Uri.parse("smsto:"+dataList.get(holder.getAdapterPosition()).getProviderphone());
        Intent sms_intent = new Intent(Intent.ACTION_SENDTO, sms_uri);
        sms_intent.putExtra("sms_body", "Hi, Povider,");
        context.startActivity(sms_intent);
    }
});
holder.poptdelete.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        DBHelper dbHelper=new DBHelper(context);
        dbHelper.deleteRowFromProviders(dataList.get(holder.getAdapterPosition()).getProvidername());
        dataList.remove(holder.getAdapterPosition());
        notifyItemRemoved(holder.getAdapterPosition());
        productDataList.clear();
        Cursor cursor1=dbHelper.getProductData();
        while(cursor1.moveToNext()){
            ProductData obj=new ProductData(cursor1.getString(0),cursor1.getString(1),cursor1.getString(2),cursor1.getString(3),cursor1.getString(4),cursor1.getString(5),cursor1.getString(6),cursor1.getString(7),cursor1.getString(8));
            productDataList.add(obj);
        }
        providerDataList.clear();
        Cursor cursor=dbHelper.getProvidersData();
        while(cursor.moveToNext()){
            ProviderData obj=new ProviderData(cursor.getString(0), cursor.getString(1), cursor.getString(2));
            providerDataList.add(obj);
        }


        productAdapter.notifyDataSetChanged();
        providerAdapter.notifyDataSetChanged();
    }
});

        holder.mainlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(context, SupplierDetail.class);
                provider=dataList.get(holder.getAdapterPosition()).getProvidername();
                context.startActivity(intent);



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
        TextView title,email,phone,itemsize,poptupdate,poptdelete;
        RelativeLayout mainlayout;
        ImageView compose;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            compose=itemView.findViewById(R.id.compose);
            mainlayout=itemView.findViewById(R.id.pmainlayout);
            poptupdate= itemView.findViewById(R.id.poptupdate);
            poptdelete=itemView.findViewById(R.id.poptdelete);
            title=itemView.findViewById(R.id.suppliername);
            email=itemView.findViewById(R.id.supplieremail);
            phone=itemView.findViewById(R.id.supplierphone);
            itemsize=itemView.findViewById(R.id.suppliersize);
        }
    }
}
