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

import com.tecnojin.timekiller.R;
import com.tecnojin.timekiller.games.descriptors.options.Option;
import com.tecnojin.timekiller.games.descriptors.options.Stat;
import com.tecnojin.timekiller.games.descriptors.options.StatSet;
import com.tecnojin.timekiller.menuviews.OptionAdapter.myCheckedChangeListener;
import com.tecnojin.timekiller.util.ActivityUtil;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.ToggleButton;

public class StatisticsAdapter extends ArrayAdapter<Stat>{
	private StatSet set;
	private LayoutInflater li;
	public StatisticsAdapter(Context context, int textViewResourceId,StatSet set) {
		super(context, textViewResourceId);
		this.set=set;
		li=ActivityUtil.getLayoutInflater(context);
	}
	
	@Override
	public Stat getItem(int position) {
		return set.getStatAt(position);
	}
	@Override
	public int getCount() {
		return set.getStatsCount();
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;

		Stat s=set.getStatAt(position);
		
		
			v=li.inflate(R.layout.statitem_layout,null);
			TextView optionName=(TextView) v.findViewById(R.id.optionName);
			TextView option2=(TextView) v.findViewById(R.id.option2);
			  

	
			optionName.setText(s.getViewName());
			option2.setText(s.getCurrentValue());	 


		

		return v;
	}


}
