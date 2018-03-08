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

public class DetailsMapFragment extends MapFragment implements OnMapReadyCallback{

    private static final String ARG_PARAM1 = "param1";

    Establishment establishment;

    public static MapFragment newInstance(Establishment establishment) {
        DetailsMapFragment fragment = new DetailsMapFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, establishment);
        fragment.setArguments(args);
        return fragment;
    }


    // **** MapFragment extension

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            establishment = getArguments().getParcelable(ARG_PARAM1);
            getMapAsync(this);
        }
    }

    // **** OnMapReadyCallback implementation ****

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MarkerOptions markerOptions = new MarkerOptions()
                .position(new LatLng(establishment.getLatitude(), establishment.getLongitude()))
                .title(establishment.getName());

            Marker marker = googleMap.addMarker(markerOptions);
            marker.setTag(establishment);

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(establishment.getLatitude(), establishment.getLongitude()), 18f));
    }

}
