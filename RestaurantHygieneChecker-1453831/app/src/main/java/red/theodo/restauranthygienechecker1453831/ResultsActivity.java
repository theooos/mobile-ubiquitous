package red.theodo.restauranthygienechecker1453831;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class ResultsActivity extends AppCompatActivity {

    private TextView mTextMessage;
    FragmentManager fm = getFragmentManager();

    private ArrayList<Establishment> searchResults;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment chosenFragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_list:
                    chosenFragment = EstablishmentListFragment.newInstance(searchResults);
                    mTextMessage.setText("List");
                    break;
                case R.id.navigation_map:
                    chosenFragment = EstablishmentMapFragment.newInstance(searchResults);
                    mTextMessage.setText("Map");
                    break;
            }
            fm.beginTransaction().replace(R.id.listMapFragment, chosenFragment).commit();
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        mTextMessage = (TextView) findViewById(R.id.message);

        searchResults = performSearch();

        EstablishmentListFragment list = EstablishmentListFragment.newInstance(searchResults);
        fm.beginTransaction().add(R.id.listMapFragment, list).commit();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    // TODO
    private MapFragment generateMap() {
        MapFragment map = MapFragment.newInstance();
        map.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                LatLng roosters = new LatLng(searchResults.get(0).getLatitude(), searchResults.get(0).getLongitude());
                MarkerOptions marker = new MarkerOptions().position(roosters).title(searchResults.get(0).getName());
                googleMap.addMarker(marker);
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(roosters));
            }
        });
        return map;
    }

    // TODO
    private ArrayList<Establishment> performSearch() {
        ArrayList<Establishment> searchResults = new ArrayList<>();
        searchResults.add(EstablishmentBuilder.anEstablishment()
                .withName("Roosters")
                .withRating("5")
                .withLongLat(-1.931567, 52.446296)
                .withDistance(1.2)
                .build());
        searchResults.add(EstablishmentBuilder.anEstablishment()
            .withName("Dixies")
            .withRating("4")
            .build());

        return searchResults;
    }

}
