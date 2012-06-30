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
	private static final long timeout=500;
	private int [][] matrix;
	private boolean [][] flipped;
	private int rows,cols;
	private Point lastFlipped;
	private Timer timer;
	private int founds;
	private boolean stillWaiting=false;
	private int errors;
	private onChangeListener listener;


	public MemoryState(int row, int col) {
		super();
		this.rows = row;
		this.cols = col;

		reset();
	}

	private void reset() {
		errors=0;
		founds=0;
		timer=new Timer();
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
	public void tapOnCell(int row,int col){		
		if(isFlipped(row,col))
			return;		
		if(lastFlipped!=null)
			if(lastFlipped.equals(new Point(row,col)))
				return;
		if(lastFlipped==null){
			flipped[row][col]=true;
			lastFlipped=new Point(row,col);					
			return;
		}
		else
			if(!stillWaiting){
				flipped[row][col]=true;
				timer.schedule(new FlippingTask(new Point(row,col)),timeout);
				
			}
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
	class FlippingTask extends TimerTask{
		private Point p;		

		public FlippingTask(Point p) {
			super();
			stillWaiting=true;
			this.p = p;
		}
		@Override
		public void run() {
			stillWaiting=false;

			if(matrix[lastFlipped.x][lastFlipped.y]==matrix[p.x][p.y]){
				flipped[lastFlipped.x][lastFlipped.y]=true;
				flipped[p.x][p.y]=true;
				founds++;
			}
			else{
				flipped[lastFlipped.x][lastFlipped.y]=false;
				flipped[p.x][p.y]=false;
				errors++;
			}
			if(listener!=null)
				listener.update();
			lastFlipped=null;


		}

	}
	public boolean isCompleted(){
		return founds==((rows*cols)/2);
	}

	public Integer getAt(int i, int j) {
		return matrix[i][j];
	}

	public static class onChangeListener{
		private Handler hand;	

		public onChangeListener(Handler hand) {
			super();
			this.hand = hand;
		}

		public void update(){
			hand.sendEmptyMessage(1);
		}
	}
	public void setListener(onChangeListener listener) {
		this.listener = listener;
	}


}
