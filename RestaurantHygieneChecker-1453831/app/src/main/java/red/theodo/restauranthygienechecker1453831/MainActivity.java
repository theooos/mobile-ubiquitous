package red.theodo.restauranthygienechecker1453831;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.DragEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

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

    public static String SEARCH_STRING = "search";

    private boolean advanced = false;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void performSearch(View view) {
        Intent intent = new Intent(this, ResultsActivity.class);
        String query = ((EditText)findViewById(R.id.search_text)).getText().toString();

        // TODO Get the search results
//        intent.putExtra(SEARCH_STRING, searchDetails);

        startActivity(intent);
    }

    public void toggleRefinements(View view){
        if(advanced){
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

    public void populateSortBy(){
        final String url = "http://api.ratings.food.gov.uk/SortOptions";
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray options = response.getJSONArray("sortOptions");
                            List<SortByOption> things = new ArrayList<>();
                            for(int i = 0; i < options.length(); i++){
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

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return super.getParams();
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
                            for(int i = 0; i < options.length(); i++){
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

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return super.getParams();
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
                            for(int i = 0; i < options.length(); i++){
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

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return super.getParams();
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

    private void populateAuthorities(final String nameKey){
        final String url = "http://api.ratings.food.gov.uk/Authorities";
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray options = response.getJSONArray("authorities");
                            List<AuthoritiesOption> things = new ArrayList<>();
                            for(int i = 0; i < options.length(); i++){
                                JSONObject option = options.getJSONObject(i);
                                if(option.getString("RegionName").equals(nameKey)){
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
