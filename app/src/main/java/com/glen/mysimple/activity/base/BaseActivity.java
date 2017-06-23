package com.glen.mysimple.activity.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Gln on 2017/6/12.
 */
public class BaseActivity extends AppCompatActivity {
    public String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = getComponentName().getShortClassName();
    }
}
