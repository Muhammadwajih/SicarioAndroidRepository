package com.smarticalapps.vr.video.recorder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

public class SavingActivity extends Activity {
    String Path;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saving);

        ImageView cancle = (ImageView) findViewById(R.id.cancle);
        cancle.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        Path = getIntent().getStringExtra("PATH");
                        File file = new File(Path);
                        boolean deleted = file.delete();
                        startActivity(new Intent(SavingActivity.this, StartActivity.class));
                        finish();
                    }
                }
        );

        ImageView save = (ImageView) findViewById(R.id.save);
        save.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        Toast.makeText(getApplicationContext(), "Video Successfully Saved",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(SavingActivity.this, StartActivity.class));
                        finish();
                    }
                }
        );
    }
    public void onBackPressed()
    {
        
    }
}
