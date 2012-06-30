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
package com.tecnojin.timekiller.games.fillit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class FillItState {
	
	private Cell [][] matrix;
	private int colors;
	private int rows,cols;
	
	private int cont=0;


	public FillItState(int [][] matrix, int colors) {
		super();
		rows=matrix[0].length;
		cols=matrix.length;
		this.matrix=new Cell [rows][cols];
		for(int i=0;i<rows;i++)
			for(int j=0;j<cols;j++)
				this.matrix[i][j]=new Cell(matrix[i][j],i,j);		
		this.colors=colors;
		

	}
	public FillItState(int colors,int side){
		this.colors=colors;
		this.rows=side;
		this.cols=side;
		matrix=new Cell [rows][cols];
		Random r=new Random();
		for(int i=0;i<rows;i++)
			for(int j=0;j<cols;j++)
				matrix[i][j]=new Cell(r.nextInt(colors),i,j);
		LinkedList<Cell> r1=new LinkedList<FillItState.Cell>();
		reachable(matrix[0][0], r1);
		for(Cell cc:r1)
			cc.inc();
		
	
	}
	private int findNumberOfColors() {
		ArrayList<Integer> l=new ArrayList<Integer>();
		for(int i=0;i<rows;i++)
			for(int j=0;j<cols;j++)
				if(!l.contains(matrix[i][j].value()))
					l.add(matrix[i][j].value());
		return l.size();
	}
	@Override
	public String toString() {
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<rows;i++){
			for(int j=0;j<cols;j++)
				sb.append(matrix[i][j].value()+" ");
			sb.append("\n");
		}		
		return sb.toString();
	}

	public void applyMoove(int color){
		//if(!generatePossiblesMoves().contains(color))
		//	return;
		if(color<0 || color>colors)
			throw new IllegalArgumentException("Color not supported "+color+" size of colors "+colors);
		if(color==matrix[0][0].value())
			return;
		int oldColor=matrix[0][0].value();
		
		changeColor(color);
		
		
		while(true){
			if(!newChangeColor(color))
				break;
		}
		
		//applyColor(matrix[0][0],oldColor,color);
		
		
		
		
//		cont=0;
//		HashMap<Cell, Boolean> h=new HashMap<Cell, Boolean>();
//		reachIterative(matrix[0][0],h,color);
		//reachable(matrix[0][0], l);
		
		

	}
	
	private boolean newChangeColor(int color) {
		boolean change=false;
		for(int i=0;i<rows;i++)
			for(int j=0;j<cols;j++)
				if(matrix[i][j].getNum()==0 && matrix[i][j].value==color && hasNotZeroNeigh(matrix[i][j])){
					matrix[i][j].setNum(matrix[0][0].getNum());
					change=true;					
				}
		return change;
	}
	private boolean hasNotZeroNeigh(Cell current) {
		int row=current.getRow();
		int col=current.getCol();
		if(row>0 && matrix[row-1][col].getNum()!=0)
			return true;
		if(row<matrix.length-1 && matrix[row+1][col].getNum()!=0)
			return true;
		if(col>0 && matrix[row][col-1].getNum()!=0)
			return true;
		if(col<matrix.length-1 && matrix[row][col+1].getNum()!=0)
			return true;
		return false;
	}
	private void changeColor(int color) {
		for(int i=0;i<rows;i++)
			for(int j=0;j<cols;j++)
				if(matrix[i][j].getNum()!=0)
					matrix[i][j].setValue(color);
		
	}
	private void reachIterative(Cell cell, HashMap<Cell, Boolean> map,int color) {
		LinkedList<Cell> working=new LinkedList<Cell>();
		working.add(cell);
		while(!working.isEmpty()){
			Cell current=working.removeFirst();		
			if(!map.containsKey(current)){				
				map.put(current, true);
				addNeighBors(current,working);
				current.setValue(color);
				
			}
		}
		
		
		
	}
	private void addNeighBors(Cell current, LinkedList<Cell> l) {
		int row=current.getRow();
		int col=current.getCol();
		if(row>0 && matrix[row-1][col].value==current.value)
			l.addFirst(matrix[row-1][col]);		
		if(row<matrix.length-1 && matrix[row+1][col].value==current.value)
			l.addFirst(matrix[row+1][col]);
		if(col>0 && matrix[row][col-1].value==current.value)
			l.addFirst(matrix[row][col-1]);
		if(col<matrix.length-1 && matrix[row][col+1].value==current.value)
			l.addFirst(matrix[row][col+1]);
		
	}
	private void applyColor(Cell current, int oldColor, int color) {
		boolean reach=false;
		if(current.value()==oldColor){
			current.setValue(color);
			reach=true;
		}
		if(reach && current.getCol()!=cols-1){
			applyColor(matrix[current.getRow()][current.getCol()+1], oldColor, color);
		}
		if(reach && current.getRow()!=rows-1){
			applyColor(matrix[current.getRow()+1][current.getCol()], oldColor, color);
		}
		if(reach && current.getCol()!=0 ){
			applyColor(matrix[current.getRow()][current.getCol()-1], oldColor, color);
		}
		if(reach && current.getRow()!=0 ){
			applyColor(matrix[current.getRow()-1][current.getCol()], oldColor, color);
		}


	}
	public int getColorAt(int row,int col){
		return matrix[row][col].value();
	}
	public int getRows() {
		return rows;
	}
	public int getCols() {
		return cols;
	}
	public int getColorsNumber() {
		return colors;
	}
	public List<Integer> generatePossiblesMoves(){
		HashMap<Integer, Integer> map=new HashMap<Integer, Integer>();
		fillNearestMap(matrix[0][0],map);
		ArrayList<Integer> list=new ArrayList<Integer>();
		list.addAll(map.keySet());		

		return list;
	}

	private void fillNearestMap(Cell current, HashMap<Integer, Integer> map) {
		int val=current.value();
		int r=current.getRow();
		int c=current.getCol();

		if(current.getCol()!=cols-1){
			int val2=matrix[r][c+1].value();
			if(val!=val2){
				addInMap(val2,map);
			}
			else
				fillNearestMap(matrix[r][c+1],map);
		}
		if(current.getRow()!=rows-1){
			int val2=matrix[r+1][c].value();
			if(val!=val2){
				addInMap(val2,map);
			}
			else
				fillNearestMap(matrix[r+1][c],map);
		}

	}
	private void addInMap(int val2, HashMap<Integer, Integer> map) {
		if(!map.containsKey(val2)){
			map.put(val2, 0);
		}
		Integer i=map.get(val2);
		i++;
		map.put(val2, i);

	}

	public boolean isComplete(){
		int val=matrix[0][0].value();
		for(int i=0;i<rows;i++)
			for(int j=0;j<cols;j++)
				if(matrix[i][j].value()!=val)
					return false;
		return true;

	}

	
	private void reachable(Cell current, LinkedList<Cell> l) {
		if(!l.contains(current)){
			l.add(current);
			List<Cell> sons=getNearbyOf(current);
			for(Cell c:sons)
				reachable(c, l);
		}


	}
	private List<Cell> getNearbyOf(Cell current) {
		LinkedList<Cell> l=new LinkedList<Cell>();
		int row=current.getRow();
		int col=current.getCol();
		if(row>0)
			l.add(matrix[row-1][col]);		
		if(row<matrix.length-1)
			l.add(matrix[row+1][col]);
		if(col>0)
			l.add(matrix[row][col-1]);
		if(col<matrix.length-1)
			l.add(matrix[row][col+1]);
		Iterator<Cell> i=l.iterator();
		while(i.hasNext()){
			if(i.next().value()!=current.value())
				i.remove();
		}


		return l;
	}

	public class Cell {
		private Integer value;
		private int row,col;
		private int num;

		public Cell(int value,int row,int col){
			this.value=value;
			this.row=row;
			this.col=col;

		}	
		public Cell (Cell c){
			this(c.value, c.row, c.col);
		}
		public void setValue(int value){
			this.value=value;
			inc();
		}

		public int getRow() {
			return row;
		}
		public int getCol() {
			return col;
		}

		@Override
		public String toString() {
			return row+" "+col+" = "+value();
		}
		public int value() {
			return value;
		}
		@Override
		public int hashCode() {
			return Integer.parseInt(""+row+""+col);
		}
		public int getNum() {
			return num;
		}
		public void inc(){
			num++;
		}
		public void setNum(int num) {
			this.num = num;
		}

	}
}
