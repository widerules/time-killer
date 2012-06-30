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

import java.util.Random;

public class MathUtil {
	public static int random(int from,int to){
		Random rn = new Random();
		
		int n = to - from + 1;
		int i = rn.nextInt() % n;
		return from + i;
	}
}
