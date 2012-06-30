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
package com.tecnojin.timekiller.games;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import android.content.Context;

import com.tecnojin.timekiller.R;
import com.tecnojin.timekiller.games.descriptors.AlignPuzzleDescriptor;
import com.tecnojin.timekiller.games.descriptors.FillItDescriptor;
import com.tecnojin.timekiller.games.descriptors.FrogLabirinthDescriptor;
import com.tecnojin.timekiller.games.descriptors.GameDescriptor;
import com.tecnojin.timekiller.games.descriptors.ImpiccatoDescriptor;
import com.tecnojin.timekiller.games.descriptors.MemoryDescriptor;
import com.tecnojin.timekiller.games.descriptors.MinesDescriptor;
import com.tecnojin.timekiller.games.descriptors.Puzzle15Descriptor;
import com.tecnojin.timekiller.games.descriptors.SudokuDescriptor;
import com.tecnojin.timekiller.games.descriptors.options.Option;
import com.tecnojin.timekiller.games.descriptors.options.OptionSet;
import com.tecnojin.timekiller.games.puzzle15.Puzzle15State;

public class GameManager {
	public static final int FILL_IT=1;
	public static final int SUDOKU=2;
	public static final int HANGMAN=3;
	public static final int ALIGN_PUZZLE=4;
	public static final int FROG_LABIRINTH=5;
	public static final int MINES=6;
	public static final int MEMORY=7;
	public static final int PUZZLE15=8;

	private static GameManager instance;

	private static SudokuDescriptor sudoku;
	private static FillItDescriptor fillIt;
	private static ImpiccatoDescriptor hangman;
	private static AlignPuzzleDescriptor align;
	private static FrogLabirinthDescriptor frog;
	private static MinesDescriptor mines;
	private static MemoryDescriptor memory;
	private static Puzzle15Descriptor puzzle15;


	private OptionSet globalOptionSet;

	private GameManager(Context c){
		initGlobalOptionSet(c);
	}

	private void initGlobalOptionSet(Context c) {
		Option sounds=new Option(Option.ON_OFF, R.string.sound, "sound","true",new String [] {"true","false"} ,null) ;
		Option vibrate=new Option(Option.ON_OFF, R.string.vibrate, "vibrate","true",new String [] {"true","false"} ,null) ;
		Option style=new Option(Option.MULTIPLE, R.string.gameSelectStyle, "style","pages",new String [] {"pages","list"} ,new int [] {R.string.pages,R.string.list}) ;


		globalOptionSet=new OptionSet("global.txt", sounds,vibrate,style);
		globalOptionSet.load(c);

	}

	public static synchronized GameManager instance(Context c){
		if(instance==null)
			instance=new GameManager(c);
		return instance;
	}

	public synchronized GameDescriptor getGame(int GameCode,Context c){
		switch(GameCode){
		case SUDOKU:
			if(sudoku==null) sudoku=new SudokuDescriptor(c);
			return sudoku;
		case FILL_IT:
			if(fillIt==null) fillIt=new FillItDescriptor(c);
			return fillIt;		
		case HANGMAN:
			if(hangman==null) hangman=new ImpiccatoDescriptor(c);
			return hangman;	
		case ALIGN_PUZZLE:
			if(align==null) align=new AlignPuzzleDescriptor(c);
			return align;	
		case FROG_LABIRINTH:
			if(frog==null) frog=new FrogLabirinthDescriptor(c);
			return frog;	
		case MINES:
			if(mines==null) mines=new MinesDescriptor(c);
			return mines;	
		case MEMORY:
			if(memory==null) memory=new MemoryDescriptor(c);
			return memory;	
		case PUZZLE15:
			if(puzzle15==null) puzzle15=new Puzzle15Descriptor(c);
			return puzzle15;	
		}
		return null;
	}
	public synchronized int getGameNumber(){
		return 8;
	}

	public GameDescriptor getGameFromIndex(Context c, int position) {
		switch (position) {
		case 0:
			return getGame(SUDOKU, c);
		case 1:
			return getGame(FILL_IT, c);
		case 2:
			return getGame(HANGMAN, c);
		case 3:
			return getGame(ALIGN_PUZZLE, c);
		case 4:
			return getGame(FROG_LABIRINTH, c);
		case 5:
			return getGame(MINES, c);
		case 6:
			return getGame(MEMORY, c);
		case 7:
			return getGame(PUZZLE15, c);
		}
		return null;
	}
	public OptionSet getGlobalOptions() {
		return globalOptionSet;
	}

	public int getIndexFor(GameDescriptor descriptor) {
		if(descriptor==(sudoku))
			return SUDOKU;
		if(descriptor==fillIt)
			return FILL_IT;
		if(descriptor==hangman)
			return HANGMAN;
		if(descriptor==align)
			return ALIGN_PUZZLE;
		if(descriptor==frog)
			return FROG_LABIRINTH;
		if(descriptor==mines)
			return MINES;
		if(descriptor==memory)
			return MEMORY;
		if(descriptor==puzzle15)
			return PUZZLE15;
		return -2;
	}
	public List<String> getGameNameList(){
		LinkedList<String> l=new LinkedList<String>();
		Collections.addAll(l, "sudoku","fill it","hangman","align","frog","mines","memory","puzzle15");
		return l;
	}

}
