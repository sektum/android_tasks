package com.example.preprodactionmessenger.ui;

import android.content.Context;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.preprodactionmessenger.MyTitleSource;
import com.example.preprodactionmessenger.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocationActivity extends AppCompatActivity {
    private static final String TAG = "LocationActivity";
    public static final String REFERENCE_LOCATION = "locations";
    public static final String LONGITUDE = "longitude";
    public static final String LATITUDE = "latitude";
    public static final String USERNAME = "username";
    public static final String USER_DESCRIPTION = "Android beginner";
    private static final double START_LATITUDE_POINT = 50.0371469;
    private static final double START_LONGITUDE_POINT = 36.2185;
    private static final double ZOOM = 18.0;
    private MapView map = null;
    private IMapController mapController;
    private DatabaseReference  locationDatabaseReference;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context ctx = getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
        setContentView(R.layout.activity_location);
        Configuration.getInstance().setUserAgentValue(getString(R.string.agent));
        initMapView();
        initMapController();
        initializeLocationDatabaseReference();

        String mUserName = getIntent().getStringExtra(USERNAME);
        GeoPoint mUserCoord = new GeoPoint(50.03789999d, 36.22026999d);
        DatabaseReference mUserRef = locationDatabaseReference.child(mUserName);
        mUserRef.setValue(mUserCoord);

        final Map<String, GeoPoint> users = new HashMap<>();
        locationDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dsLocation : dataSnapshot.getChildren()) {
                    String userLocation = dsLocation.getKey();
                    Double longitute = Double.parseDouble(dsLocation.child(LONGITUDE).getValue().toString());
                    Double latitude = Double.parseDouble(dsLocation.child(LATITUDE).getValue().toString());
                    users.put(userLocation, new GeoPoint(latitude, longitute));
                    Log.d(TAG, "onDataChange: " + users.get(userLocation).toDoubleString());
                }

                addGeospitiallyReferencesOverlayIcon(users);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });


    }

    private void initializeLocationDatabaseReference() {
        locationDatabaseReference = FirebaseDatabase.getInstance().getReference().child(REFERENCE_LOCATION);
    }

    private void initMapView() {
        map = findViewById(R.id.map);
        map.setTileSource(MyTitleSource.GoogleMaps);
        map.setBuiltInZoomControls(true);
        map.setMultiTouchControls(true);
    }

    private void initMapController() {
        mapController = map.getController();
        mapController.setZoom(ZOOM);
        GeoPoint startPoint = new GeoPoint(START_LATITUDE_POINT, START_LONGITUDE_POINT);
        mapController.setCenter(startPoint);
    }

    private void addGeospitiallyReferencesOverlayIcon(Map<String, GeoPoint> usersLocation) {
        List<OverlayItem> geopositionUsers = new ArrayList<>();
        for (String userLocation : usersLocation.keySet()) {
            geopositionUsers.add(new OverlayItem(userLocation, USER_DESCRIPTION, usersLocation.get(userLocation)));
        }
        ItemizedOverlayWithFocus<OverlayItem> mOverlay = new ItemizedOverlayWithFocus<>(this, geopositionUsers,
                new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
                    @Override
                    public boolean onItemSingleTapUp(final int index, final OverlayItem item) {
                        return true;
                    }

                    @Override
                    public boolean onItemLongPress(final int index, final OverlayItem item) {
                        return false;
                    }
                });
        mOverlay.setFocusItemsOnTap(true);
        map.getOverlayManager().clear();
        map.getOverlays().add(mOverlay);
    }

    @Override
    protected void onResume() {
        super.onResume();
        map.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        map.onPause();
    }
}