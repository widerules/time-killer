package com.tecnojin.timekiller.games.memory;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.tecnojin.timekiller.activity.GameActivity;
import com.tecnojin.timekiller.games.GameManager;
import com.tecnojin.timekiller.games.memory.MemoryState.onChangeListener;
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
				state.tapOnCell(cell.getRow(),cell.getCol());
				matrix.updateStatus();
				
			}

			public void onLonkClick(Cell<Integer> cell) {
								
			}
		});
		
		state.setListener(new onChangeListener(new Handler(){
			@Override
			public void handleMessage(Message msg) {
				matrix.updateStatus();
				
			}
		}));
		
	}
	private void initState(int dif) {
		int row=5;
		int col=10;
		
		state=new MemoryState(row, col);
				
	}

}
