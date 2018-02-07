package com.kickingmobiledev.customviewjava.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.kickingmobiledev.customtextview.ResizableTextView;
import com.kickingmobiledev.customviewjava.R;
import com.kickingmobiledev.customviewjava.databinding.ActivitySection11Binding;

public class Section11Activity extends AppCompatActivity {

    private ResizableTextView textView;

    public static void start(Context context) {
        Intent starter = new Intent(context, Section11Activity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySection11Binding binding = DataBindingUtil.setContentView(this, R.layout.activity_section11);
        textView = binding.textView;
    }

    public void onClickButton(View button) {
        if (textView.getVisibility() == View.VISIBLE) {
            textView.setVisibility(View.GONE);
        } else {
            textView.setVisibility(View.VISIBLE);
        }
    }
}
