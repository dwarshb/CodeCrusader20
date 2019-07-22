package com.bhadra.dwarsh.codecrusader20;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class DetailScreen extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private TextView addressvw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_screen);
        addressvw = (TextView)findViewById(R.id.addressvw);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        findViewById(R.id.homebtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DetailScreen.super.onBackPressed();
            }
        });
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        Intent intent = getIntent();
        String pos[] = intent.getStringExtra("POSITION").split(",");
        Log.e("Error",pos[0]+","+pos[1]);
        LatLng location = new LatLng(Double.parseDouble(pos[0]),Double.parseDouble(pos[1]));
        String address = intent.getStringExtra("ADDRESS");
        addressvw.setText(address);
        mMap.addMarker(new MarkerOptions().position(location).title(address));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(10f));
    }
}
