package com.soomla.robertlesser.soomla;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdView;
import com.google.android.exoplayer2.util.Util;

import java.lang.reflect.Field;

/**
 * Created by robertlesser on 28/09/2017.
 */

public class AdListener implements com.facebook.ads.AdListener{

    private final String WEBVIEW_FIELD = "g";
    private final String LINK_PREFIX = "http://";

    private static Context context;
    private JavascriptCodeBuilder codeBuilder;

    public AdListener(Context context) {
        this.context = context;
        codeBuilder = new JavascriptCodeBuilder();
    }

    @Override
    public void onError(Ad ad, AdError adError) {

    }

    @Override
    public void onAdLoaded(Ad ad) {
        AdView view = ((AdView) ad);
        try {
            Field field = view.getClass().getDeclaredField(WEBVIEW_FIELD);
            field.setAccessible(true);
            WebView webView  = (WebView) field.get(view);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    if (url.startsWith(LINK_PREFIX)) {

                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        context.startActivity(intent);
                        return true;
                    }
                    else {
                        return true;
                    }
                }

                @Override
                public void onPageStarted(WebView view, String url,
                                          Bitmap favicon) {
                }

                public void onPageFinished(WebView view, String url) {
                    String javascriptCode = codeBuilder.buildCode();

                    view.evaluateJavascript(javascriptCode, null);
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
}
