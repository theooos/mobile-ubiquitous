package red.theodo.restauranthygienechecker1453831;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    public static String ESTABLISHMENT_KEY = "establishment";

    private Establishment establishment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        establishment = getIntent().getParcelableExtra(ESTABLISHMENT_KEY);

        ((TextView) findViewById(R.id.detailsName)).setText(establishment.getName());
        ((TextView) findViewById(R.id.detailsRating)).setText(establishment.getRating());
        ((TextView) findViewById(R.id.detailsBusinessType)).setText(establishment.getBusinessType());
        ((TextView) findViewById(R.id.detailsAddress)).setText(establishment.getAddress());
        ((TextView) findViewById(R.id.detailsAuthority)).setText(establishment.getLocalAuthorityName());
        ((TextView) findViewById(R.id.detailsAuthorityEmail)).setText(establishment.getLocalAuthorityEmailAddress());
        ((TextView) findViewById(R.id.detailsName)).setText(establishment.getName());
        ((TextView) findViewById(R.id.detailsLongLat)).setText(String.format("(%s, %s)", establishment.getLongitude(), establishment.getLatitude()));
    }


}
