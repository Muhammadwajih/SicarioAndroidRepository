package com.smarticalapps.vr.sbs360.videoplayer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.ads.InterstitialAd;


public class more extends Activity
{
    private String TAG = more.class.getSimpleName();
    InterstitialAd mInterstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        /*StartActivity.spinner.setVisibility(View.VISIBLE);*/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                            try
                            {
                                Thread.sleep(4000);
                            }
                            catch (InterruptedException e)
                            {
                                e.printStackTrace();
                            }
                            runOnUiThread(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    /*spinner.setVisibility(View.GONE);*/
                                    finish();
                                    startActivity(new Intent(more.this, MainActivity.class));
                                }
                            });
            }
        }).start();


        // it's for demo purpose
        /*if (TextUtils.isEmpty(getString(R.string.interstitial_full_screen)))
        {
            Toast.makeText(getApplicationContext(), "Please mention your Interstitial Ad ID in strings.xml", Toast.LENGTH_LONG).show();
            return;
        }
        mInterstitialAd = new InterstitialAd(this);
        // set the ad unit ID
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_full_screen));

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                // Check the LogCat to get your test device ID
                .addTestDevice("5872DFE0F0DE2D4DEDAF34B0D55A99C9")
                .build();

        mInterstitialAd.loadAd(adRequest);
        mInterstitialAd.setAdListener(new AdListener()
        {
            public void onAdLoaded()
            {
                showInterstitial();
            }

            @Override
            public void onAdClosed()
            {
            }
            @Override
            public void onAdFailedToLoad(int errorCode) {
            }

            @Override
            public void onAdLeftApplication() {
            }

            @Override
            public void onAdOpened() {
            }
        });*/
    }
    public void onBackPressed()
    {
        //Do Nothing
    }
}

