package com.tecnojin.timekiller.games.memory;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import com.google.ads.s;

import android.graphics.Point;
import android.os.Handler;
import android.util.Log;

public class MemoryState {
	public static final int FIRST_TOUCH=1;
	public static final int SECOND_TOUCH_WRONG=2;
	public static final int SECOND_TOUCH_OK=3;
	public static final int STILL_FLIPPED=4;
	public static final int NOTHING_TO_DO=5;
	public static final long timeout=500;
	private int [][] matrix;
	private boolean [][] flipped;
	private int rows,cols;
	private Point firstTap,secondTap;

	private int founds;

	private int errors;


	public MemoryState(int row, int col) {
		super();
		this.rows = row;
		this.cols = col;

		reset();
	}

	private void reset() {
		errors=0;
		founds=0;

		matrix=new int [rows][cols];
		flipped=new boolean [rows][cols];
		LinkedList<Integer> l=new LinkedList<Integer>();
		for(int i=0;i<((rows*cols)/2);i++){
			l.add(i+1);
			l.add(i+1);
		}

		Collections.shuffle(l);
		Collections.shuffle(l);

		for(int i=0;i<rows;i++)
			for(int j=0;j<cols;j++)
				matrix[i][j]=l.removeFirst();				

	}
	public int tapOnCell(int row,int col){		
		if(isFlipped(row, col))
			return STILL_FLIPPED;
		if(firstTap==null ){
			firstTap=new Point(row,col);
			flipped[firstTap.x][firstTap.y]=true;
			return FIRST_TOUCH;
		}
		if(firstTap!=null && secondTap==null){
			secondTap=new Point(row,col);
			if(matrix[firstTap.x][firstTap.y]==matrix[secondTap.x][secondTap.y]){
				flipped[firstTap.x][firstTap.y]=true;
				flipped[secondTap.x][secondTap.y]=true;
				firstTap=null;
				secondTap=null;
				founds++;
				return SECOND_TOUCH_OK;				
			}
			if(matrix[firstTap.x][firstTap.y]!=matrix[secondTap.x][secondTap.y]){
				flipped[firstTap.x][firstTap.y]=true;
				flipped[secondTap.x][secondTap.y]=true;
				errors++;
				return SECOND_TOUCH_WRONG;				
			}
		}
		return NOTHING_TO_DO;

	}

	public void updateTapped() {
		flipped[firstTap.x][firstTap.y]=false;
		flipped[secondTap.x][secondTap.y]=false;
		firstTap=null;
		secondTap=null;

	}

	public boolean isFlipped(int row, int col) {
		
		return flipped[row][col];
	}
	public int getRows() {
		return rows;
	}
	public int getCols() {
		return cols;
	}

	public boolean isCompleted(){
		return founds==((rows*cols)/2);
	}

	public Integer getAt(int i, int j) {
		return matrix[i][j];
	}



}
