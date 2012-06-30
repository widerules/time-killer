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
package com.tecnojin.timekiller.games.alignpuzzle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class AlignState {
	public static final int C1=1;
	public static final int C2=2;
	public static final int C3=3;
	public static final int C4=4;
	public static final int EMPTY=5;
	public static final int LOCKED=6;

	private ArrayList<Integer> l1,l2,l3,l4,l5;

	/* 	0   1   2    3
	 * 
	 * l1 ,l1 ,l1 , l1
	 * l2 ,l2 ,l2 , l2
	 * l3 ,l3 ,l3 , l3
	 * l4 ,l4 ,l4 , l4  
	 * l5 ,l5 ,l5 , l5
	 *  
	 */

	/*View from list
	 *    0  1  2  3  4  5  6  7  8  9 10
	 *    
	 * 0                 l5
	 * 1                 l4
	 * 2                 l3
	 * 3 				 l2
	 * 4 				 l1
	 * 5  l5,l4,l3,l2,l1 00 l1,l2,l3,l4,l5
	 * 6                 l1
	 * 7                 l2
	 * 8                 l3
	 * 9                 l4
	 * 10                l5  
	 *                
	 */
	public AlignState(){
		init();		
	}
	private void init() {
		l1=new ArrayList<Integer>(4);
		l2=new ArrayList<Integer>(4);
		l3=new ArrayList<Integer>(4);
		l4=new ArrayList<Integer>(4);
		l5=new ArrayList<Integer>(4);
		Integer [] in=new Integer []{C1,C2,C3,C4};
		Collections.addAll(l1, in);
		Collections.addAll(l2, in);
		Collections.addAll(l3, in);
		Collections.addAll(l4, in);
		in=new Integer []{EMPTY,LOCKED,LOCKED,LOCKED};
		Collections.addAll(l5, in);		

	}
	public void shuffle(){
		for(int i=0;i<10;i++){
		Collections.shuffle(l1);
		Collections.shuffle(l2);
		Collections.shuffle(l3);
		Collections.shuffle(l4);
		Collections.shuffle(l5);	
		}
		
		
	}
	public void reset(){
		init();
	}

	public void move(int row,int col){
		if(row==5 && col==5)
			return ;

		List<Integer> list=null;
		int index=-1;

		//Horizontal
		if(row==5){
			if(col<5){
				list=getListFrom(col);
				index=3;
			}
			if(col>5){
				list=getListFrom(col);
				index=1;
			}
		}
		//Vertical
		if(col==5){
			if(row<5){
				list=getListFrom(row);
				index=0;
			}
			if(row>5){
				list=getListFrom(row);
				index=2;
			}
		}
		if(list.get(index)==EMPTY){
			shift(list);
			return;
		}

		List<ElementAndList> neightBors=findNeighBors(list,index);

		boolean swapped=false;
		for(ElementAndList el:neightBors){
			int i=el.value();
			if(i==EMPTY &&  list.get(index)!=LOCKED){
				swap(list,el.list,index);
				swapped=true;
				break;
			}
		}
		if(!swapped)
			shift(list);

	}
	private void shift(List<Integer> list) {
		Integer i=list.remove(0);
		list.add(i);

	}
	private void swap(List<Integer> list, List<Integer> list2, int index) {
		int val1=list.get(index);
		int val2=list2.get(index);
		
		list2.set(index, val1);
		list.set(index, val2);

	}
	private List<ElementAndList> findNeighBors(List<Integer> list, int index) {
		List<ElementAndList> l=new LinkedList<ElementAndList>();

		if(list==l1)
			l.add(new ElementAndList(l2, index));
		if(list==l2){
			l.add(new ElementAndList(l1, index));
			l.add(new ElementAndList(l3, index));
		}
		if(list==l3){
			l.add(new ElementAndList(l2, index));
			l.add(new ElementAndList(l4, index));
		}
		if(list==l4){
			l.add(new ElementAndList(l3, index));
			l.add(new ElementAndList(l5, index));
		}
		if(list==l5)
			l.add(new ElementAndList(l4, index));

		return l;
	}

	private List<Integer> getListFrom(int i) {
		if(i==0 || i==10)
			return l5;
		if(i==1 || i==9)
			return l4;
		if(i==2 || i==8)
			return l3;
		if(i==3 || i==7)
			return l2;
		if(i==4 || i==6)
			return l1;
		return null;
	}

	public int getElementAt(int row,int col){
		List<Integer> list=null;
		int index=-1;

		//Horizontal
		if(row==5){
			if(col<5){
				list=getListFrom(col);
				index=3;
			}
			if(col>5){
				list=getListFrom(col);
				index=1;
			}
		}
		//Vertical
		if(col==5){
			if(row<5){
				list=getListFrom(row);
				index=0;
			}
			if(row>5){
				list=getListFrom(row);
				index=2;
			}
		}
		if(list==null)
			return -1;
		return list.get(index);
	}
	@Override
	public String toString() {
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<11;i++){
			for(int j=0;j<11;j++)
				if((i==5 || j==5) && i!=j)
					sb.append(getElementAt(i, j)+" ");
				else
					sb.append("  ");
			sb.append("\n");
		}
		return sb.toString();
	}
	public boolean isComplete(){
		for(int i=0;i<4;i++)
			if(!isOk(i))
				return false;
		return true;
	}
	
	 private boolean isOk(int i) {
		Set<Integer> s=new TreeSet<Integer>();
		s.add(l1.get(i));
		s.add(l2.get(i));
		s.add(l3.get(i));
		s.add(l4.get(i));
		if(s.size()!=1)
			return false;
		return true;
	}

	class ElementAndList{
		 List<Integer> list;		
		 int index ;
		public ElementAndList(List<Integer> list, int index) {
			super();
			this.list = list;
			this.index = index;
		}
		public int value() {
			return list.get(index);
		}
		
		
	}



}
