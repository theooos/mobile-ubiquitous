package red.theodo.restauranthygienechecker1453831;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import red.theodo.restauranthygienechecker1453831.ResponseObjects.AuthoritiesOption;
import red.theodo.restauranthygienechecker1453831.ResponseObjects.BusinessTypeOption;
import red.theodo.restauranthygienechecker1453831.ResponseObjects.RegionsOption;
import red.theodo.restauranthygienechecker1453831.ResponseObjects.SortByOption;

public class MainActivity extends AppCompatActivity {

    private boolean advanced = false;
    private RequestQueue requestQueue;

    private LocationManager locManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        findViewById(R.id.advancedContainer).setVisibility(ConstraintLayout.GONE);
        ((SeekBar) findViewById(R.id.seekRadius)).setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                ((TextView) findViewById(R.id.viewRadiusCount)).setText(String.format("%s", i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        populateSpinners();
    }

    public void performSearch(View view) {
        String query = ((EditText) findViewById(R.id.search_text)).getText().toString();

        if (advanced) {
            advancedSearch();
        } else {
            if (query.length() == 0)
                localSearch();
            else
                simpleSearch();
        }
    }

    private void localSearch() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 100);
            return;
        }

        Location location = locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        SearchDetails searchDetails = SearchDetailsBuilder.aSearchDetails()
                .withMode(SearchMode.Local)
                .withLongitude(String.valueOf(location.getLongitude()))
                .withLatitude(String.valueOf(location.getLatitude()))
                .withPageSize("15")
                .withSortOptionKey("distance")
                .build();

        Search.search(this, requestQueue, searchDetails);
    }

    private void simpleSearch() {
        String input = ((TextView) findViewById(R.id.search_text)).getText().toString();

        SearchDetailsBuilder searchBuilder = SearchDetailsBuilder.aSearchDetails()
                .withMode(SearchMode.Simple)
                .withSortOptionKey("Rating")
                .withPageSize("50");

        boolean byAddress = ((ToggleButton) findViewById(R.id.toggleNameLocation)).isChecked();
        if(byAddress)
            searchBuilder = searchBuilder.withAddress(input);
        else
            searchBuilder = searchBuilder.withName(input);

        Search.search(this, requestQueue, searchBuilder.build());
    }

    private void advancedSearch() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 102);
            return;
        }
        String input = ((TextView) findViewById(R.id.search_text)).getText().toString();

        SearchDetailsBuilder searchBuilder = SearchDetailsBuilder.aSearchDetails()
                .withMode(SearchMode.Advanced)
                .withSortOptionKey("Rating");

        boolean byLocation = (input.length() == 0);
        if (byLocation){
            Location location = locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            searchBuilder = searchBuilder
                    .withLatitude(String.valueOf(location.getLatitude()))
                    .withLongitude(String.valueOf(location.getLongitude()));
        }
        else {
            boolean byAddress = ((ToggleButton) findViewById(R.id.toggleNameLocation)).isChecked();
            if(byAddress)
                searchBuilder = searchBuilder.withAddress(input);
            else
                searchBuilder = searchBuilder.withName(input);
        }


        if(((CheckBox) findViewById(R.id.checkSortBy)).isChecked()){
            SortByOption option = (SortByOption) ((Spinner) findViewById(R.id.spinnerSortBy)).getSelectedItem();
            searchBuilder = searchBuilder.withSortOptionKey(option.getKey());
        }

        if(((CheckBox) findViewById(R.id.checkBusinessType)).isChecked()){
            BusinessTypeOption option = (BusinessTypeOption) ((Spinner) findViewById(R.id.spinnerBusinessType)).getSelectedItem();
            searchBuilder = searchBuilder.withBusinessTypeId(String.valueOf(option.getId()));
        }

        if(((CheckBox) findViewById(R.id.checkRating)).isChecked()){
            RatingBar option = findViewById(R.id.ratingBar);
            searchBuilder = searchBuilder.withRatingKey(String.valueOf((int) option.getRating()));
        }

        if(((CheckBox) findViewById(R.id.checkRegion)).isChecked()){
            AuthoritiesOption option = (AuthoritiesOption) ((Spinner) findViewById(R.id.spinnerLocalAuthority)).getSelectedItem();
            searchBuilder = searchBuilder.withLocalAuthorityId(String.valueOf(option.getId()));
        }

        if(((CheckBox) findViewById(R.id.checkRadius)).isChecked()){
            searchBuilder = searchBuilder.withMaxDistanceLimit(String.valueOf(((SeekBar) findViewById(R.id.seekRadius)).getProgress()));
        }

        SearchDetails searchDetails = searchBuilder
                .withPageSize("50")
                .build();

        Search.search(this, requestQueue, searchDetails);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        boolean success = false;
        for (int i = 0; i < permissions.length; i++) {
            if (permissions[i].equals(Manifest.permission.ACCESS_FINE_LOCATION)) {
                success = (grantResults[i] == PackageManager.PERMISSION_GRANTED);
                break;
            }
        }

        if (success) {
            switch (requestCode) {
                case 100:
                    localSearch();
                    break;
                case 101:
                    simpleSearch();
                    break;
                case 102:
                    advancedSearch();
            }
        } else {
            Toast.makeText(this, "Location permissions required for local search.", Toast.LENGTH_SHORT).show();
        }
    }

    public void toggleRefinements(View view) {
        if (advanced) {
            findViewById(R.id.advancedContainer).setVisibility(ConstraintLayout.GONE);
            advanced = false;
        } else {
            findViewById(R.id.advancedContainer).setVisibility(ConstraintLayout.VISIBLE);
            advanced = true;
        }
    }

    private void populateSpinners() {
        populateSortBy();
        populateBusinessTypes();
        populateRegions();
    }

    public void populateSortBy() {
        final String url = "http://api.ratings.food.gov.uk/SortOptions";
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray options = response.getJSONArray("sortOptions");
                            List<SortByOption> things = new ArrayList<>();
                            for (int i = 0; i < options.length(); i++) {
                                JSONObject option = options.getJSONObject(i);
                                things.add(new SortByOption(
                                        option.getString("sortOptionName"),
                                        option.getString("sortOptionKey"),
                                        option.getInt("sortOptionId")));

                                ArrayAdapter<SortByOption> adapter = new ArrayAdapter<>(
                                        getApplicationContext(), android.R.layout.simple_spinner_item, things);

                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                                ((Spinner) findViewById(R.id.spinnerSortBy)).setAdapter(adapter);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.err.println("Failed to get SortBy");
                        error.printStackTrace();
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> existingHeaders = super.getHeaders();
                HashMap<String, String> newHeaders = new HashMap<>(existingHeaders);
                newHeaders.put("x-api-version", "2");
                return newHeaders;
            }
        };
        requestQueue.add(getRequest);
    }

    private void populateBusinessTypes() {
        final String url = "http://api.ratings.food.gov.uk/BusinessTypes";
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray options = response.getJSONArray("businessTypes");
                            List<BusinessTypeOption> things = new ArrayList<>();
                            for (int i = 0; i < options.length(); i++) {
                                JSONObject option = options.getJSONObject(i);
                                things.add(new BusinessTypeOption(
                                        option.getString("BusinessTypeName"),
                                        option.getInt("BusinessTypeId")));

                                ArrayAdapter<BusinessTypeOption> adapter = new ArrayAdapter<>(
                                        getApplicationContext(), android.R.layout.simple_spinner_item, things);

                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                                ((Spinner) findViewById(R.id.spinnerBusinessType)).setAdapter(adapter);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.err.println("Failed to get Business Types");
                        error.printStackTrace();
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> existingHeaders = super.getHeaders();
                HashMap<String, String> newHeaders = new HashMap<>(existingHeaders);
                newHeaders.put("x-api-version", "2");
                return newHeaders;
            }
        };
        requestQueue.add(getRequest);
    }

    private void populateRegions() {
        final String url = "http://api.ratings.food.gov.uk/Regions";
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray options = response.getJSONArray("regions");
                            List<RegionsOption> things = new ArrayList<>();
                            for (int i = 0; i < options.length(); i++) {
                                JSONObject option = options.getJSONObject(i);
                                things.add(new RegionsOption(
                                        option.getString("name"),
                                        option.getString("nameKey"),
                                        option.getInt("id")));

                                ArrayAdapter<RegionsOption> adapter = new ArrayAdapter<>(
                                        getApplicationContext(), android.R.layout.simple_spinner_item, things);

                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                                ((Spinner) findViewById(R.id.spinnerRegion)).setAdapter(adapter);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.err.println("Failed to get Region Types");
                        error.printStackTrace();
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> existingHeaders = super.getHeaders();
                HashMap<String, String> newHeaders = new HashMap<>(existingHeaders);
                newHeaders.put("x-api-version", "2");
                return newHeaders;
            }
        };
        requestQueue.add(getRequest);

        ((Spinner) findViewById(R.id.spinnerRegion)).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                populateAuthorities(((RegionsOption) adapterView.getSelectedItem()).getNameKey());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void populateAuthorities(final String nameKey) {
        final String url = "http://api.ratings.food.gov.uk/Authorities";
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray options = response.getJSONArray("authorities");
                            List<AuthoritiesOption> things = new ArrayList<>();
                            for (int i = 0; i < options.length(); i++) {
                                JSONObject option = options.getJSONObject(i);
                                if (option.getString("RegionName").equals(nameKey)) {
                                    things.add(new AuthoritiesOption(
                                            option.getString("Name"),
                                            option.getInt("LocalAuthorityId")
                                    ));
                                }

                                ArrayAdapter<AuthoritiesOption> adapter = new ArrayAdapter<>(
                                        getApplicationContext(), android.R.layout.simple_spinner_item, things);

                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                                ((Spinner) findViewById(R.id.spinnerLocalAuthority)).setAdapter(adapter);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.err.println("Failed to get Local Authorities Types");
                        error.printStackTrace();
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> existingHeaders = super.getHeaders();
                HashMap<String, String> newHeaders = new HashMap<>(existingHeaders);
                newHeaders.put("x-api-version", "2");
                return newHeaders;
            }
        };
        requestQueue.add(getRequest);
    }
}
