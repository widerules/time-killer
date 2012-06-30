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
package com.tecnojin.timekiller.games.impiccato;

import java.util.LinkedList;
import java.util.List;

public class Impiccato {
	public static final int CHAR_NOT_FOUND=1;
	public static final int CHAR_FOUND=2;
	public static final int CHAR_ALREADY_SUBMITTED=3;
	private char [] originalWord;
	private char [] currentWord;
	private List<Character> charactersTried;
	private int errors;

	public Impiccato(String word) {
		super();
		this.originalWord = word.toCharArray();
		charactersTried=new LinkedList<Character>();
		
		fillEmpty();
	}
	
	private void fillEmpty() {
		currentWord=new char [originalWord.length];
		for(int i=0;i<originalWord.length;i++)
			currentWord[i]='_';
		
	}

	public String getoriginalWord() {
		return new String(originalWord);
	}
	public int getErrors() {
		return errors;
	}
	public List<Character> getCharacter() {
		return charactersTried;
	}
	
	public int tryChar(char c){
		if(charactersTried.contains(c))
			return CHAR_ALREADY_SUBMITTED;
		charactersTried.add(c);
		
		boolean found=false;
		for(int i=0;i<originalWord.length;i++){
			if(originalWord[i]==c){
				found=true;
				currentWord[i]=c;
			}
		}
		if(found)
			return CHAR_FOUND;
		errors++;
		return CHAR_NOT_FOUND;
	}
	public String getCurrentWord(){
		return new String(currentWord);
	}

	public boolean isComplete() {
		for(int i=0;i<currentWord.length;i++)
			if(currentWord[i]=='_')
				return false;
		return true;
	}
	
	
}
