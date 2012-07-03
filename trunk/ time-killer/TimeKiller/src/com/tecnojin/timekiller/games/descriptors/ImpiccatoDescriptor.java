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
import com.tecnojin.timekiller.games.impiccato.ImpiccatoActivity;

public class ImpiccatoDescriptor extends GameDescriptor{
	
	public ImpiccatoDescriptor(Context c){
		initSettings(c);
		initTutorial();
		initStatistics(c);
		gameActivity=ImpiccatoActivity.class;

	}
	private void initStatistics(Context c) {
		Stat triEng=new Stat(R.string.WordsTriedENG, "tryEng", 0+"",0);
		Stat takenEng=new Stat(R.string.WordsTakedENG, "takeEng", 0+"",0);
		Stat percentEng=new Stat(R.string.WordsPercentualENG, "percentEng", 0+"",0);		
		Stat triITA=new Stat(R.string.WordsTriedIta, "tryita", 0+"",1);
		Stat takenITA=new Stat(R.string.WordsTakedIta, "takeita", 0+"",1);
		Stat percentITA=new Stat(R.string.WordsPercentualIta, "percentita", 0+"",1);		
		
		statistics=new StatSet("stat_hang.txt",
				triEng,
				takenEng,
				percentEng
				
				,triITA,
				takenITA,
				percentITA);
		
		statistics.load(c);
		
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
