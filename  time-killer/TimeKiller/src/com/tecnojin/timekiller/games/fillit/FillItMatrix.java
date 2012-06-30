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

public class FillItMatrix extends AbstractMatrix<Integer> {
	int cont=0;
	private FillItState state;
	private HashMap<Integer, Integer> colors;
	public FillItMatrix(Context context,FillItState state,HashMap<Integer, Integer> colors) {
		super(context, state.getRows(), state.getCols());
		this.state=state;
		this.colors=colors;
		setSeparatorProportion(0);
		setSquare(true);
		
	}

	@Override
	public Cell<Integer> createCell(int row, int col) {
		BackGroundCell<Integer> b =new BackGroundCell<Integer>(row, col, cont++);
		b.setBackgroundColor(colors.get(state.getColorAt(row, col)));		
		return b;
	}
	
	public void updateState(){
		for(int i=0;i<state.getRows();i++)
			for(int j=0;j<state.getCols();j++)
				((BackGroundCell<Integer>)getCell(i, j)).setBackgroundColor(colors.get(state.getColorAt(i, j)));		
		invalidate();
	}
	

}
