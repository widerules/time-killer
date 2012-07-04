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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tecnojin.timekiller.R;
import com.tecnojin.timekiller.games.GameManager;
import com.tecnojin.timekiller.games.descriptors.GameDescriptor;
import com.tecnojin.timekiller.menuviews.GamePageAdapter.myOnclickListener;
import com.tecnojin.timekiller.util.ActivityUtil;

public class GameListAdapter extends ArrayAdapter<GameDescriptor>{
	private LayoutInflater li;

	private boolean [] back;
	private Context context;

	public GameListAdapter(Context context, int textViewResourceId) {
		super(context, textViewResourceId);
		li=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		back=new boolean [GameManager.instance(context).getGameNumber()];
		this.context=context;

	}

	@Override
	public int getPosition(GameDescriptor g) {
		for(int i=0;i<getCount();i++)
			if(GameManager.instance(context).getGameFromIndex(context, i).equals(g))
				return i;
			return -1;
	}
	@Override
	public GameDescriptor getItem(int position) {
		return GameManager.instance(getContext()).getGameFromIndex(getContext(), position);
	}
	@Override
	public int getCount() {
		return GameManager.instance(getContext()).getGameNumber();
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		GameDescriptor g=GameManager.instance(getContext()).getGameFromIndex(getContext(), position);
		if(v==null) {
			v=li.inflate(R.layout.game_list_item_front, null);
		}
		if(back[position]){
			v=li.inflate(R.layout.game_list_item_back, null);
			TextView name=(TextView) v.findViewById(R.id.game_name);
			name.setText(g.getName());
			ImageView settings=(ImageView) v.findViewById(R.id.settings);
			ImageView stat=(ImageView) v.findViewById(R.id.stat);
			ImageView info=(ImageView) v.findViewById(R.id.tutorial);	
			
			settings.setOnClickListener(new myOnclickListener(myOnclickListener.SETTINGS,GameManager.instance(context).getIndexFor(g),context));
			info.setOnClickListener(new myOnclickListener(myOnclickListener.TUTORIAL,GameManager.instance(context).getIndexFor(g),context));
			stat.setOnClickListener(new myOnclickListener(myOnclickListener.STATISTICS,GameManager.instance(context).getIndexFor(g),context));
			
			if(g.getStatistics()==null)
				stat.setVisibility(View.INVISIBLE);
		}
		else{
			v=li.inflate(R.layout.game_list_item_front, null);
			ImageView icon=(ImageView) v.findViewById(R.id.gameIcon);
			icon.setOnClickListener(new myOnclickListener(myOnclickListener.PLAY,GameManager.instance(context).getIndexFor(g),context));
			TextView name=(TextView) v.findViewById(R.id.game_name);
			ActivityUtil.setFont(name, ActivityUtil.FONTS_BATES_SHOWER);
			if(g.isReady())
			icon.setImageResource(g.getIcon());
			else
				icon.setImageResource(R.drawable.work);
			name.setText(g.getName());

		}
		return v;
	}
	public void touchOn(int index){
		if(GameManager.instance(getContext()).getGameFromIndex(getContext(),index).isReady())
			back[index]=!back[index];


	}
}
