
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
package com.tecnojin.timekiller.games.descriptors.options;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import android.content.Context;
import android.util.Log;


public class StatSet implements Iterable<Stat> {
	private String optionFileName;	
	private Properties p;	
	private List<Stat> stats;	
	

	public StatSet(String optionFileName,Stat ... stats) {
		super();
		this.optionFileName = optionFileName;
		this.stats =new LinkedList<Stat>();
		Collections.addAll(this.stats, stats);
	}
	
	public void load(Context c){
		try {
			FileInputStream fis=c.openFileInput(optionFileName);
			p=new Properties();
			p.load(fis);
			for(Object ob:p.keySet()){
				String key=ob.toString();
				Stat o =findStatForKey(key);				
				if(o!=null)
					o.setCurrentValue(p.getProperty(key));
			}
			boolean b=false;
			for(Stat o:stats)
				if(!p.contains(o.getKey())){
					p.put(o.getKey(), o.getCurrentValue());
					b=true;
				}
			if(b)
				save(c);
		} catch (FileNotFoundException e) {
			initDefault(c);			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}



	public Stat findStatForKey(String key) {
		for(Stat o:stats)
			if(key.equals(o.getKey()))
				return o;
		return null;
	}

	private void initDefault(Context c) {
		p=new Properties();
		for(Stat o:stats)
			p.setProperty(o.getKey(),o.getCurrentValue());
		save(c);
		
	}

	public void save(Context c) {
		try {
			for(Stat o:stats)
				p.put(o.getKey(), o.getCurrentValue());
			FileOutputStream fos=c.openFileOutput(optionFileName, Context.MODE_PRIVATE);
			p.store(fos, "");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public Iterator<Stat> iterator() {
		return stats.iterator();
	}
	public Stat getStatAt(int index){
		
		return stats.get(index);
	}
	public int getStatsCount(){
		return stats.size();
	}
	

	

}
