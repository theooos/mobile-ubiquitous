package red.theodo.restauranthygienechecker1453831;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

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

import red.theodo.restauranthygienechecker1453831.ResponseObjects.SortByOptions;

public class MainActivity extends AppCompatActivity {

    public static String SEARCH_STRING = "search";

    private boolean advanced = false;
    private RequestQueue requestQueue;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.advancedContainer).setVisibility(ConstraintLayout.GONE);
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        populateSpinners();
    }

    @Override
    protected void onStart() {
        super.onStart();
        populateSpinners();
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
                            List<SortByOptions> things = new ArrayList<>();
                            for(int i = 0; i < options.length(); i++){
                                JSONObject option = options.getJSONObject(i);
                                things.add(new SortByOptions(
                                        option.getString("sortOptionName"),
                                        option.getString("sortOptionKey"),
                                        option.getInt("sortOptionId")));

                                ArrayAdapter<SortByOptions> adapter = new ArrayAdapter<>(
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
        requestQueue.start();
    }

    private void populateBusinessTypes() {

    }

    private void populateRegions() {

    }
}
