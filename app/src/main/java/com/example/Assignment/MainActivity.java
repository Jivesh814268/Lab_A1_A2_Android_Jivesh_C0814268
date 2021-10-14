package com.example.Assignment;

import static com.example.Assignment.Fragments.Products.productAdapter;
import static com.example.Assignment.Fragments.Providers.providerAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.github.clans.fab.FloatingActionMenu;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.example.Assignment.Adapters.PagerAdapter;
import com.example.Assignment.Database.DBHelper;
import com.example.Assignment.ModelData.ProductData;
import com.example.Assignment.ModelData.ProviderData;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    static TabLayout tabLayout;
    static ViewPager viewPager;
    FloatingActionMenu menu;
    TabItem tabItem1,tabItem2;
    Toolbar toolbar;
    MaterialSearchView materialSearchView;
 static public   List<ProductData> productDataList=new ArrayList<>();
   static public List<ProviderData> providerDataList=new ArrayList<>();
    PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
menu=findViewById(R.id.fabmenu);
materialSearchView=findViewById(R.id.search_view);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        tabLayout=findViewById(R.id.tablayout);
        viewPager=findViewById(R.id.viewpager);
        tabItem1=findViewById(R.id.products);
        tabItem2=findViewById(R.id.providers);
        int num=1;
        DBHelper dbHelper=new DBHelper(MainActivity.this);
      Cursor cursor=dbHelper.tablecheck();
        while(cursor.moveToNext()){
            num=Integer.parseInt(cursor.getString(0));
        }
        if(num==0){
            putDatainDatabase();

        }




materialSearchView.closeSearch();
materialSearchView.clearFocus();

       pagerAdapter=new PagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());

        viewPager.setAdapter(pagerAdapter);
        viewPager.setOnPageChangeListener(
                new ViewPager.SimpleOnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {
                        tabLayout.getTabAt(position).select();
                    }
                });
menu.setOnMenuButtonClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent=new Intent(MainActivity.this,AddProduct.class);
        startActivity(intent);

    }
});
getDataintoList();
getDataintoList2();
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                selectPage(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });




        materialSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return false;
            }
        });


    }

    private void filter(String query) {
        List<ProductData> exampledata=new ArrayList<>();
        List<ProviderData> exampledata2=new ArrayList<>();

if(viewPager.getCurrentItem()==0){
    for(ProductData data:productDataList){
        if(data.getProductname().toLowerCase(Locale.ROOT).contains(query.toLowerCase(Locale.ROOT))||data.getProductdesc().toLowerCase(Locale.ROOT).contains(query.toLowerCase(Locale.ROOT))){
            exampledata.add(data);
        }


    }
    productAdapter.filterlist(exampledata);
}
else{
    for(ProviderData data:providerDataList){
        if(data.getProvidername().toLowerCase(Locale.ROOT).contains(query.toLowerCase(Locale.ROOT))){
            exampledata2.add(data);
        }


    }
   providerAdapter.filterlist(exampledata2);

}




    }

    private void getDataintoList2() {
        DBHelper dbHelper=new DBHelper(MainActivity.this);
        providerDataList.clear();
        Cursor cursor=dbHelper.getProvidersData();
        while(cursor.moveToNext()){
            ProviderData obj=new ProviderData(cursor.getString(0), cursor.getString(1), cursor.getString(2) );
            providerDataList.add(obj);
        }
    }

    private void putDatainDatabase() {
        DBHelper dbHelper=new DBHelper(MainActivity.this);
        dbHelper.insertProductData(String.valueOf(R.drawable.iphone),"iPhone 7 Plus","Apple iPhone 7 Plus mobile was launched in September 2016. The phone comes with a 5.50-inch touchscreen display with a resolution of 1080x1920 pixels","36,000INR","Apple","support@apple.com","8006927753","37.3338,122.0113");
        dbHelper.insertProductData(String.valueOf(R.drawable.macbook),"MacBook Pro 2020","The MacBook Pro 2020 we tested comes with a quad-core 10th gen Intel Core Core i5 processor","110,000INR","Apple","support@apple.com","8006927753","37.3338,122.0113");
        dbHelper.insertProductData(String.valueOf(R.drawable.galaxywatch),"Apple Watch Series 7","Fundamental design changes were needed to achieve the vision of the larger Always-On Retina display","42,000INR","Apple","support@apple.com","8006927753","37.3338,122.0113");
        dbHelper.insertProductData(String.valueOf(R.drawable.pixelphone),"Google Pixel 4xl","Google Pixel 4xl is powered by a 1.8GHz octa-core Qualcomm Snapdragon 765G processor. It comes with 8GB of RAM.","64,000INR","Google","support@google.com","6502530000","37.3861,122.0839");
        dbHelper.insertProductData(String.valueOf(R.drawable.pixellap),"Google Pixelbook Go","Ultra thin, lightweight,a powerful processor, and up to 12 hours of battery life. Pixelbook Go is ready to go when you are. Made to move","58,000INR","Google","support@google.com","6502530000","37.3861,122.0839");
        dbHelper.insertProductData(String.valueOf(R.drawable.chromecast),"Google Chromecast","Chromecast is designed to make the most of the apps and entertainment already on your phone. Shows, movies, live TV, YouTube, photos and more","2,600INR","Google","support@google.com","6502530000","37.3861,122.0839");
        dbHelper.insertProductData(String.valueOf(R.drawable.galaxyphone),"Samsung Galaxy A32","The phone comes with a 6.40-inch touchscreen display. Samsung Galaxy A32 4G is powered by a 2GHz octa-core MediaTek Helio G80 processor","24,600INR","Samsung","support@samsung.com","180040SAMSUNG","37.2636,127.0286");
        dbHelper.insertProductData(String.valueOf(R.drawable.galaxylap),"Galaxy Book Pro 360","With a redesigned S Pen¹, a brilliant Super AMOLED screen, the latest Intel® 11th Gen Core™ processor","120,600INR","Samsung","support@samsung.com","180040SAMSUNG","37.2636,127.0286");
        dbHelper.insertProductData(String.valueOf(R.drawable.watch),"Galaxy Watch 4","The first smartwatch with Wear OS Powered by Samsung, Galaxy Watch 4 puts seamless access to your favorite apps on your wrist","20,600INR","Samsung","support@samsung.com","180040SAMSUNG","37.2636,127.0286");
        dbHelper.insertProductData(String.valueOf(R.drawable.processor),"Intel Core i7","The Intel® Core i7 is one of the most powerful Intel processors on the mainstream market.","47,600INR","Intel","support@intel.com","4087658080","37.3541,121.9552");

    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        materialSearchView.setMenuItem(item);
        return true;
    }
    static   void selectPage(int pageIndex){
        tabLayout.setScrollPosition(pageIndex,0f,true);
        viewPager.setCurrentItem(pageIndex);
    }
    private void getDataintoList() {
        DBHelper dbHelper=new DBHelper(MainActivity.this);
        productDataList.clear();
        Cursor cursor=dbHelper.getProductData();
        while(cursor.moveToNext()){
            ProductData obj=new ProductData(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8));
            productDataList.add(obj);
        }
    }
}