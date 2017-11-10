package com.smarticalapps.vr.demo.videos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.youtube.player.YouTubeApiServiceUtil;
import com.google.android.youtube.player.YouTubeInitializationResult;

public class MainActivity extends AppCompatActivity
{

    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        try
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);


            if( getIntent().getBooleanExtra("Exit me", false))
            {
                finishAffinity();
            }
            ///////////////////////////////custom tool bar //////////////////////////////////////
            /*LayoutInflater mInflaterads = LayoutInflater.from(this);
            View adsview = mInflaterads.inflate(R.layout.ads, null);*/

            /*getSupportActionBar().setDisplayShowHomeEnabled(false);
            getSupportActionBar().setDisplayShowTitleEnabled(false);*/
            LayoutInflater mInflater = LayoutInflater.from(this);
            View mCustomView = mInflater.inflate(R.layout.toolbar, null);

            ImageView backbutton = (ImageView) mCustomView
                    .findViewById(R.id.backbutton);
            backbutton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    finish();
                    startActivity(new Intent(MainActivity.this, StartActivity.class));
                }
            });
            ImageView sharebutton = (ImageView) mCustomView
                    .findViewById(R.id.sharebutton);
            sharebutton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    String t1 ="VR Demo Videos\n\n"+"https://play.google.com/store/apps/details?id=com.smarticalapps.vr.demo.videos";
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, t1);
                    sendIntent.setType("text/plain");
                    Intent intent = Intent.createChooser(sendIntent, getResources().getText(R.string.Share));

                    android.widget.ShareActionProvider sap = new android.widget.ShareActionProvider(getApplicationContext());
                    sap.setShareIntent(sendIntent);
                    sap.setOnShareTargetSelectedListener(new android.widget.ShareActionProvider.OnShareTargetSelectedListener() {
                        @Override
                        public boolean onShareTargetSelected(android.widget.ShareActionProvider source, Intent intent)
                        {
                            return false;
                        }
                    });
                    startActivityForResult(intent, 1);
                }
            });
            /*getSupportActionBar().setCustomView(adsview);*/
            getSupportActionBar().setCustomView(mCustomView);
            getSupportActionBar().setDisplayShowCustomEnabled(true);
            ///////////////////////////////////end of custonm toolbar ///////////////////////////

            ///////////////////////////Applying Ads /////////////////////////////////////////////
            if (TextUtils.isEmpty(getString(R.string.banner_home_footer)))
            {
                Toast.makeText(getApplicationContext(), "Please mention your Banner Ad ID in strings.xml", Toast.LENGTH_LONG).show();
                return;
            }
            mAdView = (AdView) findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder()
                    /*.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                    // Check the LogCat to get your test device ID
                    .addTestDevice("5872DFE0F0DE2D4DEDAF34B0D55A99C9")*/
                    .build();

            mAdView.setAdListener(new AdListener()
            {
                @Override
                public void onAdLoaded()
                {
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
                    super.onAdOpened();
                }
            });
            mAdView.loadAd(adRequest);
            /////////////////////////////////////////////////////////////////////////////
            final YouTubeInitializationResult result = YouTubeApiServiceUtil.isYouTubeApiServiceAvailable(this);
            if (result != YouTubeInitializationResult.SUCCESS)
            {
                result.getErrorDialog(this, 0).show();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    @Override
    public void onPause()
    {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }
    @Override
    public void onDestroy()
    {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }
    ///////////////////////////Ads End! //////////////////////////////////
    public void onBackPressed()
    {
        finish();
        startActivity(new Intent(MainActivity.this, StartActivity.class));
    }
}
