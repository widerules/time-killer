package com.tecnojin.timekiller.games.descriptors;

import android.content.Context;

import com.tecnojin.timekiller.R;
import com.tecnojin.timekiller.games.descriptors.options.Option;
import com.tecnojin.timekiller.games.descriptors.options.OptionSet;
import com.tecnojin.timekiller.games.descriptors.options.Tutorial;
import com.tecnojin.timekiller.games.descriptors.options.Tutorial.Page;
import com.tecnojin.timekiller.games.memory.MemoryActivity;



public class MemoryDescriptor extends GameDescriptor{

	public MemoryDescriptor(Context c){
		initOption(c);
		initTutorial();
		gameActivity=MemoryActivity.class;
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
