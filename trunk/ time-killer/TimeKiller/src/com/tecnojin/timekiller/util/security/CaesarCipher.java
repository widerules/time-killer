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
package com.tecnojin.timekiller.util.security;


public class CaesarCipher {
	private int code;

	public CaesarCipher(int code) {
		super();
		this.code = code;
	}	
	public String code(String string){
		return transform(string,code);
	}
	public String decode(String string){
		return transform(string, -code);
	}
	private String transform(String string, int c) {
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<string.length();i++){
			int a=string.charAt(i);
			a+=c;
			char b=(char) a;
			sb.append(b);
		}
		return sb.toString();
	}
	
	
	

}
