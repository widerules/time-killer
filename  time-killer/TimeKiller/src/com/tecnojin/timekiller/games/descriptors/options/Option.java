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


public class Option {
	public static final int ON_OFF=1;
	public static final int MULTIPLE=2;
	public static final int NOTHING=3;
	
	private int type;
	private int viewName;
	private String key;
	private String currentValue;
	private String [] possiblesValue;
	private int [] optionsViewName;
	
	public Option(int type, int viewName, String key, String currentValue,
			String[] possiblesValue, int[] optionsViewName) {
		super();
		this.type = type;
		this.viewName = viewName;
		this.key = key;
		this.currentValue = currentValue;
		this.possiblesValue = possiblesValue;
		this.optionsViewName = optionsViewName;
	}
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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

	public String[] getPossiblesValue() {
		return possiblesValue;
	}

	public void setPossiblesValue(String[] possiblesValue) {
		this.possiblesValue = possiblesValue;
	}

	public int[] getOptionsViewName() {
		return optionsViewName;
	}

	public void setOptionsViewName(int[] optionsViewName) {
		this.optionsViewName = optionsViewName;
	}
	
	public int getViewStringForValue(String value){
		for(int i=0;i<possiblesValue.length;i++)
			if(possiblesValue[i].equals(value))
				return optionsViewName[i];
		return -1;
	}
	
	
	
	
	
	

}
