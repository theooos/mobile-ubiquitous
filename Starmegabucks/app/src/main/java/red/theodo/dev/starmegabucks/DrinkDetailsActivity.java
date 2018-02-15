package red.theodo.dev.starmegabucks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class DrinkDetailsActivity extends AppCompatActivity {

    private int apiID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_details);
        apiID = getIntent().getIntExtra("apiID", 0);
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET,
                "https://www.sjjg.uk/coffee-shop/getProductDetails?id=" + apiID, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            ((TextView) findViewById(R.id.name)).setText(response.getString("name"));
                            ((TextView) findViewById(R.id.type)).setText(response.getString("type"));
                            ((TextView) findViewById(R.id.description)).setText(response.getString("description"));
                        } catch (JSONException ignored) {
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {}
                });
        requestQueue.add(getRequest);
    }


}
