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

import java.text.DecimalFormat;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;
import com.tecnojin.timekiller.R;
import com.tecnojin.timekiller.games.GameManager;
import com.tecnojin.timekiller.games.descriptors.GameDescriptor;
import com.tecnojin.timekiller.games.descriptors.options.Stat;
import com.tecnojin.timekiller.games.descriptors.options.StatSet;
import com.tecnojin.timekiller.util.ActivityUtil;

public abstract class GameActivity extends Activity {
	protected ImageView back;
	protected TextView name;
	protected GameDescriptor game;
	protected LinearLayout ads,gamelayout;
	private AlertDialog loadingDialog;
	private AlertDialog gameTerminatedDialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		int game=getIntent().getIntExtra(getPackageName()+".game", -1);
		ActivityUtil.makeFullScreen(this);
		if(game==-1){
			Toast.makeText(this, R.string.noTGame, Toast.LENGTH_SHORT).show();
			finish();
		}
		this.game=GameManager.instance(this).getGame(game, this);
		setContentView(R.layout.play_layout);
		back=(ImageView) findViewById(R.id.back);
		back.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				finish();

			}
		});

		name=(TextView) findViewById(R.id.gamename);
		name.setText(this.game.getName());

		ads=(LinearLayout) findViewById(R.id.ads_panel);

		gamelayout=(LinearLayout) findViewById(R.id.game_panel);

		startLoadingDialog();
		loadGame();
		stopLoadingDialog();

		manageAds();

	}
	private void stopLoadingDialog() {
		if(loadingDialog!=null)
			loadingDialog.dismiss();

	}
	public abstract void loadGame() ;

	public void startLoadingDialog() {	
		AlertDialog.Builder b=new AlertDialog.Builder(this);
		b.setTitle(R.string.loading);
		b.setCancelable(false);
		loadingDialog=b.create();
		loadingDialog.show();		
	}
	public void gameTerminated(){
		gameTerminated(null);
	}
	public void gameTerminated(String message){
		AlertDialog.Builder b=new AlertDialog.Builder(this);
		b.setTitle(R.string.gameTerminated);
		if(message==null)
			b.setMessage(R.string.playAgain);
		else
			b.setMessage(message);

		b.setCancelable(false);
		b.setNegativeButton(R.string.no, new android.content.DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				finish();

			}
		});
		b.setPositiveButton(R.string.yes, new android.content.DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				startActivity(getIntent());
				finish();

			}
		});
		gameTerminatedDialog=b.create();
		gameTerminatedDialog.show();

	}
	private void manageAds() {
		if(true)
			return;

		AdView adView = new AdView(this, AdSize.BANNER, "a9876sf98dfg");  	  
		ads.addView(adView);
		AdRequest request = new AdRequest();	   
		adView.loadAd(request);
	}

	protected void updateStatistics(boolean isTerminated,char difficult){
		try{
			StatSet s=game.getStatistics();

			String played="played"+difficult;
			String terminated="terminated"+difficult;
			String percent="percent"+difficult;

			double p=Integer.parseInt(s.findStatForKey(played).getCurrentValue());
			double t=Integer.parseInt(s.findStatForKey(terminated).getCurrentValue());

			p++;
			if(isTerminated){
				t++;
			}
			double per=0;
			
				per=t/p;
				per*=100;

				per=roundTwoDecimals(per);
			



			s.findStatForKey(played).setCurrentValue(((int)p)+"");
			s.findStatForKey(terminated).setCurrentValue(((int)t)+"");
			s.findStatForKey(percent).setCurrentValue(((int)per)+" %");

			s.save(this);

		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	double roundTwoDecimals(double d) {
		DecimalFormat twoDForm = new DecimalFormat("#.##");
		return Double.valueOf(twoDForm.format(d));
	}


}
