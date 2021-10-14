package com.example.Assignment;

import static com.example.Assignment.Fragments.Products.productAdapter;
import static com.example.Assignment.Fragments.Providers.providerAdapter;
import static com.example.Assignment.MainActivity.productDataList;
import static com.example.Assignment.MainActivity.providerDataList;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.Assignment.Database.DBHelper;
import com.example.Assignment.ModelData.ProductData;
import com.example.Assignment.ModelData.ProviderData;
//This activity updates the provider record
public class UpdateProvider extends AppCompatActivity {
    Button setloco,update;
    EditText providername,provideremail,providerphone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_provider);

        setloco=findViewById(R.id.pupdatelocation);
       providername =findViewById(R.id.pnameupdate);
        provideremail=findViewById(R.id.pemailupdate);
        providerphone=findViewById(R.id.pphoneupdate);
        update=findViewById(R.id.pbtnupdate);

//Receiving values from Adapter on which user clicks
        String name=getIntent().getStringExtra("name");
        String desc=getIntent().getStringExtra("email");
        String price=getIntent().getStringExtra("phone");

//Set current on edittext
        providername.setText(name);
        provideremail.setText(desc);
        providerphone.setText(price);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                DBHelper dbHelper=new DBHelper(UpdateProvider.this);

//Function from database to update table in SQlite
                    dbHelper.UpdateRowFromProviderswithoutimage(providername.getText().toString(),provideremail.getText().toString(),providerphone.getText().toString(),name);




                Toast.makeText(UpdateProvider.this, "Provider Updated Successfully", Toast.LENGTH_SHORT).show();

                productDataList.clear();
               //Getting edited data from database and put in into List to display on Providers Fagment
                Cursor cursor1=dbHelper.getProductData();
                while(cursor1.moveToNext()){
                    ProductData obj=new ProductData(cursor1.getString(0),cursor1.getString(1),cursor1.getString(2),cursor1.getString(3),cursor1.getString(4),cursor1.getString(5),cursor1.getString(6),cursor1.getString(7),cursor1.getString(8));
                    productDataList.add(obj);
                }
                providerDataList.clear();
                Cursor cursor=dbHelper.getProvidersData();
                //Cursor crawling all rows and columns
                while(cursor.moveToNext()){
            ProviderData obj=new ProviderData(cursor.getString(0), cursor.getString(1), cursor.getString(2) );
                    providerDataList.add(obj);
                }

//as lists are update, so we have to inform adapters to update views.
                productAdapter.notifyDataSetChanged();
                providerAdapter.notifyDataSetChanged();
                finish();





            }
        });

    }
}