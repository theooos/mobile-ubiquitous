package red.theodo.dev.snookertracker_1453831;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
        if(p1.length() == 0){
            alertInvalidName(1);
            return;
        }
        String p2 = ((TextView) findViewById(R.id.player2)).getText().toString();
        if(p2.length() == 0){
            alertInvalidName(2);
            return;
        }
        String p3 = ((TextView) findViewById(R.id.player3)).getText().toString();
        if(p3.length() == 0){
            alertInvalidName(3);
            return;
        }
        String p4 = ((TextView) findViewById(R.id.player4)).getText().toString();
        if(p4.length() == 0){
            alertInvalidName(4);
            return;
        }

        Intent intent = new Intent(this, ScoringActivity.class);
        intent.putExtra("p1", p1);
        intent.putExtra("p2", p2);
        intent.putExtra("p3", p3);
        intent.putExtra("p4", p4);

        startActivity(intent);
    }

    private void alertInvalidName(int player){
        AlertDialog alertDialog = new AlertDialog.Builder(MenuActivity.this).create();
        alertDialog.setTitle("Invalid name!");
        alertDialog.setMessage("Please provide a name for player " + player + ".");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
}
