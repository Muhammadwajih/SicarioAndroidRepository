package com.smarticalapps.vr.video.recorder;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class StartActivity extends Activity implements View.OnClickListener {
    Boolean check = false;
    Intent intent;
    AdView mAdView;
    AdRequest adRequest;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        if( getIntent().getBooleanExtra("Exit me", false))
        {
            finish();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        ImageView record = (ImageView) findViewById(R.id.record);
        ImageView more =(ImageView) findViewById(R.id.more);
        ImageView like =(ImageView) findViewById(R.id.like);
        ImageView share = (ImageView) findViewById(R.id.share);
        /*ImageView im = (ImageView) findViewById(R.id.check);
        im.setOnClickListener(this);*/
        record.setOnClickListener(this);
        more.setOnClickListener(this);
        like.setOnClickListener(this);
        share.setOnClickListener(this);

        ///////////Applying ads////////////////
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
            public void onAdLoaded()
            {
            }
            @Override
            public void onAdClosed()
            {
            }
            @Override
            public void onAdFailedToLoad(int errorCode)
            {
            }
            @Override
            public void onAdLeftApplication()
            {
            }
            @Override
            public void onAdOpened()
            {
                super.onAdOpened();
            }
        });
        mAdView.loadAd(adRequest);
    }
    public void onClick(View v)
    {
        switch (v.getId())
        {
            //do your code
            case R.id.record:
                int PERMISSION_ALL = 1;
                String[] PERMISSIONS = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO};
                if(!hasPermissions(this, PERMISSIONS) && check!=true)
                {
                    check=true;
                    ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
                    Toast.makeText(this,"Camera permission granted",Toast.LENGTH_LONG).show();
                }
                else
                {
                    new Thread(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            try
                            {
                                startActivity(new Intent(StartActivity.this, loading.class));
                                Thread.sleep(10000);
                                finishAndRemoveTask();
                            }
                            catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            runOnUiThread(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    startActivity(new Intent(StartActivity.this, MainActivity.class));
                                }
                            });
                        }
                    }).start();
                }
                break;
            case R.id.more:
                Uri uri = Uri.parse("https://play.google.com/store/apps/developer?id=Smartical+Apps");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
            case R.id.like:
                Uri urii = Uri.parse("https://play.google.com/store/apps/details?id=com.smarticalapps.vr.video.recorder");
                intent = new Intent(Intent.ACTION_VIEW, urii);
                startActivity(intent);

                break;
            case R.id.share:
                String t1 ="VR Video Camera Recorder\n\n"+"https://play.google.com/store/apps/details?id=com.smarticalapps.vr.video.recorder";
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
                break;
            /*case R.id.check:
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        intent.setType(Environment.getExternalStorageState()+"/storage");
                        startActivity(intent);
                break;*/
            default:
                break;
        }

    }
        ///////////////////////////////////////
    public static boolean hasPermissions(Context context, String... permissions)
    {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }
    public void onBackPressed()
    {
        startActivity(new Intent(StartActivity.this,exit.class));
    }
}
