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
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.Typeface;

public class TextCell<T> extends BackGroundCell<T> {
	private String string;
	private Paint p;
	private float size;
	private int textColor;
	private Typeface typeFace;



	public TextCell(int row, int col, T content,String string) {
		super(row, col, content);
		this.string=string;
		textColor=Color.BLACK;
		initPaint();



	}
	private void initPaint() {
		p=new Paint();
		p.setStyle(Style.FILL_AND_STROKE);
		p.setColor(textColor);
		if(size!=0)
			p.setTextSize(size);
		else
		p.setTextSize(getHeight());

		p.setTextAlign(Align.CENTER);
		if(typeFace!=null)
			p.setTypeface(typeFace);


	}

	@Override
	public void draw(Canvas c) {
		super.draw(c);
		drawtext(c, getLeft(), getTop(),getWidth(),getHeight());
		/*
		if(center)
			c.drawText(string,getLeft()+getWidth()/2,getTop()+getHeight()/2,p);
		else
			c.drawText(string,getLeft(),getTop()+getHeight(),p);
		 */
	}
	public void setTextColor(int textColor) {
		this.textColor = textColor;
		initPaint();
	}
	public void setTypeFace(Typeface typeFace) {
		this.typeFace = typeFace;
		initPaint();
	}
	public void setTextSize(float size) {
		this.size = size;
		initPaint();
	}
	@Override
	public void measureHasChanged() {
		super.measureHasChanged();

	}
	public void setString(String string) {
		this.string = string;
	}
	void drawtext(Canvas c, int topLeftX, int topLeftY, int width, int height) {

		Rect bounds = new Rect();
		p.getTextBounds(string, 0, string.length(), bounds);
		c.drawText(string, topLeftX+width/2, topLeftY+height/2+(bounds.bottom-bounds.top)/2, p);
	}

}
