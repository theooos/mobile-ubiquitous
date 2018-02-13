package dev.theodo.red.stopwatch;

import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StopWatchActivity extends AppCompatActivity {

    String SECONDS_KEY = "seconds";
    String RUNNING_KEY = "running";
    String WAS_RUNNING_KEY = "wasRunning";

    int seconds;
    boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_watch);

        if (savedInstanceState != null){
            seconds = savedInstanceState.getInt(SECONDS_KEY);
            running = savedInstanceState.getBoolean(RUNNING_KEY);
        }
        else {
            seconds = 0;
            running = false;
        }

        runTimer();
        updateButtons();
    }

    @Override
    public void onStart() {
        super.onStart();
        updateButtons();
    }

    @Override
    public void onStop() {
        super.onStop();
        running = false;
        updateButtons();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(SECONDS_KEY, seconds);
        savedInstanceState.putBoolean(RUNNING_KEY, running);
    }

    public void runTimer(){
        final TextView timeView = findViewById(R.id.time);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int mins = (seconds%3600)/60;
                int secs = seconds%60;
                String time = String.format("%d:%02d:%02d", hours, mins, secs);
                timeView.setText(time);
                if(running){
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }

    private void updateButtons() {
        Button start = findViewById(R.id.start_button);
        Button stop = findViewById(R.id.stop_button);
        start.setEnabled(!running);
        stop.setEnabled(running);

        if(running){
            start.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.disabledButtonColour));
            stop.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.stopButtonColour));
        } else {
            start.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.startButtonColour));
            stop.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.disabledButtonColour));
        }
    }

    public void startTimer(View view){
        running = true;
        updateButtons();
    }


    public void stopTimer(View view){
        running = false;
        updateButtons();
    }

    public void resetTimer(View view){
        seconds = 0;
        running = false;
        updateButtons();
    }
}
