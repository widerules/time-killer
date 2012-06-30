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
import com.tecnojin.timekiller.games.sudoku.SudokuActivity;


public class SudokuDescriptor extends GameDescriptor{

	public SudokuDescriptor (Context c){
		gameActivity=SudokuActivity.class;
		initTutorial();
		initOptions(c);
	}	

	private void initOptions(Context c) {
		Option lang=new Option(Option.MULTIPLE, R.string.difficulty, "diff","2",new String [] {"1","2","3"} ,new int [] {R.string.easy,R.string.medium,R.string.hard}) ;
		options=new OptionSet("sudoku.txt", lang);
		options.load(c);
		
	}

	private void initTutorial() {
		Page p1=new Page(R.string.sudoku_title_1,R.drawable.work, R.string.sudoku_text_1);
		
		tutorial=new Tutorial(p1);
		
		
		
	}

	@Override
	public int getName() {
		return R.string.sudoku;
	}

	@Override
	public int getIcon() {
		return R.drawable.game;
	}

	

	
}
