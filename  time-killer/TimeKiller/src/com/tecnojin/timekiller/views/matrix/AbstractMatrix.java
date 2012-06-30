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
package com.tecnojin.timekiller.views.matrix;

import java.util.ArrayList;
import java.util.Iterator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public abstract class AbstractMatrix<T> extends View implements Matrix,OnTouchListener,Iterable<Cell<T>>   {
	private int rows,cols;
	private ArrayList<Cell<T>> cells;	
	private int height,width;
	private int cellHeight,cellWidth;
	private int horizontalSeparatorHeight;
	private int verticalSeparatorWidth;
	private int leftMargin=10;
	private int topMargin=10;
	private CellClickedListener<T> listener;
	private Cell<T> lastCell;
	private long lastTouchTime;
	private long difference=60;
	private boolean square=true;
	private double separatorProportion=0.1;
	private int backGroundColor;
	private boolean allowMoove=false;
	private double heightWidhtProportion=-1;

	public AbstractMatrix(Context context,int rows,int columns) {
		super(context);
		this.rows=rows;
		this.cols=columns;

		setOnTouchListener(this);
	}


	public boolean onTouch(View v, MotionEvent event) {
		boolean b=controls(event);
		if(!b)
			return true;	

		if(lastCell!=null && listener!=null)
			listener.onClick(lastCell);
		return true;
	}



	private boolean controls(MotionEvent event) {
		if(event.getAction()==MotionEvent.ACTION_MOVE && ! allowMoove())
			return false;
		if(event.getAction()==MotionEvent.ACTION_UP)
			return false;
		if((System.currentTimeMillis()-lastTouchTime)<difference)
			return false;
		lastTouchTime=System.currentTimeMillis();
		int x=(int) event.getX();
		int y= (int) event.getY();				
		Cell<T> c=findCell(new Point(x, y));
		if(c==null)
			return false;
		if(lastCell!=null && c.equals(lastCell) && event.getAction()==MotionEvent.ACTION_MOVE){
			lastCell=c;
			return false;
		}

		lastCell=c;
		return true;

	}


	protected boolean allowMoove() {
		return allowMoove;
	}


	private Cell<T> findCell(Point p) {

		for(Cell<T> c:cells)
			if(c.contains(p))
				return c;
		return null;
	}


	public abstract Cell<T> createCell(int row, int col);

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {



		width = MeasureSpec.getSize(widthMeasureSpec);
		height = MeasureSpec.getSize(heightMeasureSpec);

		if(square) {
			width=Math.min(width,height);
			height=Math.min(width,height);
		}
		if(heightWidhtProportion>0){
			height=(int) (heightWidhtProportion*width);
		}
		int heightWOmargin=height-2*topMargin;
		int widthWOmargin=width-2*leftMargin;
		cellHeight=(int) ((heightWOmargin/rows)*(1-separatorProportion));
		cellWidth=(int) ((widthWOmargin/cols)*(1-separatorProportion));
		horizontalSeparatorHeight=(int) ((heightWOmargin/rows)*(separatorProportion));
		verticalSeparatorWidth=(int) ((widthWOmargin/cols)*(separatorProportion));

		if(cells==null)
		{
			cells=new ArrayList<Cell<T>>();
			for(int i=0;i<rows;i++)
				for(int j=0;j<cols;j++)
					cells.add(createCell(i,j));
		}

		updateCells();

		setMeasuredDimension(width, height);

		invalidate();
		//super.onMeasure(widthMeasureSpec, heightMeasureSpec);


	}
	@Override
	public void draw(Canvas canvas) {
		drawBackground(canvas,width,height);
		drawCells(canvas);
		drawExtraLayer(canvas);
	}

	public void drawExtraLayer(Canvas canvas) {};

	private void drawCells(Canvas canvas) {
		for(Cell<T> c:cells)
			c.draw(canvas);

	}



	private void updateCells() {
		for(Cell<T> c:cells){
			int left=leftMargin+((cellWidth+verticalSeparatorWidth)*c.getCol());
			int top=topMargin+((cellHeight+horizontalSeparatorHeight)*c.getRow());
			c.updateParameters(left, top, cellHeight, cellWidth);
		}


	}




	public interface CellClickedListener<T>{
		public void onClick(Cell<T> cell);
		public void onLonkClick(Cell<T> cell);
	}
	public void setSquare(boolean square) {
		this.square = square;
	}
	public void setListener(CellClickedListener<T> listener) {
		this.listener = listener;
	}
	public Iterator<Cell<T>> iterator() {
		return cells.iterator();
	}
	public void setSeparatorProportion(double separatorProportion) {
		this.separatorProportion = separatorProportion;
	}
	@Override
	public void setBackgroundColor(int color) {
		this.backGroundColor=color;
	}

	public void drawBackground(Canvas canvas, int w, int h) {
		Paint p=new Paint();
		p.setColor(backGroundColor);
		canvas.drawRect(0, 0, getWidth(), getHeight(), p);
	}
	public final boolean isBorder(Cell<T> c){
		return c.getRow()==rows-1 || c.getCol()==cols-1 || c.getRow()==0 || c.getCol()==0;
	}

	public Cell<T> getCell(int row,int col){
		//if(cols>rows)
			return cells.get((cols*row)+col);
		//return null;
	}
	public void setAllowMoove(boolean allowMoove) {
		this.allowMoove = allowMoove;
	}

	public void setHeightWidhtProportion(double heightWidhtProportion) {
		this.heightWidhtProportion = heightWidhtProportion;
	}
	public int getCellWidth() {
		return cellWidth;
	}
	public void setLeftMargin(int leftMargin) {
		this.leftMargin = leftMargin;
	}
	public void setTopMargin(int topMargin) {
		this.topMargin = topMargin;
	}
}
