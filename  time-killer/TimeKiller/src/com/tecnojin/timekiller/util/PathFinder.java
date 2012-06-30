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

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import android.graphics.Point;

public class PathFinder {

	private int rows,cols;
	private int minLenght;
	private LinkedList<Point> path;
	private LinkedList<Point> finalPath;
	private boolean startOnBorder, endOnBorder;
	private long maxTime;
	private long start;


	public PathFinder(int rows, int cols, int minLenght, boolean startOnBorder,
			boolean endOnBorder,long maxTime) {
		super();
		this.rows = rows;
		this.cols = cols;
		this.minLenght = minLenght;
		this.startOnBorder = startOnBorder;
		this.endOnBorder = endOnBorder;
		path=new LinkedList<Point>();
		this.maxTime=maxTime;
		finalPath=new LinkedList<Point>();
	}


	public void findPath(){
		start=System.currentTimeMillis();
		Point start;
		if(startOnBorder)
			start=randomOnBorder();
		else
			start=randomCell();
		move(start);
		find();

	}


	private void move(Point p) {
		path.addLast(p);

	}


	private void find() {
		if(stop()){

			return;
		}

		Point p=path.getLast();
		LinkedList<Point> neightBors=getNeightBors(p);
		shuffle(neightBors);

		for(Point pp:neightBors){
			move(pp);
			find();
			undo();
		}

	}


	private void shuffle(LinkedList<Point> neightBors) {
		Collections.shuffle(neightBors);
		Collections.shuffle(neightBors);
		Collections.shuffle(neightBors);

	}


	private void undo() {
		path.removeLast();
	}


	private LinkedList<Point> getNeightBors(Point p) {
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
		Iterator<Point> it=n.iterator();
		while(it.hasNext())
			if(path.contains(it.next()))
				it.remove();

		return n;
	}


	private boolean stop() {
		if(System.currentTimeMillis()-start>maxTime)
			return true;
		if(!finalPath.isEmpty())
			return true;
		if(path.size()>=minLenght){
			Point p=path.getLast();
			if(opposite(p,path.getFirst())){
				finalPath.addAll(path);
			}
			else
				if(!endOnBorder)
					finalPath.addAll(path);
		}
		return !finalPath.isEmpty();
	}


	private boolean opposite(Point p1, Point p2) {
		int x1=p1.x;
		int x2=p2.x;
		int y1=p1.y;
		int y2=p2.y;
		
		
		if(Math.abs(x1-x2)==rows-1)
			return true;
		if(Math.abs(y1-y2)==cols-1)
			return true;
		return false;
	}


	private Point randomCell() {
		Random r=new Random();
		return new Point(r.nextInt(rows),r.nextInt(cols));
	}


	private Point randomOnBorder() {
		Random r=new Random();
		Point p=randomCell();
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
	public LinkedList<Point> getFinalPath() {
		return finalPath;
	}



}
