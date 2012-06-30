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
package com.tecnojin.timekiller.games.descriptors;

import com.tecnojin.timekiller.activity.GameActivity;
import com.tecnojin.timekiller.games.descriptors.options.OptionSet;
import com.tecnojin.timekiller.games.descriptors.options.Tutorial;

public abstract class GameDescriptor {

	protected OptionSet options;	
	protected Tutorial tutorial;	
	protected Class<? extends GameActivity> gameActivity;
	protected GameDescriptor (){}


	public abstract int getName();
	public abstract int getIcon();


	public OptionSet getOptions() {
		return options;
	}
	public Tutorial getTutorial() {
		return tutorial;
	}


	public boolean isReady() {
		return getTutorial()!=null;
	}


	public Class<?> getGameActivity() {
		return gameActivity;
	}


}
