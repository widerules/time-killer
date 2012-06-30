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
package com.tecnojin.timekiller.games.fillit;

import java.util.HashMap;

import android.content.Context;

import com.tecnojin.timekiller.views.matrix.AbstractMatrix;
import com.tecnojin.timekiller.views.matrix.BackGroundCell;
import com.tecnojin.timekiller.views.matrix.Cell;

public class FillItVector extends AbstractMatrix<Integer>{
	private int cont=0;
	private HashMap<Integer, Integer> colors;

	public FillItVector(Context context,HashMap<Integer, Integer> colors) {
		super(context, 1, colors.keySet().size());
		this.colors=colors;
		setSeparatorProportion(0.05);
		double a=1;
		double b=colors.keySet().size();
		setHeightWidhtProportion(a/b);
		setSquare(false);
		
		
	}

	@Override
	public Cell<Integer> createCell(int row, int col) {
		BackGroundCell<Integer> b =new BackGroundCell<Integer>(row, col, cont);
		b.setBackgroundColor(colors.get(cont++));
		return b;
	}

}
