package ru.matbrain.trainingapp;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by danilzdanov on 24.05.17.
 */

public class JumpingRopeActivity extends AppCompatActivity {

    private static final String TAG = "JumpingRopeActivity";
    private static final String SECONDS_KEY = "SECONDS_KEY";
    private static final String RUNNING_KEY = "RUNNING_KEY";
    private static final String COUNT_KEY = "COUNT_KEY";
    private static final String REPEAT_COUNT_KEY = "REPEAT_COUNT_KEY";
    int seconds = 0;
    boolean isRunning;
    boolean wasRunning;
    private ImageButton minusButton;
    private ImageButton plusButton;
    private TextView jumpingRopeCountTV;
    private int jumpingRopeCount = 0;
    private Button hrefButton;
    private Button exerciseDoneButton;
    //    Поля таймера
    private ImageButton startButton;
    private ImageButton pauseButton;
    private Button resetButton;
    private TextView ClockTV;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jumping_rope);
        Log.i(TAG, "Called method onCreate");

        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt(SECONDS_KEY);
            isRunning = savedInstanceState.getBoolean(RUNNING_KEY);
            jumpingRopeCount = savedInstanceState.getInt(REPEAT_COUNT_KEY);
        }

        initUI();
        runTimer();

        if (getIntent() != null) {
            jumpingRopeCount = getIntent().getIntExtra(MainActivity.JUMPING_ROPE_KEY, 0);
        }
        jumpingRopeCountTV.setText(Integer.toString(jumpingRopeCount));
        setHrefButtonBehavior();

    }

    //    Сохраняем состояние счетчика повторений и таймера при изменении состояния жизненного цикла активности
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.i(TAG, "Called method onSaveInstanceState");
        outState.putInt(SECONDS_KEY, seconds);
        outState.putBoolean(RUNNING_KEY, isRunning);
        outState.putInt(COUNT_KEY, jumpingRopeCount);
//        outState.putInt();
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onStart() {
        Log.i(TAG, "Called method onStart");

        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.i(TAG, "Called method onResume");

        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.i(TAG, "Called method onPause");

        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.i(TAG, "Called method onStop");

        super.onStop();
    }

    @Override
    protected void onRestart() {
        Log.i(TAG, "Called method onRestart");

        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, "Called method onDestroy");

        super.onDestroy();
    }

    private void initUI() {
        minusButton = (ImageButton) findViewById(R.id.button_minus);
        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (jumpingRopeCount >= 1) {
                    jumpingRopeCount--;
                    jumpingRopeCountTV.setText(String.valueOf(jumpingRopeCount));
                } else {
                    Toast.makeText(JumpingRopeActivity.this, "Can't reduce less than the current value", Toast.LENGTH_SHORT).show();
                }
            }
        });

        plusButton = (ImageButton) findViewById(R.id.button_plus);
        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpingRopeCount++;
                jumpingRopeCountTV.setText(String.valueOf(jumpingRopeCount));
            }
        });
        jumpingRopeCountTV = (TextView) findViewById(R.id.repeats_count);

        hrefButton = (Button) findViewById(R.id.href_jumping_rope);
        exerciseDoneButton = (Button) findViewById(R.id.button_exercise_done);

        setExerciseDoneButtonBehavior();

        startButton = (ImageButton) findViewById(R.id.start_button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRunning = true;
            }
        });
        pauseButton = (ImageButton) findViewById(R.id.pause_button);
        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRunning = false;
            }
        });
        resetButton = (Button) findViewById(R.id.reset_button);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isRunning) {
                    isRunning = false;
                    seconds = 0;
                }
            }
        });
        ClockTV = (TextView) findViewById(R.id.clock_text_view);


    }

    @Override
    public void onBackPressed() {
        Intent jumpingRopeIntent = new Intent();
        jumpingRopeIntent.putExtra(MainActivity.JUMPING_ROPE_KEY, jumpingRopeCount);
        setResult(RESULT_CANCELED, jumpingRopeIntent);
        super.onBackPressed();
    }

    private void setExerciseDoneButtonBehavior() {
        exerciseDoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent jumpingRopeIntent = new Intent();
                jumpingRopeIntent.putExtra(MainActivity.JUMPING_ROPE_KEY, jumpingRopeCount);
                setResult(RESULT_OK, jumpingRopeIntent);
                finish();
            }
        });
    }

    private void setHrefButtonBehavior() {
        hrefButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri href = Uri.parse("http://www.aif.ru/health/secrets/trenirovka_so_skakalkoy_kak_prygat_pravilno");
                Intent hrefIntent = new Intent(Intent.ACTION_VIEW, href);
                Intent appChooser = new Intent().createChooser(hrefIntent, getString(R.string.app_chooser));
                if (isAppInstalled("com.android.chrome")) {
                    startActivity(appChooser);
                } else {
                    Toast.makeText(JumpingRopeActivity.this, R.string.chrome_browser_needed, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean isAppInstalled(String targetPackage) {
        List<ApplicationInfo> packages;
        PackageManager pm = getPackageManager();
        packages = pm.getInstalledApplications(0);
        for (ApplicationInfo packageInfo : packages) {
            if (packageInfo.packageName.equals(targetPackage)) return true;
        }
        return false;
    }

    private void runTimer() {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override

            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds / 3600) % 60;
                int secs = seconds % 60;

                String hoursStr = hours > 9 ? String.valueOf(hours) : String.valueOf("0" + hours);
                String minutesStr = minutes > 9 ? String.valueOf(minutes) : String.valueOf("0" + minutes);
                String secsStr = secs > 9 ? String.valueOf(secs) : String.valueOf("0" + secs);

                String time = hoursStr + ":" + minutesStr + ":" + secsStr;
                ClockTV.setText(time);
                if (isRunning) {
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }
}
