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


import android.graphics.Canvas;
import android.graphics.Point;


public abstract class Cell<T>{
	
	private int row,col;
	private int height,width;
	private int left,top;
	private T content;
	private boolean visible=true;
	
	public Cell(int row, int col,
			T content) {	
		this.row = row;
		this.col = col;
		this.content = content;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public void draw(Canvas c){
		if(!isVisible())
			return;
	}
	
	public void updateParameters(int left,int top,int height,int width){
		this.left=left;
		this.top=top;
		this.height=height;
		this.width=width;
		measureHasChanged();
	}
	public abstract void measureHasChanged() ;

	public boolean contains(Point p){
		if(p.x>= left && p.x< left+width)
			if(p.y>= top && p.y< top+height)
				return true;
		return false;
	}
	public int getRow() {
		return row;
	}
	public int getCol() {
		return col;
	}
	public T getContent() {
		return content;
	}
	public int getHeight() {
		return height;
	}
	public int getWidth() {
		return width;
	}
	public int getLeft() {
		return left;
	}
	public int getTop() {
		return top;
	}
	@Override
	public boolean equals(Object o) {
		if(! (o instanceof Cell))
			return false;
		Cell c=(Cell) o;
		return c.row==row && c.col==col;
		
	}
	@Override
	public String toString() {
		return row+" "+col+" "+content;
	}
	public void setContent(T content) {
		this.content = content;
	}
	public boolean isVisible() {
		return visible;
	}
	
	
	

}
