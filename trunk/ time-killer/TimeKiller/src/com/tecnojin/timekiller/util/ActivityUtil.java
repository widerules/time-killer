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
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

public class ActivityUtil {
	public static final String FONTS_GAME_OVER="fonts/game_over.ttf";
	public static final String FONTS_BATES_SHOWER="fonts/bates_shower.ttf";
	public static final String FONTS_ACID_DL="fonts/acid_dl.otf";
	public static final String FONTS_DIRTY_EGO="fonts/dirty_ego.ttf";
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
	
	public static void playAnimation(Context context,int animationId,View v){
		Animation a=AnimationUtils.loadAnimation(context, animationId);
		v.startAnimation(a);
	}
	public static void setFont(TextView t,String font){
		Typeface type=Typeface.createFromAsset(t.getContext().getAssets(),font);
		t.setTypeface(type);
	}
	public static void showKeyboard(Context c,View v){
		InputMethodManager mgr = (InputMethodManager) c.getSystemService(Context.INPUT_METHOD_SERVICE);
		mgr.showSoftInput(v, InputMethodManager.SHOW_IMPLICIT);
	}
	public static void hideKeyboard(Context c,View v){
		InputMethodManager mgr = (InputMethodManager) c.getSystemService(Context.INPUT_METHOD_SERVICE);
		mgr.hideSoftInputFromInputMethod(v.getWindowToken(),0);
	}
}
