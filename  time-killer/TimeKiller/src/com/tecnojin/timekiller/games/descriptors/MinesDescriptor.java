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
import com.tecnojin.timekiller.games.descriptors.options.Tutorial;
import com.tecnojin.timekiller.games.descriptors.options.Tutorial.Page;
import com.tecnojin.timekiller.games.mines.MinesActivity;

public class MinesDescriptor extends GameDescriptor {
	
	public MinesDescriptor(Context c){
		initOptions(c);
		initTutorial();
		gameActivity=MinesActivity.class;
	}

	private void initTutorial() {
		Page p1=new Page(R.string.mines_title_1,R.drawable.work, R.string.mines_text_1);
		
		tutorial=new Tutorial(p1);
		
	}

	private void initOptions(Context c) {
		Option lang=new Option(Option.MULTIPLE, R.string.difficulty, "diff","2",new String [] {"1","2","3"} ,new int [] {R.string.easy,R.string.medium,R.string.hard}) ;
		options=new OptionSet("mines.txt", lang);
		options.load(c);
		
	}

	@Override
	public int getName() {
		return R.string.mines;
	}

	@Override
	public int getIcon() {
		return R.drawable.game;
	}

}