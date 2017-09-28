package com.soomla.robertlesser.soomla;

import android.content.Context;
import com.facebook.ads.*;
import com.facebook.ads.AdListener;

import java.util.ArrayList;

/**
 * Created by robertlesser on 28/09/2017.
 */

public class AdInjector {

    private static String PLACEMENT_ID = "IMG_16_9_APP_INSTALL#YOUR_PLACEMENT_ID";

    Context context;
    ArrayList<AdView> adViews;
    AdListener adlistener;

    public AdInjector(Context context, AdListener adlistener){
        this.context = context;
        this.adlistener = adlistener;
        adViews = new ArrayList<>();
    }

    public void addAd(AdContainer adContainer) {
        // Instantiate an AdView view
        AdView adView = new AdView(context, PLACEMENT_ID, AdSize.BANNER_HEIGHT_50);
        adViews.add(adView);

        // Add the ad view to your activity layout
        adContainer.addAdToContainer(adView);

        // Request an ad
        adView.loadAd();
        adView.setAdListener(adlistener);
    }

    public void destroy() {
        if (!adViews.isEmpty()) {
            for (AdView adview : adViews) {
                adview.destroy();
            }
            adViews = new ArrayList<>();
        }
    }
}
