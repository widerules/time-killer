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
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.tecnojin.timekiller.R;
import com.tecnojin.timekiller.dialogs.FindGameDialog;
import com.tecnojin.timekiller.dialogs.FindGameDialog.gameFindListener;
import com.tecnojin.timekiller.games.GameManager;
import com.tecnojin.timekiller.games.descriptors.GameDescriptor;
import com.tecnojin.timekiller.games.descriptors.options.Option;
import com.tecnojin.timekiller.menuviews.GameFlipView;
import com.tecnojin.timekiller.menuviews.GameListView;
import com.tecnojin.timekiller.util.ActivityUtil;

public class MainActivity extends Activity {
	private GameFlipView gamesPage;
	private GameListView gamesList;
	private static final String SHARED_PAGE="shared_page";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActivityUtil.makeFullScreen(this);

	}
	@Override
	protected void onStart() {
		super.onStart();
		init();
	}
	private void init() {

		setContentView(R.layout.game_page_layout);
		LinearLayout l=(LinearLayout) findViewById(R.id.container);

		if(page()){
			gamesPage=new GameFlipView(this);
			l.addView(gamesPage);
		}
		else{
			gamesList=new GameListView(this);
			l.addView(gamesList);
		}

		ImageView settingsImageView=(ImageView) findViewById(R.id.settings);

		settingsImageView.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				startActivity(new Intent(MainActivity.this,OptionActivity.class));
			}
		});
		ImageView searc=(ImageView) findViewById(R.id.search);
		searc.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				new FindGameDialog(MainActivity.this, new gameFindListener() {

					public void gameFound(GameDescriptor g) {
						if(gamesPage!=null)
							gamesPage.rollTo(g);
						if(gamesList!=null)							
							gamesList.rollTo(g);
						

					}
				}).show();

			}
		});

	}
	private boolean page() {
		Option o=GameManager.instance(this).getGlobalOptions().findOptionForKey("style");
		String s=o.getCurrentValue();
		return s.equals("pages");
	}
	@Override
	protected void onPause() {
		SharedPreferences s=getSharedPreferences(SHARED_PAGE,Context.MODE_WORLD_READABLE);
		Editor e=s.edit();
		if(gamesPage!=null)
			e.putInt("page", gamesPage.getCurrentItem());
		if(gamesList!=null)
			e.putInt("list", gamesList.getFirstVisiblePosition());
		e.commit();
		super.onPause();

	}
	@Override
	protected void onResume() {
		SharedPreferences s=getSharedPreferences(SHARED_PAGE,Context.MODE_WORLD_READABLE);
		int a=s.getInt("page", -1);
		int b=s.getInt("list", -1);
		if(a>0 && gamesPage!=null)
			gamesPage.setCurrentItem(a);
		if(b>0 && gamesList!=null)
			gamesList.setSelection(b);		
		super.onResume();
	}
	@Override
	protected void onDestroy() {
		SharedPreferences s=getSharedPreferences(SHARED_PAGE,Context.MODE_WORLD_READABLE);
		Editor e=s.edit();
		e.clear();
		e.commit();
		super.onDestroy();
	}

}
