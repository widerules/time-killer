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
package com.tecnojin.timekiller.games.sudoku;

import android.content.Context;
import android.graphics.Color;

import com.tecnojin.timekiller.views.matrix.AbstractMatrix;
import com.tecnojin.timekiller.views.matrix.BorderCell;
import com.tecnojin.timekiller.views.matrix.Cell;

public class SudokuMatrix extends AbstractMatrix<Integer>{
	private SudokuState state;
	private int cont=0;

	public SudokuMatrix(Context context, SudokuState state) {
		super(context, state.getSide(),state.getSide());
		this.state=state;
		setBackgroundColor(Color.WHITE);
		setSquare(true);
		setSeparatorProportion(0);
	}

	@Override
	public Cell<Integer> createCell(int row, int col) {
		BorderCell<Integer> b=new BorderCell<Integer>(row, col, cont++);
		b.setTextSize(22);
		b.setTextColor(Color.BLACK);
		b.setBorderColor(Color.BLACK);

		b.setBorderWidth(0.01);
		if(col!=state.getSide()-1)
			b.setRight(true);
		if(row!=state.getSide()-1)
			b.setDown(true);


		b.setBorderWidth(0.2);

		short s=state.get(row, col);
		if(s!=0)
			b.setString(s+"");
		else
			b.setString(" ");
		return b;
	}

	public void update() {
		for(int i=0;i<state.getSide();i++)
			for(int j=0;j<state.getSide();j++)
				if(state.get(i, j)!=0)
					((BorderCell<Integer>)getCell(i, j)).setString(state.get(i, j)+"");
				else
					((BorderCell<Integer>)getCell(i, j)).setString(" ");
		invalidate();

	}

	public void next(int r,int c){
		short a=state.get(r, c);
		a++;
		if(a>state.getSide())
			a=0;
		state.set(r, c, a);
		update();
	}
	
	


}
