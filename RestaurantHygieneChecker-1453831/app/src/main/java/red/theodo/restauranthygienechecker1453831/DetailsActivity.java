package red.theodo.restauranthygienechecker1453831;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;

public class DetailsActivity extends AppCompatActivity {

    public static String ESTABLISHMENT_KEY = "establishment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Establishment establishment = getIntent().getParcelableExtra(ESTABLISHMENT_KEY);

        ((TextView) findViewById(R.id.detailsName)).setText(establishment.getName());
        if (!establishment.getRating().equals("Exempt"))
            ((RatingBar) findViewById(R.id.detailsRatingBar)).setRating(Float.parseFloat(establishment.getRating()));
        ((TextView) findViewById(R.id.detailsBusinessType)).setText(establishment.getBusinessType());
        ((TextView) findViewById(R.id.detailsAddress)).setText(establishment.getAddress());
        ((TextView) findViewById(R.id.detailsAuthority)).setText(establishment.getLocalAuthorityName());
        ((TextView) findViewById(R.id.detailsAuthorityEmail)).setText(establishment.getLocalAuthorityEmailAddress());
        ((TextView) findViewById(R.id.detailsName)).setText(establishment.getName());
        ((TextView) findViewById(R.id.detailsLongLat)).setText(String.format("(%s, %s)", establishment.getLongitude(), establishment.getLatitude()));

        FragmentManager fm = getFragmentManager();
        MapFragment mapFragment = DetailsMapFragment.newInstance(establishment);
        fm.beginTransaction().replace(R.id.detailsMapView, mapFragment).commit();
    }

}
