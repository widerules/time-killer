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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.tecnojin.timekiller.R;
import com.tecnojin.timekiller.games.GameManager;
import com.tecnojin.timekiller.games.descriptors.options.OptionSet;
import com.tecnojin.timekiller.menuviews.OptionAdapter;
import com.tecnojin.timekiller.util.ActivityUtil;

public class OptionActivity extends Activity {
	private OptionSet options;
	private OptionAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActivityUtil.makeFullScreen(this);
		setContentView(R.layout.option_layout);
		ListView l=(ListView) findViewById(R.id.optionList);

		int optionIndex=getIntent().getIntExtra(getPackageName()+".option", -1);
		if(optionIndex<0)
			options=GameManager.instance(this).getGlobalOptions();
		else
			options=GameManager.instance(this).getGame(optionIndex, this).getOptions();

		if(options==null){
			Toast.makeText(this, R.string.noOption, Toast.LENGTH_SHORT).show();
			finish();
		}
		adapter=new OptionAdapter(this, android.R.layout.simple_list_item_single_choice, options,this);
		l.setAdapter(adapter);

		l.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				adapter.clickOn(arg2);
				

			}

		});
		ImageView back=(ImageView) findViewById(R.id.back);
		back.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				finish();
			}
		});

	}

}
