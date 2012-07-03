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


public class Stat {

	private int viewName;
	private String key;
	private String currentValue;	
	private int page=0;


	public Stat(int viewName, String key, String currentValue) {
		super();
		this.viewName = viewName;
		this.key = key;
		this.currentValue = currentValue;
	}

	public Stat(int viewName, String key, String currentValue,int page) {
		super();
		this.viewName = viewName;
		this.key = key;
		this.currentValue = currentValue;
		this.page=page;
	}



	public int getViewName() {
		return viewName;
	}

	public void setViewName(int viewName) {
		this.viewName = viewName;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(String currentValue) {
		this.currentValue = currentValue;
	}
	@Override
	public boolean equals(Object o) {
		if(! (o instanceof Stat))
			return false;
		Stat t=(Stat) o;
		return t.key.equals(key);
	}
	public int getPage() {
		return page;
	}






}
