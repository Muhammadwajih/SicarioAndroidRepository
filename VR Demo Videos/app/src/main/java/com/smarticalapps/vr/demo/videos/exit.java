package com.smarticalapps.vr.demo.videos;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class exit extends Activity {
    Intent intent;

    ImageView iv;
    int i;
    Runnable r;
    public Integer[] images =
            {
                    R.drawable.incp01,
                    R.drawable.incp03,
                    R.drawable.incp02
            };
    public String[] Links =
            {
                    "https://play.google.com/store/apps/details?id=com.smartical.vr.cinema.videoplayer&hl=en",
                    "https://play.google.com/store/apps/details?id=com.smarticalapps.vr.video.recorder&hl=en",
                    "https://play.google.com/store/apps/details?id=com.smarticalapps.vr.songs.videoplayer&hl=en"
            };

    ImageView INCPimage;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exit);
        ////////////////////////////IACP/////////////////////////////////
        iv = (ImageView) findViewById(R.id.INCP);
        i = 0;
        r = new Runnable()
        {
            public void run()
            {
                final int a = i;
                iv.setImageResource(images[a]);
                i++;
                if (i >= images.length)
                {
                    i = 0;
                }
                iv.postDelayed(r, 2000); //set to go off again in 3 seconds.

                iv.setOnClickListener(new View.OnClickListener()
                {
                    public void onClick(View v)
                    {
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.addCategory(Intent.CATEGORY_BROWSABLE);
                        intent.setData(Uri.parse(Links[a]));
                        startActivity(intent);
                    }
                });
            }
        };
        iv.postDelayed(r, 100); // set first time for 3 seconds////// }

        for (int i=0; i>=0; i++)
        {
            if(i==1000)
                break;
        }
        ////////////////////////////////////////////////////////////////
        ImageView b1 = (ImageView) findViewById(R.id.imageButton4);
        try
        {
            b1.setOnClickListener(
                    new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {
                            Intent intent = new Intent(exit.this, StartActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.putExtra("Exit me", true);
                            startActivity(intent);
                            finish();
                        }
                    }
            );
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        ImageView b2 = (ImageView) findViewById(R.id.imageButton5);
        b2.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        /*Intent intent = new Intent(exit.this, StartActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);*/
                        finish();
                    }
                }
        );

        /*ImageView incp1 = (ImageView) findViewById(R.id.imageView2);
        incp1.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.smartical.vr.cinema.videoplayer");
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }
                }
        );

        ImageView incp2 = (ImageView) findViewById(R.id.imageView3);
        incp2.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.smarticalapps.vr.video.recorder&hl=en");
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }
                }
        );

        ImageView incp3 = (ImageView) findViewById(R.id.imageView4);
        incp3.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.smarticalapps.vr.songs.videoplayer");
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }
                }
        );*/
    }
}
