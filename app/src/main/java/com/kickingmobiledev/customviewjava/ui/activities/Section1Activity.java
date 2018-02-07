package com.kickingmobiledev.customviewjava.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;

import com.kickingmobiledev.customviewjava.R;
import com.kickingmobiledev.customviewjava.databinding.ActivitySection1Binding;
import com.kickingmobiledev.customtextview.ResizableTextView;

public class Section1Activity extends AppCompatActivity implements TextWatcher {

    private ResizableTextView textView;
    private AppCompatEditText inputText;

    public static void start(Context context) {
        Intent starter = new Intent(context, Section1Activity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySection1Binding binding = DataBindingUtil.setContentView(this, R.layout.activity_section1);
        textView = binding.textView;
        inputText = binding.inputText;
        inputText.addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        textView.setText(s);
    }
}
