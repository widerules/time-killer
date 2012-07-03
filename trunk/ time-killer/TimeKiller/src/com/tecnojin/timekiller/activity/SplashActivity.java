package com.tecnojin.timekiller.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import com.tecnojin.timekiller.R;
import com.tecnojin.timekiller.util.ActivityUtil;

public class SplashActivity extends Activity{
	private static long WAIT_TIME=1000;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActivityUtil.makeFullScreen(this);
		setContentView(R.layout.splash_layout);		
		TextView t=(TextView) findViewById(R.id.tecnojin);
		TextView n=(TextView) findViewById(R.id.myname);
		ActivityUtil.setFont(t, ActivityUtil.FONTS_ACID_DL);
		ActivityUtil.setFont(n, ActivityUtil.FONTS_DIRTY_EGO);
		
		
		
		
		
		
	}
	@Override
	protected void onStart() {
		super.onStart();
		
			new AsyncTask<Long, Integer, Integer>() {

				@Override
				protected Integer doInBackground(Long... params) {
					try {
						Thread.sleep(params[0]);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					startActivity(new Intent(SplashActivity.this,MainActivity.class));
					finish();
					return 1;
				}
			}.execute(WAIT_TIME);
		
		
		
	}
	@Override
	public void onBackPressed() {
		
	}
}
