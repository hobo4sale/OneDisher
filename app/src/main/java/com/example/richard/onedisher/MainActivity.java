package com.example.richard.onedisher;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;


public class MainActivity extends Activity {

    private Random mRand;
    private TextView mTextChoice;
    private TextView mRichardCount;
    private TextView mTimCount;
    private int timCounter;
    private int richardCounter;
    private SharedPreferences mPrefs;
    private SharedPreferences.Editor editor;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRand = new Random();

        mPrefs = getPreferences(MODE_PRIVATE);
        editor = mPrefs.edit();

        richardCounter = mPrefs.getInt("richard", 0);
        timCounter = mPrefs.getInt("tim", 0);

        Button buttonDish = (Button) findViewById(R.id.button_dish);

        mRichardCount = (TextView) findViewById(R.id.text_richard_count);
        mTimCount = (TextView) findViewById(R.id.text_tim_count);

        mRichardCount.setText(String.valueOf(richardCounter));
        mTimCount.setText(String.valueOf(timCounter));

        mTextChoice = (TextView) findViewById(R.id.text_choice);
        buttonDish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean temp = mRand.nextBoolean();
                if (temp) {
                    mTextChoice.setText("Richard");
                    richardCounter++;
                    mRichardCount.setText(String.valueOf(richardCounter));
                } else {
                    mTextChoice.setText("Tim");
                    timCounter++;
                    mTimCount.setText(String.valueOf(timCounter));
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        editor.putInt("richard", richardCounter);
        editor.putInt("tim", timCounter);
        editor.apply();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
