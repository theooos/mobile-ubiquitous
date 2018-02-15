package red.theodo.dev.starmegabucks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TopLevelActivity extends AppCompatActivity {

    private ArrayList<CafeProduct> products = new ArrayList<>();
    private ArrayAdapter productsAdpt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_level);
        productsAdpt = new ArrayAdapter(this, android.R.layout.simple_selectable_list_item, products);
        ListView listView = findViewById(R.id.productList);
        listView.setAdapter(productsAdpt);

        final AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CafeProduct clickedItem = (CafeProduct) productsAdpt.getItem(i);
                Intent intent = new Intent(TopLevelActivity.this, DrinkDetailsActivity.class);
                intent.putExtra("apiID", clickedItem.getApiID());
                startActivity(intent);
            }
        };
        listView.setOnItemClickListener(itemClickListener);
    }

    public void onRequestProducts(View view){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        final String allProducts = "https://www.sjjg.uk/coffee-shop/getAllProducts";
        JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, allProducts, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        populateList(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {}
                });
        requestQueue.add(getRequest);
    }

    void populateList(JSONArray items){
        products.clear();
        try{
            for(int i=0; i < items.length(); i++){
                JSONObject jo = items.getJSONObject(i);
                products.add(new CafeProduct(jo.getString("name"), jo.getInt("id")));
            }
        } catch (JSONException err){}
        productsAdpt.notifyDataSetChanged();
    }
}
