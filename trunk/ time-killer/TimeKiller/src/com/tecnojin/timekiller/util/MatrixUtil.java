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
package com.tecnojin.timekiller.util;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import android.graphics.Point;

public class MatrixUtil {
	private static Random r=new Random();
	public static Point randomCell(int rows,int cols){
		r=new Random();
		return new Point(r.nextInt(rows),r.nextInt(cols));
	}
	public static Point randomBorderCell(int rows,int cols){
		r=new Random();
		Point p=randomCell(rows,cols);
		int a=r.nextInt(4);
		if(a==0)
			p.x=0;
		if(a==1)
			p.x=rows-1;
		if(a==2)
			p.y=0;
		if(a==4)
			p.y=cols-1;
		return p;
	}
	public static List<Point> getNeightBors(int rows,int cols,Point p){		
			int x=p.x;
			int y=p.y;

			LinkedList<Point> n=new LinkedList<Point>();

			if(x>0)
				n.add(new Point(x-1,y));
			if(y>0)
				n.add(new Point(x,y-1));
			if(y<cols-1)
				n.add(new Point(x,y+1));
			if(x<rows-1)
				n.add(new Point(x+1,y));
		

			return n;
		
	}
	public static List<Point> getNeightBors6(int rows, int cols, Point p) {
		List<Point> l=getNeightBors(rows, cols, p);
		int r=p.x;
		int c=p.y;
		
		if(r!=0 && c!=0)
			l.add(new Point(r-1,c-1));
		if(r!=0 && c!=cols-1)
			l.add(new Point(r-1,c+1));
		if(r!=rows-1 && c!=cols-1)
			l.add(new Point(r+1,c+1));
		if(r!=rows-1 && c!=0)
			l.add(new Point(r+1,c-1));
		
		
		return l;
	}

}
