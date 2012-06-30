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
			ImageView play=(ImageView) v.findViewById(R.id.play);
			ImageView info=(ImageView) v.findViewById(R.id.tutorial);	
			
			play.setOnClickListener(new myOnclickListener(myOnclickListener.PLAY,GameManager.instance(context).getIndexFor(g),context));
			settings.setOnClickListener(new myOnclickListener(myOnclickListener.SETTINGS,GameManager.instance(context).getIndexFor(g),context));
			info.setOnClickListener(new myOnclickListener(myOnclickListener.TUTORIAL,GameManager.instance(context).getIndexFor(g),context));
		}
		else{
			v=li.inflate(R.layout.game_list_item_front, null);
			ImageView icon=(ImageView) v.findViewById(R.id.gameIcon);
			TextView name=(TextView) v.findViewById(R.id.game_name);
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
