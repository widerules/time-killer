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
import com.tecnojin.timekiller.R.drawable;
import com.tecnojin.timekiller.games.alignpuzzle.AlignPuzzleActivity;
import com.tecnojin.timekiller.games.descriptors.options.Stat;
import com.tecnojin.timekiller.games.descriptors.options.StatSet;
import com.tecnojin.timekiller.games.descriptors.options.Tutorial;
import com.tecnojin.timekiller.games.descriptors.options.Tutorial.Page;

public class AlignPuzzleDescriptor extends GameDescriptor {

	public AlignPuzzleDescriptor(Context c) {
		initTutorial();
		initStatistics(c);
		gameActivity=AlignPuzzleActivity.class;
	}

	private void initStatistics(Context c) {
		Stat played=new Stat(R.string.GamePlayed, "played", 0+"");
		Stat terminated=new Stat(R.string.GameResolved, "terminated", 0+"");
		Stat percent=new Stat(R.string.percentual, "percent", 0+"");	
		statistics=new StatSet("stat_align.txt", played,terminated,percent);
		statistics.load(c);
		
	}

	private void initTutorial() {
		Page p=new Page(R.string.align_title_1, R.drawable.work, R.string.align_text_1);
		Page p1=new Page(R.string.align_title_2, R.drawable.work, R.string.align_text_2);
		tutorial=new Tutorial(p,p1);		
	}

	@Override
	public int getName() {
		return R.string.align;
	}

	@Override
	public int getIcon() {
		return drawable.game;
	}

}
