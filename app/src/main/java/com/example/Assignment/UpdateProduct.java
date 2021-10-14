package com.example.Assignment;

import static com.example.Assignment.Fragments.Products.productAdapter;
import static com.example.Assignment.Fragments.Providers.providerAdapter;
import static com.example.Assignment.MainActivity.productDataList;
import static com.example.Assignment.MainActivity.providerDataList;

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

public class UpdateProduct extends AppCompatActivity {
    Uri IMGURL;
    Button upload;
   Boolean check;
    EditText prodname,proddesc,prodprice,pppname,pppemail,ppphone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);

        prodname=findViewById(R.id.updateprodname);
        proddesc=findViewById(R.id.updateproddescription);
        prodprice=findViewById(R.id.updateprodprice);
        upload=findViewById(R.id.btnupdate);
        pppname=findViewById(R.id.pppnameupdate);
        pppemail=findViewById(R.id.pppemailupdate);
        ppphone=findViewById(R.id.pppphoneupdate);
//Same, getting values from adapters
        String name=getIntent().getStringExtra("name");
                String desc=getIntent().getStringExtra("desc");
                        String price=getIntent().getStringExtra("price");
                        String pname=getIntent().getStringExtra("pname");
        String pemail=getIntent().getStringExtra("pemail");
        String pphone=getIntent().getStringExtra("pphone");


        DBHelper dbHelper=new DBHelper(UpdateProduct.this);
//same, setting current text to EditViews
pppname.setText(pname);
pppemail.setText(pemail);
ppphone.setText(pphone);
        prodname.setText(name);
        proddesc.setText(desc);
        prodprice.setText(price);




        upload.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                check=true;
                //this function from database checks if provider entered by the users already existed or not?
                Boolean check2=dbHelper.checkfromProvider(pppname.getText().toString());
                if(check2==false){
                    pppname.setError("Provider not exists");
                    check=false;
                }

                if (check == true) {
                    DBHelper dbHelper = new DBHelper(UpdateProduct.this);
                    if (IMGURL == null) {
                        dbHelper.UpdateRowFromProductswithoutimage(prodname.getText().toString(), proddesc.getText().toString(), prodprice.getText().toString(),pppname.getText().toString(),pppemail.getText().toString(),ppphone.getText().toString(), name);
                    } else {
                        dbHelper.UpdateRowFromProducts(prodname.getText().toString(), proddesc.getText().toString(), prodprice.getText().toString(), IMGURL.toString(),pppname.getText().toString(),pppemail.getText().toString(),ppphone.getText().toString(), name);
                    }


                    Toast.makeText(UpdateProduct.this, "Product Updated Successfully", Toast.LENGTH_SHORT).show();
                    productDataList.clear();
                    Cursor cursor1 = dbHelper.getProductData();
                    while (cursor1.moveToNext()) {
                        ProductData obj = new ProductData(cursor1.getString(0), cursor1.getString(1), cursor1.getString(2), cursor1.getString(3), cursor1.getString(4), cursor1.getString(5), cursor1.getString(6), cursor1.getString(7), cursor1.getString(8));
                        productDataList.add(obj);
                    }
                    providerDataList.clear();
                    Cursor cursor = dbHelper.getProvidersData();
                    while (cursor.moveToNext()) {
                        ProviderData obj=new ProviderData(cursor.getString(0), cursor.getString(1), cursor.getString(2) );
                        providerDataList.add(obj);
                    }


                    productAdapter.notifyDataSetChanged();
                    providerAdapter.notifyDataSetChanged();
                    finish();


                }
            }
        });



    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            IMGURL =data.getData();
            Toast.makeText(UpdateProduct.this, "Uploaded!", Toast.LENGTH_SHORT).show();

        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(UpdateProduct.this, "Error while uploading image, Try Again", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show();
        }
    }
}