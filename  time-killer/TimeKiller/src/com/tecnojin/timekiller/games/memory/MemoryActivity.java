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

import com.tecnojin.timekiller.activity.GameActivity;
import com.tecnojin.timekiller.games.GameManager;
import com.tecnojin.timekiller.views.matrix.AbstractMatrix.CellClickedListener;
import com.tecnojin.timekiller.views.matrix.Cell;

public class MemoryActivity extends GameActivity{
	private MemoryMatrix matrix;
	private MemoryState state;
	@Override
	public void loadGame() {
		int dif=Integer.parseInt(GameManager.instance(this).getGame(GameManager.MEMORY, this).getOptions().findOptionForKey("diff").getCurrentValue());
		initState(dif);
		matrix=new MemoryMatrix(this, state);
		gamelayout.addView(matrix);
		
		matrix.setListener(new CellClickedListener<Integer>() {

			public void onClick(Cell<Integer> cell) {
				int a=state.tapOnCell(cell.getRow(),cell.getCol());
			
				if(a==MemoryState.NOTHING_TO_DO)
					state.updateTapped();
				matrix.updateStatus();
				if(state.isCompleted())
					gameTerminated();
				
			}

			

			public void onLonkClick(Cell<Integer> cell) {
								
			}
		});
		
		
	}
	private void initState(int dif) {
		int row=5;
		int col=10;
		
		state=new MemoryState(row, col);
				
	}

}
