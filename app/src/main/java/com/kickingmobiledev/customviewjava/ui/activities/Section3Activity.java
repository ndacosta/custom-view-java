package com.kickingmobiledev.customviewjava.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.kickingmobiledev.customviewjava.R;
import com.kickingmobiledev.customviewjava.databinding.ActivitySection3Binding;
import com.kickingmobiledev.customtextview.ResizableTextView;

public class Section3Activity extends AppCompatActivity {

    private ResizableTextView textView;

    public static void start(Context context) {
        Intent starter = new Intent(context, Section3Activity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySection3Binding binding = DataBindingUtil.setContentView(this, R.layout.activity_section3);
        textView = binding.resizableTextView;
    }

    public void onClickButton(View button) {
        switch (button.getId()) {
            case R.id.normalAspectRatio:
                textView.setAspectRatio(ResizableTextView.NORMAL_ASPECT_RATIO);
                break;
            case R.id.oneTo2:
                textView.setAspectRatio(ResizableTextView.ONE_TO_2_ASPECT_RATIO);
                break;
            case R.id.twoTo3:
                textView.setAspectRatio(ResizableTextView.TWO_TO_3_ASPECT_RATIO);
                break;
            case R.id.oneTo1:
                textView.setAspectRatio(ResizableTextView.ONE_TO_1_ASPECT_RATIO);
                break;
        }
    }
}
