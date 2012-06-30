/*******************************************************************************
 * Copyright (c) 2012 Vincenzo Marzano.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     Vincenzo Marzano - initial API and implementation
 ******************************************************************************/
package com.tecnojin.timekiller.activity;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.tecnojin.timekiller.R;
import com.tecnojin.timekiller.games.GameManager;
import com.tecnojin.timekiller.games.descriptors.GameDescriptor;
import com.tecnojin.timekiller.menuviews.TutorialAdapter;
import com.tecnojin.timekiller.util.ActivityUtil;

public class TutorialActivity extends Activity{
	private ProgressBar progress;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		ActivityUtil.makeFullScreen(this);
		
		int a=getIntent().getIntExtra(getPackageName()+".tutorial",-1);
		if(a<0){
			Toast.makeText(this, R.string.noTutorial, Toast.LENGTH_SHORT).show();
			finish();
		}
		GameDescriptor g=GameManager.instance(this).getGame(a, this);
		if(g.getTutorial()==null){
			Toast.makeText(this, R.string.noTutorial, Toast.LENGTH_SHORT).show();
			finish();
		}
		
		setContentView(R.layout.tutorial_activity_layout);
		LinearLayout l=(LinearLayout) findViewById(R.id.tutorial_panel);
		ViewPager p=new ViewPager(this);
		p.setAdapter(new TutorialAdapter(g.getTutorial(),this));
		l.addView(p);
		ImageView back=(ImageView) findViewById(R.id.back);
		back.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				finish();
				
			}
		});
		p.setOnPageChangeListener(new OnPageChangeListener() {
			
			public void onPageSelected(int arg0) {
				updateProgress(arg0);
				
			}
			
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		progress=(ProgressBar) findViewById(R.id.progress);
		progress.setMax(g.getTutorial().getPageCount());
		updateProgress(0);
	}

	private void updateProgress(int i) {
		progress.setProgress(i+1);
		
		
	}

}
