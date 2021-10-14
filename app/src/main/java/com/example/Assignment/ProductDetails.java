package com.example.Assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ProductDetails extends AppCompatActivity {
TextView productid,productname,productdesc,productprice,providername,provideremail,providerphone,providerlocation;
   ImageView productimg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        productid=findViewById(R.id.productiddetail);
        productname=findViewById(R.id.producttitledetail);
        productdesc=findViewById(R.id.productdescriptiondetail);
        productprice=findViewById(R.id.productpricedetail);
        providername=findViewById(R.id.providernamedetail);
        provideremail=findViewById(R.id.provideremaildetail);
        providerphone=findViewById(R.id.providerphonenumberdetail);
        providerlocation=findViewById(R.id.providerlocationdetail);
        productimg=findViewById(R.id.detailimg);


        Bundle bundle=getIntent().getExtras();
        if(bundle.isEmpty()){
            finish();
            Toast.makeText(ProductDetails.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
        }
        else{
           productimg.setBackgroundResource(Integer.parseInt(bundle.getString("productimg")));
           productname.setText(bundle.getString("producttitle"));
            productid.setText(bundle.getString("productid"));
            productdesc.setText(bundle.getString("productdescription"));
            productprice.setText(bundle.getString("productprice"));
            providername.setText(bundle.getString("providername"));
            provideremail.setText(bundle.getString("provideremail"));
            providerphone.setText(bundle.getString("providerphone"));
            providerlocation.setText(bundle.getString("providerlocation"));








        }



    }
}