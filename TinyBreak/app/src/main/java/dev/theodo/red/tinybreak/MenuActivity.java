package dev.theodo.red.tinybreak;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void playGame(View view){
        String p1 = ((TextView) findViewById(R.id.player1)).getText().toString();
        String p2 = ((TextView) findViewById(R.id.player2)).getText().toString();
        String p3 = ((TextView) findViewById(R.id.player3)).getText().toString();
        String p4 = ((TextView) findViewById(R.id.player4)).getText().toString();

        Intent intent = new Intent(this, ScoringActivity.class);
        intent.putExtra("p1", p1);
        intent.putExtra("p2", p2);
        intent.putExtra("p3", p3);
        intent.putExtra("p4", p4);

        startActivity(intent);
    }
}
