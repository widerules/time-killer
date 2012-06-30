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
package com.tecnojin.timekiller.games.alignpuzzle;

import com.tecnojin.timekiller.activity.GameActivity;
import com.tecnojin.timekiller.views.matrix.AbstractMatrix.CellClickedListener;
import com.tecnojin.timekiller.views.matrix.Cell;

public class AlignPuzzleActivity extends GameActivity{
	private AlignState state;
	private AlignMatrix matrix;

	@Override
	public void loadGame() {
		state=new AlignState();
	//	state.shuffle();
		matrix=new AlignMatrix(this, state);
		
		gamelayout.addView(matrix);
		matrix.setListener(new CellClickedListener<Integer>() {

			public void onClick(Cell<Integer> cell) {
				if(cell.getContent()<0)
					return;
				state.move(cell.getRow(), cell.getCol());
				matrix.updateState();
				if(state.isComplete()){
					gameTerminated();
				}
			}

			public void onLonkClick(Cell<Integer> cell) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
	}

}
