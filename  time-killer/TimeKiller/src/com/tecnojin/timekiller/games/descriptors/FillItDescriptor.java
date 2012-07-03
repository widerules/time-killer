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
import com.tecnojin.timekiller.games.descriptors.options.Option;
import com.tecnojin.timekiller.games.descriptors.options.OptionSet;
import com.tecnojin.timekiller.games.descriptors.options.Stat;
import com.tecnojin.timekiller.games.descriptors.options.StatSet;
import com.tecnojin.timekiller.games.descriptors.options.Tutorial;
import com.tecnojin.timekiller.games.descriptors.options.Tutorial.Page;
import com.tecnojin.timekiller.games.fillit.FillItActivity;

public class FillItDescriptor extends GameDescriptor{

	public FillItDescriptor(Context c){
		createOption(c);
		createTutorial();
		initStats(c);
		gameActivity=FillItActivity.class;
	}


	private void initStats(Context c) {
		Stat playedEasy=new Stat(R.string.GamePlayedEasy, "playedE", 0+"",0);
		Stat terminatedEasy=new Stat(R.string.GameResolvedEasy, "terminatedE", 0+"",0);
		Stat percentEasy=new Stat(R.string.percentualEasy, "percentE", 0+"",0);
		
		Stat playedMedium=new Stat(R.string.GamePlayedMedium, "playedM", 0+"",1);
		Stat terminatedMedium=new Stat(R.string.GameResolvedMedium, "terminatedM", 0+"",1);
		Stat percentMedium=new Stat(R.string.percentualMedium, "percentM", 0+"",1);
		
		Stat playedMHard=new Stat(R.string.GamePlayedHard , "playedH", 0+"",2);
		Stat terminatedHard=new Stat(R.string.GameResolvedHard, "terminatedH", 0+"",2);
		Stat percentHard=new Stat(R.string.percentualHard, "percentH", 0+"",2);
		
		statistics=new StatSet("stat_fillit.txt",playedEasy,terminatedEasy,percentEasy,playedMedium,terminatedMedium,percentMedium,playedMHard,terminatedHard,percentHard);
		statistics.load(c);
		
	}


	private void createTutorial() {
		Page p1=new Page(R.string.flood_title_1,R.drawable.work, R.string.flood_text_1);
		Page p2=new Page(R.string.flood_title_2,R.drawable.work, R.string.flood_text_2);
		tutorial=new Tutorial(p1,p2);
	}

	private void createOption(Context c) {
		Option lang=new Option(Option.MULTIPLE, R.string.difficulty, "diff","2",new String [] {"1","2","3"} ,new int [] {R.string.easy,R.string.medium,R.string.hard}) ;
		options=new OptionSet("fillit.txt", lang);
		options.load(c);
	}



	@Override
	public int getName() {
		return R.string.fillit;
	}

	@Override
	public int getIcon() {
		return R.drawable.game;
	}

}
