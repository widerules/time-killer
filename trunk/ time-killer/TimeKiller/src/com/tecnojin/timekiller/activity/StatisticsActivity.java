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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.tecnojin.timekiller.R;
import com.tecnojin.timekiller.games.GameManager;
import com.tecnojin.timekiller.games.descriptors.options.StatSet;
import com.tecnojin.timekiller.menuviews.StatisticsAdapter;
import com.tecnojin.timekiller.util.ActivityUtil;

public class StatisticsActivity extends Activity{
	private StatSet set;
	private StatisticsAdapter adapter;
	private ViewPager pager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ActivityUtil.makeFullScreen(this);
		setContentView(R.layout.option_layout);
	
		pager=new ViewPager(this);
		((LinearLayout)findViewById(R.id.statisticLayout)).addView(pager);

		int optionIndex=getIntent().getIntExtra(getPackageName()+".statistics", -1);

		set=GameManager.instance(this).getGame(optionIndex, this).getStatistics();

		if(set==null){
			Toast.makeText(this, R.string.noOption, Toast.LENGTH_SHORT).show();
			finish();
		}
		adapter=new StatisticsAdapter(this, android.R.layout.simple_list_item_single_choice, set);
		pager.setAdapter(adapter);

		
		ImageView back=(ImageView) findViewById(R.id.back);
		back.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				finish();
			}
		});

	}

}
