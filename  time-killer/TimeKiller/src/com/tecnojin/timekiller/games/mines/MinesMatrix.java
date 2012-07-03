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
package com.tecnojin.timekiller.games.mines;

import android.content.Context;
import android.graphics.Color;

import com.tecnojin.timekiller.views.matrix.AbstractMatrix;
import com.tecnojin.timekiller.views.matrix.Cell;
import com.tecnojin.timekiller.views.matrix.TextCell;

public class MinesMatrix extends AbstractMatrix<Integer> {
	private MinesState state;
	private boolean [][] flags;

	public MinesMatrix(Context context, MinesState state) {
		super(context, state.getRows(), state.getCols());
		this.state=state;
		flags=new boolean [state.getRows()][state.getCols()];
	}

	@Override
	public Cell<Integer> createCell(int row, int col) {
		TextCell<Integer> c=new TextCell<Integer>(row, col,1, "");
		updateCell(c,row,col);
		return c;
	}
	public void updadeState(){
		for(int i=0;i<state.getRows();i++)
			for(int j=0;j<state.getCols();j++)
				updateCell(getCell(i, j),i, j);
		invalidate();
	}


	private void updateCell(Cell<Integer> cell, int row, int col) {
		TextCell<Integer> t=(TextCell<Integer>) cell;
		t.setString("");
		if(flags[row][col]){
			t.setBackgroundColor(Color.YELLOW);
			return;
		}
		if(!state.isVisible(row, col)){
			t.setBackgroundColor(Color.GRAY);
			return;
		}
		t.setBackgroundColor(Color.LTGRAY);
		int value=state.getValueAt(row, col);
		if(value==MinesState.MINE){
			t.setBackgroundColor(Color.RED);
			return;
		}

		if(value==MinesState.EMPTY){			
			return;
		}
		t.setString(value+"");
		t.setTextColor(Color.GREEN);
	}
	public void resetFlags(){
		flags=new boolean [state.getRows()][state.getCols()];
	}
	public void setFlag(int row,int col){
		flags[row][col]=!flags[row][col];
		
	}

	public boolean isFlagged(int row, int col) {
		return flags[row][col];
	}

}
