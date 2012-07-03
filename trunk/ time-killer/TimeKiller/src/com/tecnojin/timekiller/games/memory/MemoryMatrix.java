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
package com.tecnojin.timekiller.games.memory;

import java.util.HashMap;
import java.util.Random;

import android.content.Context;
import android.graphics.Color;

import com.tecnojin.timekiller.views.matrix.AbstractMatrix;
import com.tecnojin.timekiller.views.matrix.BorderCell;
import com.tecnojin.timekiller.views.matrix.Cell;

public class MemoryMatrix extends AbstractMatrix<Integer>{
	private MemoryState state;
	private HashMap<Integer, Integer> map;
	public MemoryMatrix(Context context, MemoryState state) {
		super(context,state.getRows(),state.getCols());
		this.state=state;
		initMap();

	}

	private void initMap() {
		map=new HashMap<Integer, Integer>();
		for(int i=0;i<((state.getRows()*state.getCols())/2);i++){
			int r,g,b;
			r=new Random().nextInt(255);
			g=new Random().nextInt(255);
			b=new Random().nextInt(255);
			map.put(i+1, Color.rgb(r, g, b));
		}

	}

	@Override
	public Cell<Integer> createCell(int row, int col) {
		BorderCell<Integer> b=new BorderCell<Integer>(row, col, 1);
		updateCell(b);
		return b;
	}

	private void updateCell(Cell<Integer> cell) {	
		BorderCell<Integer> b=(BorderCell<Integer>) cell;
		if(state.isFlipped(cell.getRow(), cell.getCol())){
			b.setBackgroundColor(Color.GREEN);
			b.setString("");
		}
		else{
			b.setBackgroundColor(Color.WHITE);
			b.setString(state.getAt(cell.getRow(),cell.getCol())+"");
		}
	}

	public void updateStatus(){
		for(int i=0;i<state.getRows();i++)
			for(int j=0;j<state.getCols();j++){	
				
				updateCell(getCell(i, j));
			}			
		invalidate();

	}
}
