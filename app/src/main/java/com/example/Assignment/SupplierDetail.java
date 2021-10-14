package com.example.Assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import com.example.Assignment.Adapters.SliderAdapter;
import com.example.Assignment.Database.DBHelper;
import com.example.Assignment.ModelData.OnlyProductData;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class SupplierDetail extends AppCompatActivity {
    List<OnlyProductData> onlyProductDataList = new ArrayList<>();
    SliderAdapter sliderAdapter;
    SliderView sliderView;
    DBHelper dbHelper = new DBHelper(SupplierDetail.this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_detail);
        sliderView = findViewById(R.id.productslider);

        sliderAdapter = new SliderAdapter(SupplierDetail.this, onlyProductDataList);

        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.FILL);
        sliderView.setSliderTransformAnimation(SliderAnimations.FADETRANSFORMATION);
        sliderView.startAutoCycle();

sliderView.dataSetChanged();
        onlyProductDataList.clear();
        Cursor cursor = dbHelper.getProductsfromProvider(getIntent().getStringExtra("providername"));
        while (cursor.moveToNext()) {
            OnlyProductData data = new OnlyProductData(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
            onlyProductDataList.add(data);
        }
        sliderAdapter.notifyDataSetChanged();
    }



    @Override
    protected void onStop() {
        super.onStop();
        Intent intent=new Intent(SupplierDetail.this,MainActivity.class);
        startActivity(intent);
        finish();
    }


}