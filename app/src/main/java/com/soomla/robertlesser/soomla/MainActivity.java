package com.soomla.robertlesser.soomla;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.facebook.ads.*;

public class MainActivity extends Activity implements View.OnClickListener {

    private static String PLACEMENT_ID = "CAROUSEL_IMG_SQUARE_APP_INSTALL#YOUR_PLACEMENT_ID";

    private AdView adView;
    private LinearLayout adContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bannerButton = findViewById(R.id.banner_button);
        bannerButton.setOnClickListener(this);

        adContainer = findViewById(R.id.banner_container);
    }

    @Override
    protected void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        final int viewId = view.getId();
        switch (viewId){
            case R.id.banner_button:
                if (adView == null) {
                    // Instantiate an AdView view
                    adView = new AdView(this, PLACEMENT_ID, AdSize.BANNER_HEIGHT_50);

                    // Add the ad view to your activity layout
                    adContainer.addView(adView);

                    // Request an ad
                    adView.loadAd();
                }
                break;
        }
    }
}
