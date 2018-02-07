package com.kickingmobiledev.customviewjava.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.View;

import com.kickingmobiledev.customtextview.ResizableTextView;
import com.kickingmobiledev.customviewjava.R;
import com.kickingmobiledev.customviewjava.databinding.ActivitySection10Binding;

public class Section10Activity extends AppCompatActivity {

    private ResizableTextView textView;

    public static void start(Context context) {
        Intent starter = new Intent(context, Section10Activity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySection10Binding binding = DataBindingUtil.setContentView(this, R.layout.activity_section10);
        textView = binding.textView;
    }

    public void onClickButton(View button) {
        SpannableString spannable = new SpannableString(textView.getText());
        spannable.setSpan(new StrikethroughSpan(), 10, 13, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new StyleSpan(Typeface.ITALIC), 10, 13, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new UnderlineSpan(), 0, 5, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new StyleSpan(Typeface.BOLD), 8, textView.getText().length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        textView.setText(spannable);
    }
}
