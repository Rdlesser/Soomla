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

import java.lang.reflect.Field;

/**
 * Created by robertlesser on 28/09/2017.
 */

public class AdListener implements com.facebook.ads.AdListener{

    private static Context context;

    private final String[] icons = {
            "https://avatars3.githubusercontent.com/u/2118838?v=4&s=200",
            "https://maxcdn.icons8.com/Share/icon/color/Gaming//bullbasaur1600.png",
            "https://lh3.googleusercontent.com/ez8pDFoxU2ZqDmyfeIjIba6dWisd8MY_6choHhZNpO0WwLhICu0v0s5eV2WHOhuhKw=w170",
            "https://lh3.googleusercontent.com/YGqr3CRLm45jMF8eM8eQxc1VSERDTyzkv1CIng0qjcenJZxqV5DBgH5xlRTawnqNPcOp=w300",
            "https://maxcdn.icons8.com/Share/icon/color/Users//donald_trump1600.png"
    };

    private final String[] links = {
            "http://www.hattrick.org/",
            "http://www.youtube.com/",
            "http://www.google.com",
            "http://www.something.com/"
    };


    public AdListener(Context context) {
        this.context = context;
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
                    view.evaluateJavascript("var FunctionOne = function () {"
                            + "  try{document.getElementsByClassName('icon')[0].src='"
                            + Utility.getRandom(icons)
                            + "'; "
                            + "document.getElementById('fbAdLink').href='"
                            + Utility.getRandom(links)
                            + "'; }catch(e){}"
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
}
