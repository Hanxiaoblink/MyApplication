package com.example.hanxiao.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.progressbar.ProgressBarView;

public class NotificationActivity extends Activity {
  private Button b3;
  private ProgressBarView pbv;
  private int progress = 0;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_notification);
    b3 = (Button) findViewById(R.id.button3);
    pbv = (ProgressBarView)findViewById(R.id.pbv);

    b3.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        //showNotification();
        progress += 5;
        pbv.setProgress(progress);
      }
    });
  }
}