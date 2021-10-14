package com.example.Assignment;

import static com.example.Assignment.Fragments.Products.productAdapter;
import static com.example.Assignment.Fragments.Providers.providerAdapter;
import static com.example.Assignment.MainActivity.productDataList;
import static com.example.Assignment.MainActivity.providerDataList;
import static com.example.Assignment.MapsActivity.lat;
import static com.example.Assignment.MapsActivity.log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.example.Assignment.Database.DBHelper;
import com.example.Assignment.ModelData.ProductData;
import com.example.Assignment.ModelData.ProviderData;

public class AddProduct extends AppCompatActivity  {
Button setimg,upload,setbtnlcation;
Boolean check;
Uri IMGURL;
EditText prodname,proddesc,prodprice,providername,provideremail,providerphone;
String loc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        setimg=findViewById(R.id.setproductimg);
        prodname=findViewById(R.id.addprodname);
        proddesc=findViewById(R.id.addproddescription);
        prodprice=findViewById(R.id.addprodprice);
        providername=findViewById(R.id.addpname);
        provideremail=findViewById(R.id.addpemail);
        providerphone=findViewById(R.id.addpphone);
        setbtnlcation=findViewById(R.id.setlocationbtn);
upload=findViewById(R.id.btnadd);
setbtnlcation.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
Intent intent=new Intent(AddProduct.this,MapsActivity.class);
startActivity(intent);
    }

});
upload.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        check=true;
        DBHelper dbHelpercheck=new DBHelper(AddProduct.this);
        Boolean check2=dbHelpercheck.checkfromProvider(providername.getText().toString());
        if(check2==true){

            Cursor cursor= dbHelpercheck.getEmailandPhoneProvider(providername.getText().toString());
            while (cursor.moveToNext()){
                String email,phone;
                email=cursor.getString(0);
                 phone=cursor.getString(1);
                if(email.equalsIgnoreCase(provideremail.getText().toString())&&phone.equalsIgnoreCase(providerphone.getText().toString())){

                }
                else
                {
                    check=false;
                    provideremail.setError("Use Previous Contact Information");
                    providerphone.setError("Use Previous Contact Information");
                    Toast.makeText(AddProduct.this, "You can change Contact Information in Update Provider section", Toast.LENGTH_SHORT).show();
                }
            }




        }

        loc=lat+","+log;
        if(prodname.getText().toString().isEmpty()){
            prodname.setError("Product Name is Mandatory Field");
            check=false;
        }
        if(proddesc.getText().toString().isEmpty()){
            proddesc.setError("Product Description is Mandatory Field");
            check=false;
        }
        if(prodprice.getText().toString().isEmpty()){
            prodprice.setError("Product Price is Mandatory Field");
            check=false;
        }
        if(providername.getText().toString().isEmpty()){
            providername.setError("Provider Name is Mandatory Field");
            check=false;
        }
        if(provideremail.getText().toString().isEmpty()){
            provideremail.setError("Provider E-Mail is Mandatory Field");
            check=false;
        }
        if(providerphone.getText().toString().isEmpty()){
            providerphone.setError("Provider Phone is Mandatory Field");
            check=false;
        }
        if(check==true){
            DBHelper dbHelper=new DBHelper(AddProduct.this);
            if(IMGURL==null){
                IMGURL=Uri.parse("not set");
            }
            if(loc.equalsIgnoreCase("null,null")){
                loc="20.5937,78.9629";
            }
            Boolean datacheck= dbHelper.insertProductData(IMGURL.toString(),prodname.getText().toString(),proddesc.getText().toString(),prodprice.getText().toString(),providername.getText().toString(),provideremail.getText().toString(),providerphone.getText().toString(),loc);
            if(datacheck==true){
                Toast.makeText(AddProduct.this, "Product Added Successfully", Toast.LENGTH_SHORT).show();
              productDataList.clear();
                Cursor cursor1=dbHelper.getProductData();
                while(cursor1.moveToNext()){
                    ProductData obj=new ProductData(cursor1.getString(0),cursor1.getString(1),cursor1.getString(2),cursor1.getString(3),cursor1.getString(4),cursor1.getString(5),cursor1.getString(6),cursor1.getString(7),cursor1.getString(8));
                    productDataList.add(obj);
                }
                providerDataList.clear();
                Cursor cursor=dbHelper.getProvidersData();
                while(cursor.moveToNext()){
                    ProviderData obj=new ProviderData(cursor.getString(0), cursor.getString(1), cursor.getString(2) );

                    providerDataList.add(obj);
                }


                productAdapter.notifyDataSetChanged();
                providerAdapter.notifyDataSetChanged();
                finish();
            }
            else{
                Toast.makeText(AddProduct.this, "Not Entered", Toast.LENGTH_SHORT).show();
            }
        }


    }
});




        setimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(AddProduct.this)
                        .cropSquare()	    			//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            IMGURL =data.getData();
            Toast.makeText(AddProduct.this, "Uploaded!", Toast.LENGTH_SHORT).show();

        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(AddProduct.this, "Error while uploading image, Try Again", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show();
        }
    }

}