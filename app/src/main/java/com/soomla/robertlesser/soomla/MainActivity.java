package com.soomla.robertlesser.soomla;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;

import com.facebook.ads.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static android.R.attr.value;

public class MainActivity extends Activity implements View.OnClickListener, AdListener {

    private static String PLACEMENT_ID = "IMG_16_9_APP_INSTALL#YOUR_PLACEMENT_ID";

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
                    adView.setAdListener(this);
                }
                break;
        }
    }

    @Override
    public void onError(Ad ad, AdError adError) {

    }

    @Override
    public void onAdLoaded(Ad ad) {
        AdView view = ((AdView) ad);
        try {
            Field field = view.getClass().getDeclaredField("g");
            field.setAccessible(true);
            WebView webView  = (WebView) field.get(view);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    if (url.startsWith("http://")) {

                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intent);
                        return true;
                    }
                    else {
                        int i = url.length();
                        return true;
                    }
                }

                @Override
                public void onPageStarted(WebView view, String url,
                                          Bitmap favicon) {
                }

                public void onPageFinished(WebView view, String url) {
                    view.evaluateJavascript("var FunctionOne = function () {"
                            + "  try{document.getElementsByClassName('icon')[0].src='https://avatars3.githubusercontent.com/u/2118838?v=4&s=200';" +
                            "document.getElementById('fbAdLink').href='http://www.google.com'; }catch(e){}"
                            + "}; FunctionOne();", null);
                }
            });
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onAdClicked(Ad ad) {

    }

    @Override
    public void onLoggingImpression(Ad ad) {

    }

//    class LoadListener{
//
//        private String html;
//        @JavascriptInterface
//        public void processHTML(String html)
//        {
//            Log.e("result",html);
//            this.html = html;
//        }
//
//        public void changeButtonText(String text){
//
//        }
//
//        public String getHtml() {
//            return html;
//        }
//    }
}
