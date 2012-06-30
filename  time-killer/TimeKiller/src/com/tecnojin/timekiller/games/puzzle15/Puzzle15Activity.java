package com.tecnojin.timekiller.games.puzzle15;

import com.tecnojin.timekiller.activity.GameActivity;
import com.tecnojin.timekiller.games.GameManager;
import com.tecnojin.timekiller.games.descriptors.options.Option;
import com.tecnojin.timekiller.games.descriptors.options.OptionSet;
import com.tecnojin.timekiller.views.matrix.AbstractMatrix.CellClickedListener;
import com.tecnojin.timekiller.views.matrix.Cell;

public class Puzzle15Activity extends GameActivity {
	private Puzzle15State state;
	private Puzzle15Matrix matrix;

	@Override
	public void loadGame() {
		OptionSet o=GameManager.instance(this).getGame(GameManager.PUZZLE15, this).getOptions();
		Option oo=o.findOptionForKey("diff");
		int a=Integer.parseInt(oo.getCurrentValue());
		state=initState(a);
		matrix=new Puzzle15Matrix(this, state);
		gamelayout.addView(matrix);
		matrix.setListener(new CellClickedListener<Integer>() {

			public void onClick(Cell<Integer> cell) {
				state.move(cell.getRow(), cell.getCol());
				matrix.updadeState();
				if(state.isCompleted())
					gameTerminated();
				
			}

			public void onLonkClick(Cell<Integer> cell) {
				// TODO Auto-generated method stub
				
			}
		});

	}

	private Puzzle15State initState(int a) {
		if(a==1)
			return new Puzzle15State(3, 3);
		if(a==2)
			return new Puzzle15State(4,4);
		return new Puzzle15State(6,6);

	}

}
