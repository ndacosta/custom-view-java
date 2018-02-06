package com.kickingmobiledev.customviewjava.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.kickingmobiledev.customviewjava.R;
import com.kickingmobiledev.customviewjava.databinding.ActivitySection6Binding;
import com.kickingmobiledev.customviewjava.ui.widgets.ResizableTextView;

public class Section6Activity extends AppCompatActivity {

    private ResizableTextView textView;

    public static void start(Context context) {
        Intent starter = new Intent(context, Section6Activity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySection6Binding binding = DataBindingUtil.setContentView(this, R.layout.activity_section6);
        textView = binding.textView;
    }

    public void onClickButton(View button) {
        switch (button.getId()) {
            case R.id.banned:
                textView.setFrontDrawableResource(R.drawable.ic_block_black_24dp);
                break;
            case R.id.approved:
                textView.setFrontDrawableResource(R.drawable.ic_check_black_24dp);
                break;
        }
    }
}
