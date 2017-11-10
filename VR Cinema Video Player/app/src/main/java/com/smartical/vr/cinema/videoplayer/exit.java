package com.smartical.vr.cinema.videoplayer;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class exit extends Activity {
   // private Vibrator myVib;
    ImageView iv;
    int i;
    Runnable r;
    public Integer[] images =
            {
                    R.drawable.incp02,
                    R.drawable.incp03,
                    R.drawable.incp04
            };
    public String[] Links =
            {
                    "https://play.google.com/store/apps/details?id=com.smarticalapps.vr.songs.videoplayer&hl=en",
                    "https://play.google.com/store/apps/details?id=com.smarticalapps.vr.video.recorder&hl=en",
                    "https://play.google.com/store/apps/details?id=com.smarticalapps.vr.demo.videos&hl=en"

            };
    ImageView INCPimage;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exit);
        //myVib = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);

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

        ImageView b1 = (ImageView) findViewById(R.id.imageButton4);  // yes button
        try
        {
            b1.setOnClickListener(
                    new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {
                            //myVib.vibrate(50);
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

        ImageView b2 = (ImageView) findViewById(R.id.imageButton5); // No button
        b2.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        //myVib.vibrate(50);
                        /*Intent intent = new Intent(exit.this, StartActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);*/
                        exit.this.finish();
                    }
                }
        );
    }
}
