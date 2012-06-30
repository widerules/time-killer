package com.tecnojin.timekiller.games.puzzle15;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import com.tecnojin.timekiller.util.MatrixUtil;

import android.graphics.Point;

public class Puzzle15State {
	private int rows,cols;
	private int [][] matrix;
	private int moves;

	public Puzzle15State(int row, int col) {
		super();
		this.rows = row;
		this.cols = col;		
		reset();
	}
	private void reset() {
		matrix=new int [rows][cols];
		int cont=1;
		for(int i=0;i<rows;i++)
			for(int j=0;j<cols;j++)
				matrix[i][j]=cont++;
		matrix[rows-1][cols-1]=0;
		shuffle();
		moves=0;		
	}
	private void shuffle() {
		int repetition=new Random().nextInt(50)+20;
		Point p=new Point(rows-1,cols-1);
		Set<Move> done=new HashSet<Move>();
		while(repetition-- > 0){
			List<Point> l=MatrixUtil.getNeightBors(rows, cols, p);
			Collections.shuffle(l);
			boolean found=false;
			for(Point pp:l){
				Move m=new Move(p, pp);
				if(!done.contains(m)){
					found=true;
					done.add(m);
					swap(p,pp);
					p=pp;
					break;
				}
			}
			if(!found){
				Point pp=l.get(0);
				swap(p,pp);
				p=pp;
			}
		}
	}
	private void swap(Point p, Point pp) {
		int valp=matrix[p.x][p.y];
		matrix[p.x][p.y]=matrix[pp.x][pp.y];
		matrix[pp.x][pp.y]=valp;		
	}
	public boolean isCompleted(){
		if(matrix[rows-1][cols-1]!=0)
			return false;
		int cont=1;
		for(int i=0;i<rows;i++)
			for(int j=0;j<cols;j++)
				if(matrix[i][j]!=cont++)
					return false;
		return true;
	}
	public void move(int row,int col){
		List<Point> l=MatrixUtil.getNeightBors(rows, cols, new Point(row,col));
		Point hole=null;
		for(Point p:l)
			if(matrix[p.x][p.y]==0){
				hole=p;
				break;
			}
		if(hole==null)
			return;
		moves++;
		swap(hole, new Point(row,col));
	}
	public int getMoves() {
		return moves;
	}
	public int getRows() {
		return rows;
	}
	public int getCols() {
		return cols;
	}
	public int getValueAt(int row,int col){
		return matrix[row][col];
	}

	class Move{
		private Point f,t;

		public Move(Point f, Point t) {
			super();
			this.f = f;
			this.t = t;
		}

		@Override
		public boolean equals(Object o) {
			Move m=(Move) o;
			return m.f.equals(f) && m.t.equals(t);
		}
	}



}
