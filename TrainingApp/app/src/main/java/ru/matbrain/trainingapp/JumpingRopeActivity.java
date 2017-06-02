package ru.matbrain.trainingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by danilzdanov on 24.05.17.
 */

public class JumpingRopeActivity extends AppCompatActivity {

    private static final String TAG = "JumpingRopeActivity";
    private ImageButton minusButton;
    private ImageButton plusButton;
    private TextView jumpingRopeCountTV;
//    private TextView descriptionTextView;

    private int jumpingRopeCount = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jumping_rope);

        initUI();

        jumpingRopeCount = getIntent().getIntExtra(MainActivity.JUMPING_ROPE_KEY, 0);
        jumpingRopeCountTV.setText(Integer.toString(jumpingRopeCount));


    }

    private void initUI() {
        minusButton = (ImageButton) findViewById(R.id.button_minus);
        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (jumpingRopeCount>=1){
                    jumpingRopeCount--;
                    jumpingRopeCountTV.setText(Integer.toString(jumpingRopeCount));
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
                jumpingRopeCountTV.setText(Integer.toString(jumpingRopeCount));
            }
        });
        jumpingRopeCountTV = (TextView) findViewById(R.id.repeats_count);
    }

    @Override
    public void onBackPressed() {
        Intent jumpingRopeIntent = new Intent();
        jumpingRopeIntent.putExtra(MainActivity.JUMPING_ROPE_KEY, jumpingRopeCount);
        setResult(RESULT_OK, jumpingRopeIntent);
        super.onBackPressed();
    }
}
