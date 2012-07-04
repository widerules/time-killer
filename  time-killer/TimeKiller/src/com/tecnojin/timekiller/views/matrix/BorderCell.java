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
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;

public class BorderCell<T> extends TextCell<T>{
	private double borderWidth=.1;
	private int borderColor=Color.RED;
	private boolean left,right,up,down,principalDiagonal,secondaryDiagonal;
	private Paint p;
	public BorderCell(int row, int col, T content) {
		super(row, col, content,"");
		initPaint();
	}
	

	private void initPaint() {
		p=new Paint();
		p.setStyle(Style.FILL_AND_STROKE);
		p.setColor(borderColor);
		p.setStrokeWidth((float) (getWidth()*borderWidth));
		

	}
	@Override
	public void draw(Canvas c) {
		if(!isVisible())
			return;
		super.draw(c);
		if(left)
			left(c);
		if(right)
			right(c);
		if(up)
			up(c);
		if(down)
			down(c);
		if(principalDiagonal)
			pd(c);
		if(secondaryDiagonal)
			sd(c);

	}
	private void sd(Canvas c) {
		c.drawLine(getLeft()+getWidth(), getTop(),getLeft(),getTop()+getHeight(), p);

	}
	private void pd(Canvas c) {
		c.drawLine(getLeft(), getTop(),getLeft()+getWidth(),getTop()+getHeight(), p);

	}
	private void down(Canvas c) {
		c.drawLine(getLeft(), getTop()+getHeight(),getLeft()+getWidth(),getTop()+getHeight(), p);

	}
	private void up(Canvas c) {
		c.drawLine(getLeft(), getTop(),getLeft()+getWidth(),getTop(), p);

	}
	private void right(Canvas c) {
		c.drawLine(getLeft()+getWidth(), getTop(),getLeft()+getWidth(),getTop()+getHeight(), p);

	}
	private void left(Canvas c) {
		c.drawLine(getLeft(), getTop(),getLeft(),getTop()+getHeight(), p);

	}
	public double getBorderWidth() {
		return borderWidth;
	}
	public void setBorderWidth(double borderWidth) {
		this.borderWidth = borderWidth;
	}
	public boolean isLeft() {
		return left;
	}
	public void setLeft(boolean left) {
		this.left = left;
	}
	public boolean isRight() {
		return right;
	}
	public void setRight(boolean right) {
		this.right = right;
	}
	public boolean isUp() {
		return up;
	}
	public void setUp(boolean up) {
		this.up = up;
	}
	public boolean isDown() {
		return down;
	}
	public void setDown(boolean down) {
		this.down = down;
	}
	public boolean isPrincipalDiagonal() {
		return principalDiagonal;
	}
	public void setPrincipalDiagonal(boolean principalDiagonal) {
		this.principalDiagonal = principalDiagonal;
	}
	public boolean isSecondaryDiagonal() {
		return secondaryDiagonal;
	}
	public void setSecondaryDiagonal(boolean secondaryDiagonal) {
		this.secondaryDiagonal = secondaryDiagonal;
	}
	public void setBorderColor(int borderColor) {
		this.borderColor = borderColor;
		initPaint();
	}
	public int getBorderColor() {
		return borderColor;
	}
	public void setSquare() {
		up=true;
		down=true;
		left=true;
		right=true;

	}
	@Override
	public void measureHasChanged() {
		super.measureHasChanged();
		initPaint();
	}
	


}
