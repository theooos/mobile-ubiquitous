package dev.theodo.red.stopwatch;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class StopWatchActivity extends AppCompatActivity {

    int seconds = 0;
    boolean running = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_watch);
    }

    public void runTimer(View view){
        final TextView timeView = (TextView) findViewById(R.id.time);
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int mins = (seconds%3600)/60;
                int secs = seconds%60;
                String time = String.format("%d:%d:%d", hours, mins, secs);
                timeView.setText(time);
                
            }
        });
    }

    public void startTimer(View view){
        running = true;
        runTimer(view);
    }

    public void stopTimer(View view){
        running = false;
    }

    public void resetTimer(View view){
        running = false;
        seconds = 0;
    }
}
