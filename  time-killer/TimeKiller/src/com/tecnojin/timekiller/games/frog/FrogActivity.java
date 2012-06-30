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

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.tecnojin.timekiller.activity.GameActivity;
import com.tecnojin.timekiller.views.matrix.AbstractMatrix.CellClickedListener;
import com.tecnojin.timekiller.views.matrix.Cell;

public class FrogActivity extends GameActivity{
	private FrogState state;
	private FrogMatrix matrix;
	private Button reset;

	@Override
	public void loadGame() {
		state=new FrogState(10, 10);
		matrix=new FrogMatrix(this, state);
		gamelayout.addView(matrix);
		reset=new Button(this);
		reset.setText("Reset");
		gamelayout.addView(reset);
		reset.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				state.reset();
				matrix.updateState();
				
			}
		});
		
		matrix.setListener(new CellClickedListener<Integer>() {

			public void onClick(Cell<Integer> cell) {
				state.wantToMove(cell.getRow(), cell.getCol());
				matrix.updateState();
				if(state.isOver())
					gameTerminated();
				
			}

			public void onLonkClick(Cell<Integer> cell) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		
	}
	@Override
	protected void onStart() {
		super.onStart();
		
	}
	

}
