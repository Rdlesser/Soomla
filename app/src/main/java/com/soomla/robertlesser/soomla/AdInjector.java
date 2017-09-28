package com.soomla.robertlesser.soomla;

import android.content.Context;
import android.widget.LinearLayout;

import com.facebook.ads.*;
import com.facebook.ads.AdListener;

import static com.facebook.ads.AudienceNetworkActivity.PLACEMENT_ID;

/**
 * Created by robertlesser on 28/09/2017.
 */

public class AdInjector {

    private static String PLACEMENT_ID = "IMG_16_9_APP_INSTALL#YOUR_PLACEMENT_ID";

    Context context;
    AdView adView;
    AdListener adlistener;

    public AdInjector(Context context, AdListener adlistener){
        this.context = context;
        this.adlistener = adlistener;
    }

    public void addAd(AdContainer adContainer) {
        // Instantiate an AdView view
        adView = new AdView(context, PLACEMENT_ID, AdSize.BANNER_HEIGHT_50);

        // Add the ad view to your activity layout
        adContainer.addAdToContainer(adView);

        // Request an ad
        adView.loadAd();
        adView.setAdListener(adlistener);
    }

    public void destroy() {
        if (adView != null) {
            adView.destroy();
        }
    }
}
