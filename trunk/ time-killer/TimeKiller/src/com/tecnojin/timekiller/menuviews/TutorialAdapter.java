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
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tecnojin.timekiller.R;
import com.tecnojin.timekiller.games.descriptors.options.Tutorial;
import com.tecnojin.timekiller.games.descriptors.options.Tutorial.Page;

public class TutorialAdapter extends PagerAdapter {
	private Tutorial t;
	private Context c;
	private LayoutInflater li;

	public TutorialAdapter(Tutorial tutorial,Context c) {
		this.t=tutorial;
		this.c=c;
		li=(LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);		
	}

	@Override
	public int getCount() {
		return t.getPageCount();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0==arg1;

	}
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		View v=li.inflate(R.layout.tutorial_page_layout, null);

		Page p=t.getPageAt(position);

		ImageView i=(ImageView) v.findViewById(R.id.tutorialImage);
		TextView title=(TextView) v.findViewById(R.id.tutorialTitle);
		TextView content=(TextView) v.findViewById(R.id.tutorialText);

		if(p.getImage()!=0)
			i.setImageResource(p.getImage());
		if(p.getTitle()!=0)
			title.setText(p.getTitle());
		else
			title.setVisibility(View.INVISIBLE);
		content.setText(p.getText());

		container.addView(v);

		return v;
	}

}
