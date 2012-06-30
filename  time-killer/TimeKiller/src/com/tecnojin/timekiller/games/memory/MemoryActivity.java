package com.tecnojin.timekiller.games.memory;

import android.util.Log;

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
				Log.d("Jin","Return "+a);
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
