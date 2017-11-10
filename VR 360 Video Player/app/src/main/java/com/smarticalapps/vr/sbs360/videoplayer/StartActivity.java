package com.smarticalapps.vr.sbs360.videoplayer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.ads.InterstitialAd;
import com.smarticalapps.vr.sbs360.videoplayer.content.YouTubeContent;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class StartActivity extends Activity implements View.OnClickListener
{
    private InterstitialAd mInterstitialAd;

    private AdView mAdView;
    public static ProgressBar spinner;
    ImageButton more;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        if( getIntent().getBooleanExtra("Exit me", false))
    {
        finishAffinity();
    }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

                    //appling spinner progress bar..
        spinner = (ProgressBar)findViewById(R.id.progressBar1);
        spinner.setVisibility(View.GONE);
        /*Drawable draw = getResources().getDrawable(R.drawable.custom_progressbar);
        spinner.setProgressDrawable(draw);*/
        ImageButton play = (ImageButton) findViewById(R.id.imageButton1);
        play.setOnClickListener(this);
        ImageView exit =(ImageView) findViewById(R.id.imageButton);
        exit.setOnClickListener(this);

        ImageView rate =(ImageView) findViewById(R.id.imageView5);
        rate.setOnClickListener(this);

        more =(ImageButton) findViewById(R.id.imageButton3);
        more.setOnClickListener(this);

        /////////////////////////Loading Interstial ADs //////////////////////////////////////
        mInterstitialAd = newInterstitialAd();
        loadInterstitial();
        //////////////////////////////////////////////////////////////////////////////////
                        ///////////////////////////Applying banner Ads//////////////////////////////////
        if (TextUtils.isEmpty(getString(R.string.banner_home_footer)))
        {
            /*Toast.makeText(getApplicationContext(), "Please mention your Banner Ad ID in strings.xml", Toast.LENGTH_LONG).show();*/
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
            public void onAdLoaded() {
            }
            @Override
            public void onAdClosed() {
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
    }
    ///////////////////////////ADD End! ////////////////////////////////////

    ///////////////////////// On Back press ////////////////////////////////
    public void onBackPressed()
    {
        startActivity(new Intent(StartActivity.this, exit.class));
    }
    ////////////////////////////On back press End /////////////////////////
    public void onClick(View v)
    {
        switch (v.getId())
        {
            //do your code
            case R.id.imageButton1:
                /*spinner.setVisibility(View.VISIBLE);*/

                String URL1 = "https://www.googleapis.com/youtube/v3/playlists?part=snippet&channelId=UC5RdRZN7q6temIhzHMlmRnA&key=AIzaSyB8RuveEIrh9hWLcl0cW4zrDZz0F6oYuTo";

                if (isNetworkAvailable())
                {
                    YouTubeContent.getdata(URL1);
                    startActivity(new Intent(StartActivity.this, more.class));
                    showInterstitial();
                   /* new Thread(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            *//*try
                            {
                                startActivity(new Intent(StartActivity.this, more.class));
                                Thread.sleep(12000);
                                finish();
                            }
                            catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            runOnUiThread(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    spinner.setVisibility(View.GONE);
                                    startActivity(new Intent(StartActivity.this, MainActivity.class));
                                }
                            });*//*
                        }
                    }).start();*/
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Check your network connection ... ",
                            Toast.LENGTH_LONG).show();
                    spinner.setVisibility(View.GONE);
                }
                break;

            case R.id.imageButton:

                startActivity(new Intent(StartActivity.this, exit.class));
                break;

            case R.id.imageButton3:

            Uri uri = Uri.parse("https://play.google.com/store/apps/developer?id=Smartical+Apps");
            intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
            break;

            case  R.id.imageView5:
                Uri uri1 = Uri.parse("https://play.google.com/store/apps/details?id=com.smarticalapps.vr.sbsdemo.videoplayer");
                intent = new Intent(Intent.ACTION_VIEW, uri1);
                startActivity(intent);

            default:
                break;
        }
    }
    private boolean isNetworkAvailable()
    {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    ////////////////////////////////////Interstitial AD ////////////////////////////////////////
    private InterstitialAd newInterstitialAd()
    {
        InterstitialAd interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getString(R.string.interstitial_full_screen));
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
            }
            @Override
            public void onAdFailedToLoad(int errorCode)
            {

            }
            @Override
            public void onAdClosed()
            {

            }
        });
        return interstitialAd;
    }
    private void showInterstitial()
    {
        // Show the ad if it's ready. Otherwise toast and reload the ad.
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Toast.makeText(this, "Wait to Connect Network", Toast.LENGTH_SHORT).show();
        }
    }
    private void loadInterstitial()
    {
        // Disable the next level button and load the ad.
        AdRequest adRequest = new AdRequest.Builder()
                /*.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
            // Check the LogCat to get your test device ID
            .addTestDevice("5872DFE0F0DE2D4DEDAF34B0D55A99C9")*/
            .build();
        mInterstitialAd.loadAd(adRequest);
    }
    ////////////////////////////////////////End of AD /////////////////////////////////////////
}
