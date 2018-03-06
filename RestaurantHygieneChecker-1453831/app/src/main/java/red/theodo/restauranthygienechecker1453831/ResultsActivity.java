package red.theodo.restauranthygienechecker1453831;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

public class ResultsActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            ArrayList<Establishment> searchResults = new ArrayList<>();
            searchResults.add(new Establishment("Roosters", "4"));
            searchResults.add(new Establishment("Chicken.com", "4"));
            searchResults.add(new Establishment("Country Girl", "4"));

            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_list:
                    mTextMessage.setText(R.string.title_list);
                    selectedFragment = ListFragment.newInstance(searchResults);
                    break;
                case R.id.navigation_map:
                    mTextMessage.setText(R.string.title_map);
                    selectedFragment = ListFragment.newInstance(searchResults);
                    break;
            }
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.listMapFragment, selectedFragment);
            transaction.commit();
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
