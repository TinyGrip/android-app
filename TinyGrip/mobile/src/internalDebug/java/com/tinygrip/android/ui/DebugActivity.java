package com.tinygrip.android.ui;

import android.app.Activity;
import android.os.Bundle;

import com.tinygrip.android.R;


public final class DebugActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.debug_activity);
    }
}