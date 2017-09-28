package com.soomla.robertlesser.soomla;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;

import com.facebook.ads.*;

import java.lang.reflect.Field;
import java.util.Random;


public class MainActivity extends Activity implements View.OnClickListener, AdContainer {

    Button bannerButton;
    private AdView adView;
    private LinearLayout adContainer;

    private AdInjector adInjector;
    private AdListener adListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bannerButton = findViewById(R.id.banner_button);
        bannerButton.setOnClickListener(this);
        adContainer = findViewById(R.id.banner_container);

        adListener = new AdListener(this);
        adInjector = new AdInjector(this, adListener);
    }

    @Override
    protected void onDestroy() {
        adInjector.destroy();
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        addAd();
    }

    private void addAd() {
        adInjector.addAd(this);
    }

    @Override
    public void addAdToContainer(AdView adView) {
        this.adContainer.addView(adView);
    }
}
