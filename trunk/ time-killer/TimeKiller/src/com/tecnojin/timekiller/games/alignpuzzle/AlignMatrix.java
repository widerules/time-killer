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

import java.util.HashMap;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;

import com.tecnojin.timekiller.views.matrix.AbstractMatrix;
import com.tecnojin.timekiller.views.matrix.BackGroundCell;
import com.tecnojin.timekiller.views.matrix.Cell;

public class AlignMatrix extends AbstractMatrix<Integer> {
	private AlignState state;
	private HashMap<Integer, Integer> colors;
	private Paint backgroundPaint;

	public AlignMatrix(Context context,AlignState state) {
		super(context,11,11);
		this.state=state;
		colors=new HashMap<Integer, Integer>();
		colors.put(AlignState.C1, Color.RED);
		colors.put(AlignState.C2, Color.GREEN);
		colors.put(AlignState.C3, Color.YELLOW);
		colors.put(AlignState.C4, Color.BLUE);
		colors.put(AlignState.EMPTY, Color.WHITE);
		colors.put(AlignState.LOCKED, Color.GRAY);
		colors.put(-1, Color.TRANSPARENT);		
		backgroundPaint=new Paint();
		backgroundPaint.setStyle(Style.STROKE);
		backgroundPaint.setColor(Color.DKGRAY);
		setLeftMargin(0);
		setTopMargin(0);
		setSeparatorProportion(0);
	}

	@Override
	public Cell<Integer> createCell(int row, int col) {
		BackGroundCell<Integer> b=new BackGroundCell<Integer>(row, col, state.getElementAt(row, col));
		b.setBackgroundColor(colors.get(state.getElementAt(row, col)));		
		return b;
	}
	
	public void updateState(){
		for(int i=0;i<11;i++)
			for(int j=0;j<11;j++){
				((BackGroundCell<Integer>)getCell(i, j)).setContent(state.getElementAt(i, j));
				((BackGroundCell<Integer>)getCell(i, j)).setBackgroundColor(colors.get(state.getElementAt(i, j)));
			}
		invalidate();
	}
	
	@Override
	public void drawBackground(Canvas canvas, int w, int h) {
		
		int cellWidth=(int) (getCellWidth());
		int radius=0;
		Cell<Integer> c=getCell(5, 5);
		int x=c.getLeft()+(c.getWidth()/2);
		int y=c.getTop()+(c.getHeight()/2);
		backgroundPaint.setStrokeWidth(c.getWidth()/3);
				
	
		for(int i=0;i<=5;i++){
			canvas.drawCircle(x,y, radius, backgroundPaint);
			radius+=cellWidth;
		}
	}

	

}
