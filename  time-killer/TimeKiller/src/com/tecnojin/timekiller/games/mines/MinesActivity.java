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

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ToggleButton;

import com.tecnojin.timekiller.activity.GameActivity;
import com.tecnojin.timekiller.games.GameManager;
import com.tecnojin.timekiller.games.descriptors.options.Option;
import com.tecnojin.timekiller.games.descriptors.options.OptionSet;
import com.tecnojin.timekiller.views.matrix.AbstractMatrix.CellClickedListener;
import com.tecnojin.timekiller.views.matrix.Cell;

public class MinesActivity extends GameActivity {
	private MinesState state;
	private MinesMatrix m;
	private Button reset;
	private ToggleButton flag;	
	@Override
	public void loadGame() {
		OptionSet o=GameManager.instance(this).getGame(GameManager.MINES, this).getOptions();
		Option oo=o.findOptionForKey("diff");
		int a=Integer.parseInt(oo.getCurrentValue());
		
		state=createState(a);
		
		m=new MinesMatrix(this, state);
		reset=new Button(this);
		reset.setText("Reset");
		gamelayout.addView(m);
		gamelayout.addView(reset);
		
		reset.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				state.reset();				
				m.resetFlags();
				flag.setSelected(false);
				m.updadeState();
				
				
			}

			
		});
		
		m.setListener(new CellClickedListener<Integer>() {

			public void onClick(Cell<Integer> cell) {
				if(m.isFlagged(cell.getRow(), cell.getCol()))
					return;
				if(flag.isChecked() && !state.isVisible(cell.getRow(), cell.getCol())){
					m.setFlag(cell.getRow(), cell.getCol());
					m.updadeState();
					return;
				}
				state.touchOn(cell.getRow(), cell.getCol());
				
				if(state.isMineFound()){
					state.showAllMines();
				}
				if(state.isComplete())					
					gameTerminated(null);
				m.updadeState();
			}

			public void onLonkClick(Cell<Integer> cell) {
				
				
			}
			
		});
		flag=new ToggleButton(this);
		flag.setText("Flag");
		
		
		gamelayout.addView(flag);
		
		
		
		
		
		
	}
	private MinesState createState(int a) {
		int row=9;
		int col=9;
		
		if(a==2){
			row=16;
			col=16;
		}
		if(a==3){
			row=30;
			col=16;
		}
		return new MinesState(row, col);
			
	}
	
}
