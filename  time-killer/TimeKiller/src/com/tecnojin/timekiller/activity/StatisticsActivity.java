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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.tecnojin.timekiller.R;
import com.tecnojin.timekiller.games.GameManager;
import com.tecnojin.timekiller.games.descriptors.options.OptionSet;
import com.tecnojin.timekiller.games.descriptors.options.StatSet;
import com.tecnojin.timekiller.menuviews.OptionAdapter;
import com.tecnojin.timekiller.menuviews.StatisticsAdapter;
import com.tecnojin.timekiller.util.ActivityUtil;

public class StatisticsActivity extends Activity{
	private StatSet set;
	private StatisticsAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActivityUtil.makeFullScreen(this);
		setContentView(R.layout.option_layout);
		ListView l=(ListView) findViewById(R.id.optionList);

		int optionIndex=getIntent().getIntExtra(getPackageName()+".statistics", -1);

		set=GameManager.instance(this).getGame(optionIndex, this).getStatistics();

		if(set==null){
			Toast.makeText(this, R.string.noOption, Toast.LENGTH_SHORT).show();
			finish();
		}
		adapter=new StatisticsAdapter(this, android.R.layout.simple_list_item_single_choice, set);
		l.setAdapter(adapter);

		
		ImageView back=(ImageView) findViewById(R.id.back);
		back.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				finish();
			}
		});

	}

}
