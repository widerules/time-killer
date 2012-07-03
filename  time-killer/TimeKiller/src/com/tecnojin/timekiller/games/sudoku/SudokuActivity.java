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

import java.util.HashMap;
import java.util.List;

import android.util.Log;
import android.widget.Toast;

import com.tecnojin.timekiller.activity.GameActivity;
import com.tecnojin.timekiller.games.GameManager;
import com.tecnojin.timekiller.util.DatabaseManager;
import com.tecnojin.timekiller.util.MathUtil;
import com.tecnojin.timekiller.views.matrix.AbstractMatrix.CellClickedListener;
import com.tecnojin.timekiller.views.matrix.Cell;

public class SudokuActivity extends GameActivity{
	private SudokuState state;
	private SudokuMatrix matrix;

	private SudokuState resolveState() {
		int dif=-1;
		int id=-1;
		Integer min=null,max=null;

		try {
			dif=Integer.parseInt(GameManager.instance(this).getGame(GameManager.SUDOKU, this).getOptions().findOptionForKey("diff").getCurrentValue());
			DatabaseManager d=DatabaseManager.instance(this);
			List<HashMap<String, String>> m=d.query("Select min(id) from sud where val3='"+dif+"';");
			List<HashMap<String, String>> M=d.query("Select max(id) from sud where val3='"+dif+"';");


			min=Integer.parseInt(m.get(0).get("min(id)").toString());
			max=Integer.parseInt(M.get(0).get("max(id)").toString());

			id=MathUtil.random(min, max);

			List<HashMap<String, String>> s=d.query("Select * from sud where id='"+id+"';");

			String sudoku=s.get(0).get("val1");
			String solution=s.get(0).get("val2");

			
			return new SudokuState(sudoku, solution);

		} catch (Exception e) {
			Log.d("Jin", "Error with index "+id+" from min "+min+" max "+max);
			throw new RuntimeException(e);
		}

		/*
		short [][] s =new short [][] {
				{8,3,5,4,1,6,9,2,7},
				{2,9,6,8,5,7,4,3,1},
				{4,1,7,2,9,3,6,5,8},
				{5,6,9,1,3,4,7,8,2},
				{1,2,3,6,7,8,5,4,9},
				{7,4,8,5,2,9,1,6,3},
				{6,5,2,7,8,1,3,9,4},
				{9,8,1,3,4,5,2,7,8},
				{3,7,4,9,6,2,8,1,5},				
		};
		return new SudokuState(s);
		 */
	}
	@Override
	public void loadGame() {

		state=resolveState();
		matrix=new SudokuMatrix(this,state);
		gamelayout.addView(matrix);

		matrix.setListener(new CellClickedListener<Integer>() {

			public void onClick(Cell<Integer> cell) {
				int r=cell.getRow();
				int c=cell.getCol();
	
				matrix.next(r, c);
				if(state.isComplete()){
					Toast.makeText(SudokuActivity.this, "OK",Toast.LENGTH_SHORT).show();
				}


			}

			public void onLonkClick(Cell<Integer> cell) {
				// TODO Auto-generated method stub

			}

		});


	}

}
