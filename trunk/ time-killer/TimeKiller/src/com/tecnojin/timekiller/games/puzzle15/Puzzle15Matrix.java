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
package com.tecnojin.timekiller.games.puzzle15;

import android.content.Context;

import com.tecnojin.timekiller.views.matrix.AbstractMatrix;
import com.tecnojin.timekiller.views.matrix.BorderCell;
import com.tecnojin.timekiller.views.matrix.Cell;

public class Puzzle15Matrix extends AbstractMatrix<Integer> {
	private Puzzle15State state;
	public Puzzle15Matrix(Context context,Puzzle15State state) {
		super(context, state.getRows(), state.getCols());
		this.state=state;
		
	}

	@Override
	public Cell<Integer> createCell(int row, int col) {
		BorderCell<Integer> b=new BorderCell<Integer>(row, col, 1);
		b.setSquare();
		updateCell(b);
		return b;
	}

	private void updateCell(BorderCell<Integer> a) {
		if(state.getValueAt(a.getRow(), a.getCol())==0)
			a.setString("");
		else
			a.setString(""+state.getValueAt(a.getRow(), a.getCol()));		
	}
	public void updadeState(){
		for(int i=0;i<state.getRows();i++)
			for(int j=0;j<state.getCols();j++)
				updateCell(((BorderCell<Integer>)getCell(i, j)));
		invalidate();
	}

	

}
