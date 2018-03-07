package red.theodo.restauranthygienechecker1453831;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;


/**
 * Created by theo on 07/03/18.
 */

public class EstablishmentMapFragment extends MapFragment implements OnMapReadyCallback {

    private static final String ARG_PARAM1 = "param1";

    ArrayList<Establishment> searchResults;
    ArrayList<Marker> markers;

    public static MapFragment create(final ArrayList<Establishment> searchResults){
        EstablishmentMapFragment fragment = new EstablishmentMapFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_PARAM1, searchResults);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if(getArguments() != null){
            searchResults = getArguments().getParcelableArrayList(ARG_PARAM1);
        }
    }

    @Override
    public void getMapAsync(OnMapReadyCallback onMapReadyCallback) {
        super.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng roosters = new LatLng(searchResults.get(0).getLatitude(), searchResults.get(0).getLongitude());
        MarkerOptions marker = new MarkerOptions().position(roosters).title(searchResults.get(0).getName());
        googleMap.addMarker(marker);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(roosters));
    }

}
