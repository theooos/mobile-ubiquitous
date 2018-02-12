package dev.theodo.red.stopwatch;

import android.graphics.PorterDuff;
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

    int seconds;
    boolean running;
    boolean wasRunning = false;

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

        // Changing the colour using the XML defined colours.
//        findViewById(R.id.start_button).setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.startButtonColor));

        // Changing the colour to something previously undefined.
//        ((Button)findViewById(R.id.stop_button)).getBackground().setColorFilter(0xFFFF6666, PorterDuff.Mode.MULTIPLY);
    }

    @Override
    public void onStart() {
        super.onStart();
        running = wasRunning;
    }

    @Override
    public void onStop() {
        super.onStop();
        wasRunning = running;
        running = false;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBoolean(RUNNING_KEY, running);
        savedInstanceState.putInt(SECONDS_KEY, seconds);
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

    public void startTimer(View view){
        running = true;
        findViewById(R.id.start_button).setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.disabledButtonColour));
    }

    public void stopTimer(View view){
        running = false;
    }

    public void resetTimer(View view){
        running = false;
        seconds = 0;
    }
}
