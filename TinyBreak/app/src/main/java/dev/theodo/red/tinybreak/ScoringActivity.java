package dev.theodo.red.tinybreak;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ScoringActivity extends AppCompatActivity {

    Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoring);
        Intent intent = getIntent();
        String p1 = intent.getStringExtra("p1");
        String p2 = intent.getStringExtra("p2");
        String p3 = intent.getStringExtra("p3");
        String p4 = intent.getStringExtra("p4");

        game = new Game(p1, p2, p3, p4);
    }
}
