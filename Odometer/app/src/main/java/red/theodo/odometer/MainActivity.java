package red.theodo.odometer;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static final int FINE_LOCATION_PERMISSION = 1;
    LocationManager locManager;
    LocationListener locListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            updateLonLat(location);
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };

    private double lastLat = 0;
    private double lastLng = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        // Check if we have location permissions.
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED) {
            // Check to see if we're also required to present a rationale.
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                new AlertDialog.Builder(this)
                        .setMessage(R.string.loc_req_message)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                requestLocPerms();
                            }
                        })
                        .create()
                        .show();
            } else {
                requestLocPerms();
            }

        } else {
            attachLocManager();
        }

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapView);
        mapFragment.getMapAsync(this);
    }

    private void requestLocPerms() {
        ActivityCompat.requestPermissions(
                MainActivity.this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, FINE_LOCATION_PERMISSION);
    }

    public void attachLocManager(){
        try{
            locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, locListener);
            Location location = locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            updateLonLat(location);
        } catch (SecurityException e){
            Log.wtf("abc", e.toString());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults){
        switch (requestCode){
            case FINE_LOCATION_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    attachLocManager();
                }
        }
    }

    void updateLonLat(Location location){
        ((TextView) findViewById(R.id.lon_value)).setText(String.format("%.2f", location.getLongitude()));
        ((TextView) findViewById(R.id.lat_value)).setText(String.format("%.2f", location.getLatitude()));
        lastLat = location.getLatitude();
        lastLng = location.getLongitude();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.addMarker(new MarkerOptions().position(new LatLng(lastLat, lastLng)).title("Your location!"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(lastLat, lastLng)));
    }
}
