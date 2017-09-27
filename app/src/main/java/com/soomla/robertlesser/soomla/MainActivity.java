package com.soomla.robertlesser.soomla;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bannerButton = findViewById(R.id.banner_button);
        bannerButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        final int viewId = view.getId();
        switch (viewId){
            case R.id.banner_button:
                break;
        }
    }
}
