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
package com.tecnojin.timekiller.games.frog;

import java.util.LinkedList;
import java.util.List;

import android.graphics.Point;
import android.util.Log;

import com.tecnojin.timekiller.util.PathFinder;

public class FrogState {
	public static final int UP=1;
	public static final int DOWN=2;
	public static final int LEFT=3;
	public static final int RIGHT=4;

	public static final int WATER=5;
	
	public static final int FREE=6;
	public static final int FROG=7;

	private int currentDirection;

	private int [][] matrix;

	private int rows,cols;
	private Point frog;
	private int passed;
	private List<Point> path;

	public FrogState(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;

		while(true){
			PathFinder p=new PathFinder(rows, cols, ((rows*cols)/3), true, true, 1000);
			p.findPath();
			path=p.getFinalPath();
			if(!path.isEmpty())
				break;
		}
		path=takeOnlyChange(path);
		initMatrix(path);

	}

	private void initMatrix(List<Point> path) {
		matrix=new int [rows][cols];
		for(int i=0;i<rows;i++)
			for(int j=0;j<cols;j++)
				matrix[i][j]=WATER;
		frog=path.get(0);
		for(Point p:path){
			
			matrix[p.x][p.y]=FREE;
		}
		moveFrogFrom(null,frog);
		
		firstDirection();
	
		passed=0;

	}

	private void firstDirection() {
		int x=frog.x;
		int y=frog.y;
		if(x==0)
			currentDirection=DOWN;
		if(x==rows-1)
			currentDirection=UP;
		if(y==0)
			currentDirection=RIGHT;
		if(y==cols-1)
			currentDirection=LEFT;
		
	}

	private void moveFrogFrom(Point old, Point to) {
		passed++;
		frog=to;
		matrix[frog.x][frog.y]=FROG;
		if(old!=null){
			matrix[old.x][old.y]=WATER;
			updateDirectioN(old,to);
		}
		
	}

	private void updateDirectioN(Point old, Point to) {
		int r1=old.x;
		int c1=old.y;
		int r2=to.x;
		int c2=to.y;

		if(r1==r2){
			if(c1>c2)
				currentDirection=LEFT;
			else
				currentDirection=RIGHT;
		}
		if(c1==c2){
			if(r1>r2)
				currentDirection=UP;
			else
				currentDirection=DOWN;
		}

	}

	private LinkedList<Point> takeOnlyChange(List<Point> path) {
		LinkedList<Point> l=new LinkedList<Point>();
		l.add(path.get(0));
		path.remove(0);
		Point candidate=null;
		for(Point p1:path){			
			Point last=l.getLast();
			if(seemDirection(p1,last))
				candidate=p1;
			else{
				l.addLast(candidate);
				candidate=p1;
			}
		}
		return l;

	}

	private boolean seemDirection(Point p1, Point p2) {
		if(p1.x==p2.x || p1.y==p2.y)
			return true;
		return false;
	}

	public void wantToMove(int r,int c){
		if(matrix[r][c]!=FREE)
			return;
		List<Point> possibleMooves=getPossibleMooves();
		Point p=new Point(r,c);
		if(!possibleMooves.contains(p)){
			Log.d("Jin", "No moves ");
			return;
		}
		if(goBack(frog,p)){
			Log.d("Jin", "Back");
			
			return;
		}
		
		moveFrogFrom(frog, p);
	}

	private boolean goBack(Point from, Point to) {
		int r1=from.x;
		int r2=to.x;
		int c1=from.y;
		int c2=to.y;
		if(r1==r2){			
			//moving left
			if(c1>c2 && currentDirection==RIGHT)
				return true;
			//moving right
			if(c1<c2 && currentDirection==LEFT)
				return true;
			return false;			
		}
		if(c1==c2){			
			//moving down
			if(r1<r2 && currentDirection==UP)
				return true;
			//up
			if(r1>r2 && currentDirection==DOWN)
				return true;
			return false;
				
		}
		return false;
	}

	private List<Point> getPossibleMooves() {
		LinkedList<Point> l=new LinkedList<Point>();
		int r=frog.x;
		int c=frog.y;

		//up
		for(int i=r-1;i>=0;i--)
			if(matrix[i][c]==FREE){
				l.add(new Point(i,c));
				break;
			}
		//down
		for(int i=r+1;i<rows;i++)
			if(matrix[i][c]==FREE){
				l.add(new Point(i,c));
				break;
			}

		//left
		for(int i=c-1;i>=0;i--)
			if(matrix[r][i]==FREE){
				l.add(new Point(r,i));
				break;
			}
		//right
		for(int i=c+1;i<cols;i++)
			if(matrix[r][i]==FREE){
				l.add(new Point(r,i));
				break;
			}

		return l;
	}

	public int getRows() {
		return rows;
	}
	public int getCols() {
		return cols;
	}
	public int get(int row,int col){
		return matrix[row][col];
	}
	public int getCurrentDirection() {
		return currentDirection;
	}
	
	public void reset(){
		initMatrix(path);
		
	}
	public int getNumberFor(int r,int c){
		return path.indexOf(new Point(r,c));
	}
	public boolean isOver(){
		
		if(passed==path.size()-1)
			return true;
		return false;
		
	}

}
