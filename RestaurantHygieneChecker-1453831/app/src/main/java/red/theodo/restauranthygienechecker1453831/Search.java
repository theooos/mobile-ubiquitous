package red.theodo.restauranthygienechecker1453831;

import android.content.Context;
import android.content.Intent;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by theo on 08/03/18.
 */

class Search {

    static void search(final Context context, RequestQueue requestQueue, SearchDetails searchDetails) {
        final String url = searchDetails.generateUrl();
        System.out.println(url);
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray options = response.getJSONArray("establishments");
                            ArrayList<Establishment> things = new ArrayList<>();
                            for (int i = 0; i < options.length(); i++) {
                                JSONObject option = options.getJSONObject(i);

                                EstablishmentBuilder establishmentBuilder = EstablishmentBuilder.anEstablishment()
                                        .withName(option.getString("BusinessName"))
                                        .withBusinessType(option.getString("BusinessType"))
                                        .withLocalAuthorityName(option.getString("LocalAuthorityName"))
                                        .withLocalAuthorityEmailAddress(option.getString("LocalAuthorityEmailAddress"))
                                        .withRating(option.getString("RatingValue"));

                                StringBuilder address = new StringBuilder();
                                for (int j = 1; j < 5; j++) {
                                    String line = option.getString("AddressLine" + j);
                                    if (line.length() > 0) {
                                        address.append(line).append("\n");
                                    }
                                }
                                address.append(option.getString("PostCode"));

                                establishmentBuilder = establishmentBuilder.withAddress(address.toString());

                                if(!option.isNull("RatingDate")){
                                    DateFormat format = new SimpleDateFormat("yyyy-mm-dd");
                                    Date date = new Date(0);
                                    try {
                                        date = format.parse(option.getString("RatingDate").split("T")[0]);
                                    } catch (ParseException e) {
                                        System.err.println("Failed to parse time");
                                        e.printStackTrace();
                                    }
                                    establishmentBuilder = establishmentBuilder
                                            .withRatingDate(date.getTime());
                                }

                                JSONObject lonlat = option.getJSONObject("geocode");
                                if(!lonlat.isNull("longitude") && !lonlat.isNull("latitude")){
                                    establishmentBuilder = establishmentBuilder
                                            .withLongLat(lonlat.getDouble("longitude"), lonlat.getDouble("latitude"));
                                }

                                things.add(establishmentBuilder.build());
                            }

                            Intent intent = new Intent(context, ResultsActivity.class);
                            intent.putParcelableArrayListExtra(ResultsActivity.RESULTS_STRING, things);

                            context.startActivity(intent);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.err.println("Failed to get Establishments");
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
