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
package com.tecnojin.timekiller.games.sudoku;

import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

import com.google.ads.o;

import android.util.Log;

public class SudokuState {
	private short [][] matrix;
	private boolean [][] original;
	private int side;

	public SudokuState(short[][] matrix) {
		super();
		this.matrix = matrix;
		side=matrix.length;
	}
	public SudokuState(String sudoku,String solution){
		matrix=new short [9][9];
		side=9;
		StringTokenizer st=new StringTokenizer(sudoku,"*");
		int row=0;
		while(st.hasMoreTokens()){
			String s=st.nextToken();
			for(int i=0;i<s.length();i++)
				matrix[row][i]=Short.parseShort(s.charAt(i)+"");
			row++;
		}
		st=new StringTokenizer(solution,"*");
		row=0;
		while(st.hasMoreTokens()){
			String s=st.nextToken();
			Set<Short> set=new TreeSet<Short>();
			for(int i=0;i<s.length();i++)
				set.add(Short.parseShort(s.charAt(i)+""));
			for(int j=0;j<side;j++)
				if(!set.contains(matrix[row][j]))
					matrix[row][j]=0;
			row++;
		}
		initOriginal();
		
	}



	private void initOriginal() {
		original=new boolean [matrix.length][matrix[0].length];
		for(int i=0;i<original.length;i++)
			for(int j=0;j<original[0].length;j++)
				if(matrix[i][j]!=0)
					original[i][j]=true;
		
	}
	public boolean isComplete(){
		boolean r=checkRows();
		boolean c=checkCols();
		boolean s=checkSquares();

		return r&&s&&c;
	}

	private boolean checkRows() {
		for(int i=0;i<side;i++)
			if(!checkRow(i))
				return false;
		return true;
	}

	private boolean checkRow(int row) {
		Set<Short> s=new TreeSet<Short>();
		for(int i=0;i<side;i++){

			if(matrix[row][i]==0)
				return false;
			else
				s.add(matrix[row][i]);
		}
		if(s.size()!=side)
			return false;

		return true;
	}

	private boolean checkCols() {
		for(int i=0;i<side;i++)
			if(!checkCol(i))
				return false;
		return true;
	}

	private boolean checkCol(int col) {
		Set<Short> s=new TreeSet<Short>();
		for(int i=0;i<side;i++)
			if(matrix[i][col]==0)
				return false;
			else
				s.add(matrix[i][col]);	
		if(s.size()!=side)
			return false;

		return true;
	}

	private boolean checkSquares() {
		for(int i=0;i<side;i+=Math.sqrt(side))
			for(int j=0;j<side;j+=Math.sqrt(side))
				if(!checkSquare(i,j))
					return false;
		return true;
	}

	private boolean checkSquare(int row, int col) {
		Set<Short> s=new TreeSet<Short>();

		for(int i=row;i<row+Math.sqrt(side);i++)
			for(int j=col;j<col+Math.sqrt(side);j++)
				if(matrix[i][j]==0)
					return false;
				else
					s.add(matrix[i][j]);
		return s.size()==side;
	}
	public short get(int r,int c){
		return matrix[r][c];
	}
	public void set(int r,int c,short val){
		matrix[r][c]=val;
	}
	public int getSide() {
		return side;
	}
	public boolean canEdit(int r,int c){
		return !original[r][c];
	}


}
