package com.zhirova.alina.lesson4;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String SECOND_SAVE = "seconds";
    private static final String IS_RUNNING_SAVE = "isRunning";

    private int seconds = 0;
    private boolean isRunning;
    private boolean wasRunning;

    private Button resetButton;
    private Button startButton;
    private Button stopButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate is called!");

        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt(SECOND_SAVE);
            isRunning = savedInstanceState.getBoolean(IS_RUNNING_SAVE);
        }

        resetButton = findViewById(R.id.reset_button);
        startButton = findViewById(R.id.start_button);
        stopButton = findViewById(R.id.stop_button);

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isRunning = false;
                seconds = 0;
            }
        });

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isRunning = true;
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isRunning = false;
            }
        });

        runTimer();
    }


    private void runTimer(){
        final TextView timeView = findViewById(R.id.textViewTime);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;
                String time = String.format("%d:%02d:%02d", hours, minutes, secs);
                timeView.setText(time);
                if (isRunning) {
                    seconds++;
                }
                handler.postDelayed(this, 1000L);
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart is called!");
        if (wasRunning) {
            isRunning = true;
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume is called!");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause is called!");
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(SECOND_SAVE, seconds);
        savedInstanceState.putBoolean(IS_RUNNING_SAVE, isRunning);
    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop is called!");
        wasRunning = isRunning;
        isRunning = false;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy is called!");
    }


}
