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
import com.tecnojin.timekiller.games.impiccato.ImpiccatoActivity;

public class ImpiccatoDescriptor extends GameDescriptor{
	
	public ImpiccatoDescriptor(Context c){
		initSettings(c);
		initTutorial();
		gameActivity=ImpiccatoActivity.class;

	}
	private void initTutorial() {
		Page p=new Page(R.string.sudoku_title_1, R.drawable.work, R.string.hang_text);
		tutorial=new Tutorial(p);
		
	}
	private void initSettings(Context c) {
		Option lang=new Option(Option.MULTIPLE, R.string.language, "lang","eng",new String [] {"eng","ita"} ,new int [] {R.string.english,R.string.italian,}) ;
		options=new OptionSet("hang.txt", lang);
		options.load(c);
		
	}
	@Override
	public int getName() {
		return R.string.hangman;
	}

	@Override
	public int getIcon() {
		return R.drawable.game;
	}

}
