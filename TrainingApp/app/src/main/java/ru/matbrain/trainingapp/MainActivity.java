package ru.matbrain.trainingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static android.view.View.GONE;
import static ru.matbrain.trainingapp.R.id.dumbbell_swings_card_view;
import static ru.matbrain.trainingapp.R.id.dumbbell_swings_description_text_view;
import static ru.matbrain.trainingapp.R.id.dumbbell_swings_description_view;
import static ru.matbrain.trainingapp.R.id.jumping_rope_card_view;
import static ru.matbrain.trainingapp.R.id.jumping_rope_description_text_view;
import static ru.matbrain.trainingapp.R.id.jumping_rope_description_view;
import static ru.matbrain.trainingapp.R.id.running_card_description_text_view;
import static ru.matbrain.trainingapp.R.id.running_card_description_view;
import static ru.matbrain.trainingapp.R.id.running_card_view;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    public static final String JUMPING_ROPE_KEY = "JUMPING_ROPE_COUNT_KEY";
    public static final int JUMPING_ROPE_REQUEST_CODE = 100;

    private CardView runningCV;
    private CardView jumping_ropeCV;
    private CardView dumbbell_swingsCV;

    private CardView runningDescriptionCV;
    private CardView jumpingRopeDescriptionCV;
    private CardView dumbbellSwingsDescriptionCV;

    private TextView runningDescriptionTV;
    private TextView jumpingRopeDescriptionTV;
    private TextView dumbbellSwingsDescriptionTV;

    private TextView jumpingRopeCountTV;

    private Button goToExerciseJumpingRope;

    private int jumpingRopeCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
        setRunningClickBehavior();
        setJumpingRopeClickBehavior();
        setDumbbellSwingsClickBehavior();
        setJumpingRopeGoToExerciseBehavior();

        jumpingRopeSetLastRepeatCount();
    }

    private void jumpingRopeSetLastRepeatCount() {
        String jumpingRopeRepeatCount = getResources().getString(R.string.number_of_repeats);
        jumpingRopeRepeatCount += " " + jumpingRopeCount;
        jumpingRopeCountTV.setText(jumpingRopeRepeatCount);
    }

    private void initUI(){
        runningCV = (CardView) findViewById(running_card_view);
        jumping_ropeCV = (CardView) findViewById(jumping_rope_card_view);
        dumbbell_swingsCV = (CardView) findViewById(dumbbell_swings_card_view);

        runningDescriptionCV = (CardView) findViewById(running_card_description_view);
        jumpingRopeDescriptionCV = (CardView) findViewById(jumping_rope_description_view);
        dumbbellSwingsDescriptionCV = (CardView) findViewById(dumbbell_swings_description_view);

        runningDescriptionTV = (TextView) findViewById(running_card_description_text_view);
        jumpingRopeDescriptionTV = (TextView) findViewById(jumping_rope_description_text_view);
        dumbbellSwingsDescriptionTV = (TextView) findViewById(dumbbell_swings_description_text_view);

        jumpingRopeCountTV = (TextView) findViewById(R.id.jumping_rope_number_of_repeats);

        goToExerciseJumpingRope = (Button) findViewById(R.id.go_to_exercise_jumping_rope);

        runningDescriptionCV.setVisibility(GONE);
        jumpingRopeDescriptionCV.setVisibility(GONE);
        dumbbellSwingsDescriptionCV.setVisibility(GONE);

    }

    private void setRunningClickBehavior(){
        runningCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (runningDescriptionCV.getVisibility() != View.VISIBLE){
                    Log.i(TAG, "Running pressed, runningDescriptionCV is turning VISIBLE");
                    runningDescriptionCV.setVisibility(View.VISIBLE);
                    runningDescriptionTV.setText(R.string.running_description);
                    Toast.makeText(MainActivity.this, "Running pressed", Toast.LENGTH_SHORT).show();
                }
                else {
                    Log.i(TAG, "Running pressed, runningDescriptionCV is turning GONE");
                    runningDescriptionCV.setVisibility(GONE);
                    Toast.makeText(MainActivity.this, "Running pressed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setJumpingRopeClickBehavior(){
        jumping_ropeCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (jumpingRopeDescriptionCV.getVisibility() != View.VISIBLE){
                    Log.i(TAG, "JumpingRope pressed, jumpingRopeDescriptionCV is turning VISIBLE");
                    jumpingRopeDescriptionCV.setVisibility(View.VISIBLE);
                    jumpingRopeDescriptionTV.setText(R.string.jumping_rope_description);
                    Toast.makeText(MainActivity.this, "JumpingRope pressed", Toast.LENGTH_SHORT).show();

                }
                else {
                    Log.i(TAG, "JumpingRope pressed, jumpingRopeDescriptionCV is turning GONE");
                    jumpingRopeDescriptionCV.setVisibility(GONE);
                    Toast.makeText(MainActivity.this, "JumpingRope pressed", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    private void setDumbbellSwingsClickBehavior(){
        dumbbell_swingsCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dumbbellSwingsDescriptionCV.getVisibility() != View.VISIBLE){
                    Log.i(TAG, "DumbbellSwings pressed, dumbbellSwingsDescriptionCV is turning VISIBLE");
                    dumbbellSwingsDescriptionCV.setVisibility(View.VISIBLE);
                    dumbbellSwingsDescriptionTV.setText(R.string.dumbbell_swings_description);
                    Toast.makeText(MainActivity.this, "DumbbellSwings pressed", Toast.LENGTH_SHORT).show();

                }
                else {
                    Log.i(TAG, "DumbbellSwings pressed, dumbbellSwingsDescriptionCV is turning GONE4");
                    dumbbellSwingsDescriptionCV.setVisibility(GONE);
                    Toast.makeText(MainActivity.this, "DumbbellSwings pressed", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    private void setJumpingRopeGoToExerciseBehavior(){
        goToExerciseJumpingRope.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), JumpingRopeActivity.class);
                intent.putExtra(JUMPING_ROPE_KEY, jumpingRopeCount);
                startActivityForResult(intent, JUMPING_ROPE_REQUEST_CODE);

//                startActivity(intent);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == JUMPING_ROPE_REQUEST_CODE && resultCode == RESULT_OK && data != null){
            jumpingRopeCount = data.getIntExtra(JUMPING_ROPE_KEY, 0);
            jumpingRopeSetLastRepeatCount();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
