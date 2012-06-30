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

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;



public class BackGroundCell<T> extends Cell<T> {
	private Integer color=Color.WHITE;
	private Bitmap b;
	private Paint p;
	private Rect r;

	public BackGroundCell(int row, int col, T content) {
		super(row, col, content);	
		init();
	}
	public BackGroundCell(int row, int col, T content,int color) {
		super(row, col, content);		
		this.color=color;
		init();
	}
	public BackGroundCell(int row, int col, T content,Drawable d) {
		super(row, col, content);	
		this.b=((BitmapDrawable)d).getBitmap();
		init();
	}
	public void setBackgroundColor(int color) {
		this.color = color;		
		b=null;
		init();
	}
	public void setBackgroundImage(Drawable d) {
		this.b=((BitmapDrawable)d).getBitmap();
		color=null;
		init();

	}


	@Override
	public void draw(Canvas c) {

		if(color!=null)
			drawColor(c);
		else
			if(b!=null)
				drawDrawable(c);


	}
	private void drawDrawable(Canvas c) {

		c.drawBitmap(b, null,r,p);


	}
	private void drawColor(Canvas c) {

		c.drawRect(r, p);

	}
	private void init() {
		p=new Paint();
		if(color!=null)
			p.setColor(color);
		p.setStyle(Style.FILL_AND_STROKE);

		r=new Rect();
		r.top=getTop();
		r.left=getLeft();
		r.right=r.left+getWidth();
		r.bottom=r.top+getHeight();



	}
	@Override
	public void measureHasChanged() {
		
		init();

	}


}
