package ru.matbrain.trainingapp;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
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
    private ImageButton minusButton;
    private ImageButton plusButton;
    private TextView jumpingRopeCountTV;

    private int jumpingRopeCount = 0;

    private Button hrefButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jumping_rope);

        initUI();

        jumpingRopeCount = getIntent().getIntExtra(MainActivity.JUMPING_ROPE_KEY, 0);
        jumpingRopeCountTV.setText(Integer.toString(jumpingRopeCount));
        setHrefButtonBehavior();

    }

    private void initUI() {
        minusButton = (ImageButton) findViewById(R.id.button_minus);
        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (jumpingRopeCount >= 1) {
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

        hrefButton = (Button) findViewById(R.id.href_jumping_rope);
    }

    @Override
    public void onBackPressed() {
        Intent jumpingRopeIntent = new Intent();
        jumpingRopeIntent.putExtra(MainActivity.JUMPING_ROPE_KEY, jumpingRopeCount);
        setResult(RESULT_OK, jumpingRopeIntent);
        super.onBackPressed();
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

}
