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


public class OptionSet implements Iterable<Option> {
	private String optionFileName;	
	private Properties p;	
	private List<Option> options;	
	

	public OptionSet(String optionFileName,Option ... options) {
		super();
		this.optionFileName = optionFileName;
		this.options =new LinkedList<Option>();
		Collections.addAll(this.options, options);
	}
	
	public void load(Context c){
		try {
			FileInputStream fis=c.openFileInput(optionFileName);
			p=new Properties();
			p.load(fis);
			for(Object ob:p.keySet()){
				String key=ob.toString();
				Option o =findOptionForKey(key);				
				if(o!=null)
					o.setCurrentValue(p.getProperty(key));
			}
			boolean b=false;
			for(Option o:options)
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



	public Option findOptionForKey(String key) {
		for(Option o:options)
			if(key.equals(o.getKey()))
				return o;
		return null;
	}

	private void initDefault(Context c) {
		p=new Properties();
		for(Option o:options)
			p.setProperty(o.getKey(),o.getCurrentValue());
		save(c);
		
	}

	public void save(Context c) {
		try {
			for(Option o:options)
				p.put(o.getKey(), o.getCurrentValue());
			FileOutputStream fos=c.openFileOutput(optionFileName, Context.MODE_PRIVATE);
			p.store(fos, "");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public Iterator<Option> iterator() {
		return options.iterator();
	}
	public Option getOptionAt(int index){
		return options.get(index);
	}
	public int getOptionCount(){
		return options.size();
	}
	

	

}
