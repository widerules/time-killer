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

import java.util.List;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.tecnojin.timekiller.R;
import com.tecnojin.timekiller.games.descriptors.options.Stat;
import com.tecnojin.timekiller.games.descriptors.options.StatSet;
import com.tecnojin.timekiller.util.ActivityUtil;

public class StatisticsAdapter extends PagerAdapter{
	private StatSet set;
	private LayoutInflater li;
	private Context c;

	public StatisticsAdapter(Context context, int textViewResourceId,StatSet set) {
		this.set=set;
		li=ActivityUtil.getLayoutInflater(context);
		this.c=context;
	}


	@Override
	public int getCount() {
		return set.getPageCount();
	}
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		if(object instanceof View)
			container.removeView((View)object);
	}
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		View v = null;

		if(v==null){
			v=li.inflate(R.layout.stats_page_layout,null);
			TextView t=(TextView) v.findViewById(R.id.pageNumber);
			ActivityUtil.setFont(t, ActivityUtil.FONTS_ACID_DL);
			t.setText(getPageNumber(position+1));
			t.setVisibility(View.INVISIBLE);
			ListView ll=(ListView) v.findViewById(R.id.list);
			ll.setAdapter(new StateAdapter(c,R.layout.statitem_layout,set.getFromPage(position)));
			container.addView(v);


		}

		return v;
	}

	private String getPageNumber(int a) {
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<a;i++)
			sb.append(".");
		return sb.toString();
		
	}


	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0==arg1;
	}

	class StateAdapter extends ArrayAdapter<Stat> {
		private List<Stat> l;
		public StateAdapter(Context context, int textViewResourceId,List<Stat> list) {
			super(context, textViewResourceId);
			this.l=list;
		}
		@Override
		public int getCount() {
			return l.size();
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if(convertView==null){
				Stat s=l.get(position);
	
				convertView=li.inflate(R.layout.statitem_layout, null);

				TextView optionName=(TextView) convertView.findViewById(R.id.optionName);
				TextView option2=(TextView) convertView.findViewById(R.id.option2);



				optionName.setText(s.getViewName());
				option2.setText(s.getCurrentValue());	 
			}
			return convertView;
		}

	}

}
