package red.theodo.restauranthygienechecker1453831;

import android.content.Intent;
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

public class EstablishmentMapFragment extends MapFragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private static final String ARG_PARAM1 = "param1";

    ArrayList<Establishment> searchResults;

    public static MapFragment newInstance(final ArrayList<Establishment> searchResults) {
        EstablishmentMapFragment fragment = new EstablishmentMapFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_PARAM1, searchResults);
        fragment.setArguments(args);
        return fragment;
    }


    // **** MapFragment extension

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            searchResults = getArguments().getParcelableArrayList(ARG_PARAM1);
            getMapAsync(this);
        }
    }


    // **** OnMapReadyCallback implementation ****

    @Override
    public void onMapReady(GoogleMap googleMap) {
        for (Establishment establishment : searchResults) {
            MarkerOptions markerOptions = new MarkerOptions()
                    .position(new LatLng(establishment.getLatitude(), establishment.getLongitude()))
                    .title(searchResults.get(0).getName());

            Marker marker = googleMap.addMarker(markerOptions);
            marker.setTag(establishment);
        }

        googleMap.setOnMarkerClickListener(this);
        if (searchResults.size() > 0) {
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(
                    searchResults.get(0).getLatitude(),
                    searchResults.get(0).getLongitude())));
            googleMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        }
    }


    // **** GoogleMap.OnMarkerClickListener implementation ****

    @Override
    public boolean onMarkerClick(Marker marker) {
        Establishment establishment = (Establishment) marker.getTag();
        Intent intent = new Intent(getActivity(), DetailsActivity.class);
        intent.putExtra(DetailsActivity.ESTABLISHMENT_KEY, establishment);
        startActivity(intent);
        return false;
    }

}
