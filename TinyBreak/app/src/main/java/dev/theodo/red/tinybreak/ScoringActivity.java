package dev.theodo.red.tinybreak;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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

        ((TextView) findViewById(R.id.p1Name)).setText(p1);
        ((TextView) findViewById(R.id.p2Name)).setText(p2);
        ((TextView) findViewById(R.id.p3Name)).setText(p3);
        ((TextView) findViewById(R.id.p4Name)).setText(p4);

        game = new Game(p1, p2, p3, p4);
    }

    public void nextPlayer(View view){
        game.nextPlayer();
        updateScores();
        updatePlayerSelection();
    }

    public void foul(View view){
        game.foul();
        updateScores();
        updatePlayerSelection();
    }

    public void endFrame(View view){
        game.endFrame();
    }

    private void updateScores(){
        ((TextView) findViewById(R.id.p1Score)).setText(String.valueOf(game.getPlayer1Score()));
        ((TextView) findViewById(R.id.p2Score)).setText(String.valueOf(game.getPlayer2Score()));
        ((TextView) findViewById(R.id.p3Score)).setText(String.valueOf(game.getPlayer3Score()));
        ((TextView) findViewById(R.id.p4Score)).setText(String.valueOf(game.getPlayer4Score()));
        ((TextView) findViewById(R.id.team1Score)).setText(String.valueOf(game.getTeam1Score()));
        ((TextView) findViewById(R.id.team2Score)).setText(String.valueOf(game.getTeam2Score()));
    }

    private void updatePlayerSelection(){
        switch (game.getCurrentPlayer()){
            case 1:
                findViewById(R.id.p1Name).setBackgroundResource(R.drawable.current_player_border);
                findViewById(R.id.p1Score).setBackgroundResource(R.drawable.current_player_border);
                findViewById(R.id.p4Name).setBackgroundResource(R.drawable.border);
                findViewById(R.id.p4Score).setBackgroundResource(R.drawable.border);
                break;
            case 2:
                findViewById(R.id.p2Name).setBackgroundResource(R.drawable.current_player_border);
                findViewById(R.id.p2Score).setBackgroundResource(R.drawable.current_player_border);
                findViewById(R.id.p3Name).setBackgroundResource(R.drawable.border);
                findViewById(R.id.p3Score).setBackgroundResource(R.drawable.border);
                break;
            case 3:
                findViewById(R.id.p3Name).setBackgroundResource(R.drawable.current_player_border);
                findViewById(R.id.p3Score).setBackgroundResource(R.drawable.current_player_border);
                findViewById(R.id.p1Name).setBackgroundResource(R.drawable.border);
                findViewById(R.id.p1Score).setBackgroundResource(R.drawable.border);
                break;
            case 4:
                findViewById(R.id.p4Name).setBackgroundResource(R.drawable.current_player_border);
                findViewById(R.id.p4Score).setBackgroundResource(R.drawable.current_player_border);
                findViewById(R.id.p2Name).setBackgroundResource(R.drawable.border);
                findViewById(R.id.p2Score).setBackgroundResource(R.drawable.border);
        }
    }

    // BALLS

    public void red(View view){
        game.potBall(Balls.Red);
        updateScores();
    }

    public void yellow(View view){
        game.potBall(Balls.Yellow);
        updateScores();
    }

    public void green(View view){
        game.potBall(Balls.Green);
        updateScores();
    }

    public void brown(View view){
        game.potBall(Balls.Brown);
        updateScores();
    }

    public void blue(View view){
        game.potBall(Balls.Blue);
        updateScores();
    }

    public void pink(View view){
        game.potBall(Balls.Pink);
        updateScores();
    }

    public void black(View view){
        game.potBall(Balls.Black);
        updateScores();
    }
}
