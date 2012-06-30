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
package com.tecnojin.timekiller.games.frog;

import java.util.HashMap;

import android.content.Context;
import android.graphics.Color;

import com.tecnojin.timekiller.views.matrix.AbstractMatrix;
import com.tecnojin.timekiller.views.matrix.BorderCell;
import com.tecnojin.timekiller.views.matrix.Cell;

public class FrogMatrix extends AbstractMatrix<Integer>{
	private FrogState state;
	private HashMap<Integer, Integer> colors;
	
	public FrogMatrix(Context context, FrogState state) {
		super(context, state.getRows(), state.getCols());
		this.state=state;
		colors=new HashMap<Integer, Integer>();
		colors.put(FrogState.WATER, Color.CYAN);
		colors.put(FrogState.FREE, Color.GREEN);
		colors.put(FrogState.FROG, Color.YELLOW);
		setSquare(true);
		setSeparatorProportion(0.1);

	}

	@Override
	public Cell<Integer> createCell(int row, int col) {
		BorderCell<Integer> b=new BorderCell<Integer>(row, col,1);
		b.setBackgroundColor(colors.get( state.get(row, col)));
		
		b.setString("");		
		b.setTextSize(25);
		if (state.get(row, col)==FrogState.FROG){
			if(state.getCurrentDirection()==FrogState.LEFT)
				b.setString("<");
			if(state.getCurrentDirection()==FrogState.RIGHT)
				b.setString(">");
			if(state.getCurrentDirection()==FrogState.UP)
				b.setString("^");
			if(state.getCurrentDirection()==FrogState.DOWN)
				b.setString("V");			
		}
		return b;
	}
	public void updateState(){
		for(int i=0;i<state.getRows();i++)
			for(int j=0;j<state.getCols();j++){
				BorderCell<Integer> b=(BorderCell<Integer>) getCell(i, j);
			
				b.setBackgroundColor(colors.get(state.get(i, j)));
				b.setString("");
				
				if(state.get(i, j)==FrogState.FROG){
					if(state.getCurrentDirection()==FrogState.LEFT)
						b.setString("<");
					if(state.getCurrentDirection()==FrogState.RIGHT)
						b.setString(">");
					if(state.getCurrentDirection()==FrogState.UP)
						b.setString("^");
					if(state.getCurrentDirection()==FrogState.DOWN)
						b.setString("V");			
				}
			}

		invalidate();
	}

}
