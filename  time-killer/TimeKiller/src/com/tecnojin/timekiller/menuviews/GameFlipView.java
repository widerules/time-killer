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
import android.support.v4.view.ViewPager;

import com.tecnojin.timekiller.games.descriptors.GameDescriptor;

public class GameFlipView extends ViewPager{
	private PagerAdapter adapter;
	public GameFlipView(Context context) {
		super(context);
		adapter=new GamePageAdapter(context);
		setAdapter(adapter);
	}
	public void rollTo(GameDescriptor g){
		int index=adapter.getItemPosition(g);
		if(index>=0)
			setCurrentItem(index);
	}
	
	

}
