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
package com.tecnojin.timekiller.util;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.Window;
import android.view.WindowManager;

public class ActivityUtil {
	public static void makeFullScreen(Activity a) {
		a.requestWindowFeature(Window.FEATURE_NO_TITLE);
		a.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

	}
	public static LayoutInflater getLayoutInflater(Context c){
		return (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	public static String getStrings(Context c,int ... id){
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<id.length;i++)
			sb.append(c.getResources().getString(id[i])+" ");
		return sb.toString();
	}
}
