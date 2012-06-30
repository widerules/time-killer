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

import android.graphics.Color;
import android.widget.TextView;

import com.tecnojin.timekiller.activity.GameActivity;
import com.tecnojin.timekiller.games.GameManager;
import com.tecnojin.timekiller.views.matrix.AbstractMatrix.CellClickedListener;
import com.tecnojin.timekiller.views.matrix.Cell;

public class FillItActivity extends GameActivity {
	private int cut;
	private FillItState state;
	private HashMap<Integer, Integer> colors;
	
	private FillItMatrix matrix;
	private FillItVector vector;
	private int moves;
	private TextView t;
	@Override
	public void loadGame() {
		int dif=Integer.parseInt(GameManager.instance(this).getGame(GameManager.FILL_IT, this).getOptions().findOptionForKey("diff").getCurrentValue());
		int side=12;
		cut=22;
		if(dif==2){
			side=17;
			cut=30;
		}
		if(dif==3){
			side=22;
			cut=36;
		}
		state=new FillItState(6, side);
		colors=new HashMap<Integer, Integer>();
		
		colors.put(0,Color.RED);
		colors.put(1,Color.GREEN);
		colors.put(2,Color.BLUE);
		colors.put(3,Color.YELLOW);
		colors.put(4,Color.MAGENTA);
		colors.put(5,Color.CYAN);
		
		matrix=new FillItMatrix(this, state, colors);
		vector=new FillItVector(this, colors);
		t=new TextView(this);
		t.setTextSize(30);
		setText();
		gamelayout.addView(t);
		gamelayout.addView(matrix);
		gamelayout.addView(vector);
		
		vector.setListener(new CellClickedListener<Integer>() {

			public void onClick(Cell<Integer> cell) {
				
				
				int c=cell.getContent();
				moves++;
				setText();
				state.applyMoove(c);
				matrix.updateState();
				
				if(state.isComplete() || moves==cut){
					gameTerminated();
				}
				
			}

			public void onLonkClick(Cell<Integer> cell) {
			
			}
		});
		
		
	}
	private void setText() {
		t.setText(moves+"/"+cut);
		
	}

}
