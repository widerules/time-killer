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
package com.tecnojin.timekiller.games.mines;

import java.util.List;

import android.graphics.Point;

import com.tecnojin.timekiller.util.MatrixUtil;

public class MinesState {
	private static final int MINES_EASY=10;
	private static final int MINES_MEDIUM=40;
	private static final int MINES_HARD=99;

	public static final int MINE=-1;
	public static final int EMPTY=0;

	private int [][] matrix;
	private boolean [][] showed;
	private int rows,cols;
	private boolean mineFound=false;
	private int mines;


	public MinesState(int rows, int cols) {
		super();
		this.rows = rows;
		this.cols = cols;

		reset();
	}

	public void reset() {
		mineFound=false;
		
		matrix=new int [rows][cols];
		showed=new boolean [rows][cols];
		
		
		if(rows==9)
			placeMines(MINES_EASY);
		if(rows==16)
			placeMines(MINES_MEDIUM);
		if(rows==30)
			placeMines(MINES_HARD);

		placeNumbers();

	}

	private void placeNumbers() {
		for(int i=0;i<rows;i++)
			for(int j=0;j<cols;j++){
				if(matrix[i][j]==0){
					List<Point> neigh=MatrixUtil.getNeightBors6(rows, cols, new Point(i,j));
					for(Point p:neigh)
						if(matrix[p.x][p.y]==MINE)
							matrix[i][j]++;
				}
			}	
	}

	private void placeMines(int mines) {
		this.mines=mines;
		int cont=0;
		while(cont!=mines){
			Point p=MatrixUtil.randomCell(rows, cols);
			if(matrix[p.x][p.y]==0){
				matrix[p.x][p.y]=MINE;
				cont++;
			}
		}

	}

	public void touchOn(int row,int col){
		if(mineFound)
			return;
		if(showed[row][col])
			return;
		showed[row][col]=true;
		if(matrix[row][col]==MINE){
			mineFound=true;
			return;
		}
		if(matrix[row][col]!=EMPTY){
			showed[row][col]=true;
			return;
		}
		showed[row][col]=false;
		showEmpty(row,col);
	}

	private void showEmpty(int i, int j) {
		if(matrix[i][j]==MINE || showed[i][j])
			return;
		showed[i][j]=true;
		if(matrix[i][j]==EMPTY){
			List<Point> l=MatrixUtil.getNeightBors6(rows, cols,new Point(i,j));
			for(Point p:l)
				showEmpty(p.x, p.y);
		}

	}
	public boolean isVisible(int row,int col){
//		if(true)
//			return true;
		return showed[row][col];
	}
	public int getValueAt(int row,int col){
		return matrix[row][col];
	}
	public boolean isMineFound() {
		return mineFound;
	}
	public boolean isComplete(){
		for(int i=0;i<rows;i++)
			for(int j=0;j<cols;j++)
				if(matrix[i][j]!=MINE && !showed[i][j])
					return false;
		return true;
	}
	public int getRows() {
		return rows;
	}
	public int getCols() {
		return cols;
	}

	public void showAllMines() {
		for(int i=0;i<rows;i++)
			for(int j=0;j<cols;j++)
				if(matrix[i][j]==MINE)
					showed[i][j]=true;
		
	}






}
