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
package com.tecnojin.timekiller.games.descriptors;

import android.content.Context;

import com.tecnojin.timekiller.R;
import com.tecnojin.timekiller.games.descriptors.options.Stat;
import com.tecnojin.timekiller.games.descriptors.options.StatSet;
import com.tecnojin.timekiller.games.descriptors.options.Tutorial;
import com.tecnojin.timekiller.games.descriptors.options.Tutorial.Page;
import com.tecnojin.timekiller.games.frog.FrogActivity;

public class FrogLabirinthDescriptor extends GameDescriptor{

	public FrogLabirinthDescriptor(Context c) {
		gameActivity=FrogActivity.class;
		createTutorial();
		initStatistics(c);
	}

	private void createTutorial() {
		Page p1=new Page(R.string.frog_title_1,R.drawable.work, R.string.frog_text_1);
		Page p2=new Page(R.string.frog_title_2,R.drawable.work, R.string.frog_text_2);
		tutorial=new Tutorial(p1,p2);
		
	}
	private void initStatistics(Context c) {
		Stat played=new Stat(R.string.GamePlayed, "played", 0+"");
		Stat terminated=new Stat(R.string.GameResolved, "terminated", 0+"");
		Stat percent=new Stat(R.string.percentual, "percent", 0+"");	
		statistics=new StatSet("stat_frog.txt", played,terminated,percent);
		statistics.load(c);
		
	}


	@Override
	public int getName() {
		return R.string.frog;
	}

	@Override
	public int getIcon() {
		return R.drawable.game;
	}

}
