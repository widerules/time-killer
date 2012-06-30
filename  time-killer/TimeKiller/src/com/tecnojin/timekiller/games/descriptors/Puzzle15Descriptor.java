package com.tecnojin.timekiller.games.descriptors;

import com.tecnojin.timekiller.R;
import com.tecnojin.timekiller.games.descriptors.options.Option;
import com.tecnojin.timekiller.games.descriptors.options.OptionSet;
import com.tecnojin.timekiller.games.descriptors.options.Stat;
import com.tecnojin.timekiller.games.descriptors.options.StatSet;
import com.tecnojin.timekiller.games.descriptors.options.Tutorial;
import com.tecnojin.timekiller.games.descriptors.options.Tutorial.Page;
import com.tecnojin.timekiller.games.puzzle15.Puzzle15Activity;

import android.content.Context;

public class Puzzle15Descriptor extends GameDescriptor {
	public Puzzle15Descriptor(Context c){
		initTutorial();
		initOption(c);
		initStats(c);
		gameActivity=Puzzle15Activity.class;
	}

	private void initTutorial() {
		Page p1=new Page(R.string.puzzle15_title_1,R.drawable.work, R.string.puzzle15_text_1);
		Page p2=new Page(R.string.puzzle15_title_2,R.drawable.work, R.string.puzzle15_text_2);

		tutorial=new Tutorial(p1,p2);

	}
	private void initStats(Context c) {
		Stat playedEasy=new Stat(R.string.GamePlayedEasy, "playedE", 0+"");
		Stat terminatedEasy=new Stat(R.string.GameResolvedEasy, "terminatedE", 0+"");
		Stat percentEasy=new Stat(R.string.percentualEasy, "percentE", 0+"");
		
		Stat playedMedium=new Stat(R.string.GamePlayedMedium, "playedM", 0+"");
		Stat terminatedMedium=new Stat(R.string.GameResolvedMedium, "terminatedM", 0+"");
		Stat percentMedium=new Stat(R.string.percentualMedium, "percentM", 0+"");
		
		Stat playedMHard=new Stat(R.string.GamePlayedHard , "playedH", 0+"");
		Stat terminatedHard=new Stat(R.string.GameResolvedHard, "terminatedH", 0+"");
		Stat percentHard=new Stat(R.string.percentualHard, "percentH", 0+"");
		
		statistics=new StatSet("stat_puzzle15.txt",playedEasy,terminatedEasy,percentEasy,playedMedium,terminatedMedium,percentMedium,playedMHard,terminatedHard,percentHard);
		statistics.load(c);
		
	}

	private void initOption(Context c) {
		Option lang=new Option(Option.MULTIPLE, R.string.difficulty, "diff","2",new String [] {"1","2","3"} ,new int [] {R.string.easy,R.string.medium,R.string.hard}) ;
		options=new OptionSet("puzzle15.txt", lang);
		options.load(c);

	}

	@Override
	public int getName() {
		return R.string.puzzle15;
	}

	@Override
	public int getIcon() {
		return R.drawable.game;
	}

}
