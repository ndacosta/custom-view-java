package com.kickingmobiledev.customviewjava.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.kickingmobiledev.customviewjava.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickButton(View button) {
        switch (button.getId()) {
            case R.id.section1:
                Section1Activity.start(this);
                break;
            case R.id.section2:
                Section2Activity.start(this);
                break;
            case R.id.section3:
                Section3Activity.start(this);
                break;
            case R.id.section5:
                Section5Activity.start(this);
                break;
            case R.id.section6:
                Section6Activity.start(this);
                break;
            case R.id.section7:
                Section7Activity.start(this);
                break;
            case R.id.section8:
                Section8Activity.start(this);
                break;
            case R.id.section10:
                Section10Activity.start(this);
                break;
        }
    }
}
