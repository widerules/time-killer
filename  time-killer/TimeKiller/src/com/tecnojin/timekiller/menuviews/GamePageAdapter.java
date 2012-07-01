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
package com.tecnojin.timekiller.menuviews;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.tecnojin.timekiller.R;
import com.tecnojin.timekiller.activity.OptionActivity;
import com.tecnojin.timekiller.activity.StatisticsActivity;
import com.tecnojin.timekiller.activity.TutorialActivity;
import com.tecnojin.timekiller.games.GameManager;
import com.tecnojin.timekiller.games.descriptors.GameDescriptor;
import com.tecnojin.timekiller.util.ActivityUtil;

public class GamePageAdapter extends PagerAdapter{
	private Context context;
	private LayoutInflater inflater;

	public GamePageAdapter (Context c){
		this.context=c;
		inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return GameManager.instance(context).getGameNumber();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {

		GameDescriptor descriptor=GameManager.instance(context).getGameFromIndex(context,position);

		View layout = inflater.inflate(R.layout.game_page, null);

		TextView t=(TextView) layout.findViewById(R.id.game_name);
		ImageView icon=(ImageView) layout.findViewById(R.id.gameIcon);
		
		ImageView sett=(ImageView) layout.findViewById(R.id.settings);
		ImageView tutorial=(ImageView) layout.findViewById(R.id.tutorial);
		ImageView stat=(ImageView) layout.findViewById(R.id.stat);

		if(descriptor.getName()!=0){
			t.setText(descriptor.getName());
			ActivityUtil.setFont(t, ActivityUtil.FONTS_BATES_SHOWER);
			if(descriptor.isReady())
				icon.setImageResource(descriptor.getIcon());
			else
				icon.setImageResource(R.drawable.work);
			
			
			if(!descriptor.isReady()){
				tutorial.setEnabled(false);
				icon.setEnabled(false);
			}
			if(!descriptor.isReady() || descriptor.getOptions()==null)
				sett.setEnabled(false);
			if(descriptor.getStatistics()==null)
				stat.setVisibility(View.INVISIBLE);
			
		}
		icon.setOnClickListener(new myOnclickListener(myOnclickListener.PLAY,GameManager.instance(context).getIndexFor(descriptor),context));
		sett.setOnClickListener(new myOnclickListener(myOnclickListener.SETTINGS,GameManager.instance(context).getIndexFor(descriptor),context));
		tutorial.setOnClickListener(new myOnclickListener(myOnclickListener.TUTORIAL,GameManager.instance(context).getIndexFor(descriptor),context));
		stat.setOnClickListener(new myOnclickListener(myOnclickListener.STATISTICS,GameManager.instance(context).getIndexFor(descriptor),context));
		container.addView(layout);



		return layout;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		if(object instanceof View)
			container.removeView((View)object);
	}

	static class myOnclickListener implements OnClickListener{
		public static final int STATISTICS = 4;
		public static final int SETTINGS=1;
		public static final int TUTORIAL=2;
		public static final int PLAY=3;
		
		private int gameCode;
		private Context context;
		private int mode;


		public myOnclickListener(int mode, int gameCode,Context c) {
			super();
			this.mode=mode;
			this.gameCode = gameCode;
			this.context=c;
		}


		public void onClick(View arg0) {
				ActivityUtil.playAnimation(context, R.anim.push, arg0);	
			if(mode==SETTINGS){
				Intent i=new Intent(context,OptionActivity.class);
				i.putExtra(context.getPackageName()+".option", gameCode);
				context.startActivity(i);
			}
			if(mode==STATISTICS){
				Intent i=new Intent(context,StatisticsActivity.class);
				i.putExtra(context.getPackageName()+".statistics", gameCode);
				context.startActivity(i);
			}
			if(mode==TUTORIAL){
				Intent i=new Intent(context,TutorialActivity.class);
				i.putExtra(context.getPackageName()+".tutorial", gameCode);
				context.startActivity(i);
			}
			if(mode==PLAY){
				Class c=GameManager.instance(context).getGame(gameCode, context).getGameActivity();
				if(c==null)
					return;
				Intent i=new Intent(context,c);
				i.putExtra(context.getPackageName()+".game", gameCode);
				context.startActivity(i);
			}
			


		}





	}

}
