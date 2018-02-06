package com.kickingmobiledev.customviewjava.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.kickingmobiledev.customviewjava.R;

public class Section5Activity extends AppCompatActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, Section5Activity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section5);
    }
}
