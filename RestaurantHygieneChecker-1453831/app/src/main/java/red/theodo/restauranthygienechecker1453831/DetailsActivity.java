package red.theodo.restauranthygienechecker1453831;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class DetailsActivity extends AppCompatActivity implements OnMapReadyCallback {

    public static String ESTABLISHMENT_KEY = "establishment";

    private Establishment establishment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        establishment = getIntent().getParcelableExtra(ESTABLISHMENT_KEY);

        ((TextView) findViewById(R.id.detailsName)).setText(establishment.getName());
        ((RatingBar) findViewById(R.id.detailsRatingBar)).setRating(Float.parseFloat(establishment.getRating()));
        ((TextView) findViewById(R.id.detailsBusinessType)).setText(establishment.getBusinessType());
        ((TextView) findViewById(R.id.detailsAddress)).setText(establishment.getAddress());
        ((TextView) findViewById(R.id.detailsAuthority)).setText(establishment.getLocalAuthorityName());
        ((TextView) findViewById(R.id.detailsAuthorityEmail)).setText(establishment.getLocalAuthorityEmailAddress());
        ((TextView) findViewById(R.id.detailsName)).setText(establishment.getName());
        ((TextView) findViewById(R.id.detailsLongLat)).setText(String.format("(%s, %s)", establishment.getLongitude(), establishment.getLatitude()));

        MapView mapView = (MapView) findViewById(R.id.detailsMapView);
        mapView.onCreate(savedInstanceState);

        mapView.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        MarkerOptions markerOptions = new MarkerOptions()
                .position(new LatLng(establishment.getLatitude(), establishment.getLongitude()))
                .title(establishment.getName());

        Marker marker = googleMap.addMarker(markerOptions);
    }
}
