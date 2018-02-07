package dev.theodo.red.tinybreak;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
        if (!game.isFinished()){
            game.nextPlayer();
            updateScores();
            updatePlayerSelection();
        }
    }

    public void foul(View view){
        if (!game.isFinished()) {
            game.foul();
            updateScores();
            updatePlayerSelection();
        }
    }

    public void endFrame(View view){
        String best_team = game.getWinningTeam();
        String best_player = game.getBestPlayers();

        AlertDialog alertDialog = new AlertDialog.Builder(ScoringActivity.this).create();
        alertDialog.setTitle("Game over!");
        alertDialog.setMessage(best_team + "\n" + best_player + "\nPlay again?");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        game = new Game(game.getPlayer1().getName(), game.getPlayer2().getName(),
                                game.getPlayer3().getName(), game.getPlayer4().getName());
                        updateScores();
                        updatePlayerSelection();
                        dialog.dismiss();
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    private void updateScores(){
        ((TextView) findViewById(R.id.p1Score)).setText(String.valueOf(game.getPlayer1().getScore()));
        ((TextView) findViewById(R.id.p2Score)).setText(String.valueOf(game.getPlayer2().getScore()));
        ((TextView) findViewById(R.id.p3Score)).setText(String.valueOf(game.getPlayer3().getScore()));
        ((TextView) findViewById(R.id.p4Score)).setText(String.valueOf(game.getPlayer4().getScore()));
        ((TextView) findViewById(R.id.team1Score)).setText(String.valueOf(game.getTeam1Score()));
        ((TextView) findViewById(R.id.team2Score)).setText(String.valueOf(game.getTeam2Score()));

        if (game.isFinished()) endFrame(null);
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
        if (!game.isFinished()) {
            game.potBall(Balls.Red);
            updateScores();
        }
    }

    public void yellow(View view){
        if (!game.isFinished()) {
            game.potBall(Balls.Yellow);
            updateScores();
        }
    }

    public void green(View view){
        if (!game.isFinished()) {
            game.potBall(Balls.Green);
            updateScores();
        }
    }

    public void brown(View view){
        if (!game.isFinished()) {
            game.potBall(Balls.Brown);
            updateScores();
        }
    }

    public void blue(View view){
        if (!game.isFinished()) {
            game.potBall(Balls.Blue);
            updateScores();
        }
    }

    public void pink(View view){
        if (!game.isFinished()) {
            game.potBall(Balls.Pink);
            updateScores();
        }
    }

    public void black(View view){
        if (!game.isFinished()) {
            game.potBall(Balls.Black);
            updateScores();
        }
    }
}
