package com.tecnojin.timekiller.games.descriptors;

import android.content.Context;

import com.tecnojin.timekiller.R;
import com.tecnojin.timekiller.games.descriptors.options.Option;
import com.tecnojin.timekiller.games.descriptors.options.OptionSet;
import com.tecnojin.timekiller.games.descriptors.options.Stat;
import com.tecnojin.timekiller.games.descriptors.options.StatSet;
import com.tecnojin.timekiller.games.descriptors.options.Tutorial;
import com.tecnojin.timekiller.games.descriptors.options.Tutorial.Page;
import com.tecnojin.timekiller.games.memory.MemoryActivity;



public class MemoryDescriptor extends GameDescriptor{

	public MemoryDescriptor(Context c){
		initOption(c);
		initTutorial();
		initStats(c);
		gameActivity=MemoryActivity.class;
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
		
		statistics=new StatSet("stat_memory.txt",playedEasy,terminatedEasy,percentEasy,playedMedium,terminatedMedium,percentMedium,playedMHard,terminatedHard,percentHard);
		statistics.load(c);
		
	}

	private void initOption(Context c) {
		Option lang=new Option(Option.MULTIPLE, R.string.difficulty, "diff","2",new String [] {"1","2","3"} ,new int [] {R.string.easy,R.string.medium,R.string.hard}) ;
		options=new OptionSet("memory.txt", lang);
		options.load(c);

	}
	private void initTutorial() {
		Page p1=new Page(R.string.memory_title_1,R.drawable.work, R.string.memory_text_1);

		tutorial=new Tutorial(p1);

	}

	@Override
	public int getName() {
		return R.string.memory;
	}

	@Override
	public int getIcon() {
		return R.drawable.game;
	}

}
