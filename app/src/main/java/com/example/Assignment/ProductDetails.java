package com.example.Assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ProductDetails extends AppCompatActivity implements OnMapReadyCallback {
    TextView productid, productname, productdesc, productprice, providername, provideremail, providerphone;
    ImageView productimg;
    MapView mapView;
    GoogleMap map;
    String exact[];
String LocationDirections;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        productid = findViewById(R.id.productiddetail);
        productname = findViewById(R.id.producttitledetail);
        productdesc = findViewById(R.id.productdescriptiondetail);
        productprice = findViewById(R.id.productpricedetail);
        providername = findViewById(R.id.providernamedetail);
        provideremail = findViewById(R.id.provideremaildetail);
        providerphone = findViewById(R.id.providerphonenumberdetail);
        productimg = findViewById(R.id.detailimg);
        mapView = findViewById(R.id.mapview);

        Bundle bundle = getIntent().getExtras();
        if (bundle.isEmpty()) {
            finish();
            Toast.makeText(ProductDetails.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
        } else {
            try{
                if(bundle.getString("producttitle").equalsIgnoreCase("iPhone 7 Plus")||bundle.getString("producttitle").equalsIgnoreCase("MacBook Pro 2020")||bundle.getString("producttitle").equalsIgnoreCase("Intel Core i7")||bundle.getString("producttitle").equalsIgnoreCase("Google Pixel 4xl")||bundle.getString("producttitle").equalsIgnoreCase("Apple Watch Series 7")||bundle.getString("producttitle").equalsIgnoreCase("Google Chromecast")||bundle.getString("producttitle").equalsIgnoreCase("Google Pixelbook Go")||bundle.getString("producttitle").equalsIgnoreCase("Samsung Galaxy A32")||bundle.getString("producttitle").equalsIgnoreCase("Galaxy Watch 4")||bundle.getString("producttitle").equalsIgnoreCase("Galaxy Book Pro 360")){
                    productimg.setImageResource(Integer.parseInt(bundle.getString("productimg")));

                }
                else{
                    Glide
                            .with(this)
                            .load(Uri.parse(bundle.getString("productimg")))
                            .placeholder(R.drawable.imageerror)
                            .into(productimg);
                }

            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            productname.setText(bundle.getString("producttitle"));
            productid.setText(bundle.getString("productid"));
            productdesc.setText(bundle.getString("productdescription"));
            productprice.setText(bundle.getString("productprice"));
            providername.setText(bundle.getString("providername"));
            provideremail.setText(bundle.getString("provideremail"));
            providerphone.setText(bundle.getString("providerphone"));
            String imguri=bundle.getString("productimg");
LocationDirections=bundle.getString("providerlocation");

            mapView.onCreate(savedInstanceState);

            exact= LocationDirections.split(",");

            mapView.getMapAsync(this);


        }


    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;
        map.getUiSettings().setMyLocationButtonEnabled(false);
        LatLng positions=new LatLng(Float.parseFloat(exact[0]),Float.parseFloat(exact[1]));

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(positions,50));
        map.addMarker(new MarkerOptions().position(positions));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        map.setMyLocationEnabled(true);


    }
}