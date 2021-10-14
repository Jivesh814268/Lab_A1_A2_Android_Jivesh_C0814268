package com.example.Assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import com.example.Assignment.Adapters.ProviderAdapter;
import com.example.Assignment.Adapters.SliderAdapter;
import com.example.Assignment.Database.DBHelper;
import com.example.Assignment.ModelData.OnlyProductData;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class SupplierDetail extends AppCompatActivity {
   static public List<OnlyProductData> onlyProductDataList = new ArrayList<>();
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
        Cursor cursor = dbHelper.getProductsfromProvider(ProviderAdapter.provider);
        if(cursor==null){
            Toast.makeText(SupplierDetail.this, "Nothing Found", Toast.LENGTH_SHORT).show();
        }
        sliderAdapter.notifyDataSetChanged();
    }



    @Override
    protected void onStop() {
        super.onStop();
    }


}