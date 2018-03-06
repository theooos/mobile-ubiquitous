package red.theodo.restauranthygienechecker1453831;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static String SEARCH_STRING = "search";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void performSearch(View view) {
        Intent intent = new Intent(this, ResultsActivity.class);
        EditText editText = findViewById(R.id.search_text);
        String message = editText.getText().toString();
        SearchDetails searchDetails = new SearchBuilder().query(message).build();

        intent.putExtra(SEARCH_STRING, searchDetails);

        startActivity(intent);
    }
}
