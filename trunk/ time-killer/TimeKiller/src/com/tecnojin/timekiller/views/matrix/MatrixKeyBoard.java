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
package com.tecnojin.timekiller.views.matrix;

import java.util.LinkedList;

import android.content.Context;
import android.graphics.Color;



public class MatrixKeyBoard extends AbstractMatrix<String>{
	private String [][] keyboard;
	private int cont=0;
	private LinkedList<Character> disabled;
	private onButtonPressedListener buttonListener;
	public MatrixKeyBoard(Context context) {
		super(context, 3,10);
		initKeyBoard();
		setSquare(false);
		disabled=new LinkedList<Character>();
		setSeparatorProportion(0);
		setListener(new CellClickedListener<String>() {

			public void onClick(Cell<String> cell) {
				if(buttonListener==null)
					return;
				if(cell.getContent()== null)
					return;
				if(cell.getContent().length()==0)
					return;
				if(disabled.contains(cell.getContent()))
					return;
				buttonListener.onPress(cell.getContent().charAt(0));


			}

			public void onLonkClick(Cell<String> cell) {


			}
		});
	}

	private void initKeyBoard() {
		keyboard=new String [][] {
				{"q","w","e","r","t","y","u","i","o","p"},
				{"a","s","d","f","g","h","j","k","l",""},
				{"z","x","c","v","b","n","m","","",""}
		};

	}

	@Override
	public Cell<String> createCell(int row, int col) {
		if(keyboard==null)
			initKeyBoard();
		TextCell<String> t=new TextCell<String>(row, col, keyboard[row][col],keyboard[row][col].toUpperCase());
		if(t.getContent().length()!=0)
			t.setBackgroundColor(Color.WHITE);
		t.setTextColor(Color.BLACK);
		t.setTextSize(25);
		return t;
	}
	public void disableButton(Character c){
		disabled.add(c);
	}
	public void changeBackground(String label,int color){
		for(Cell<String> c:this)
			if(c.getContent().equals(label)){
				((TextCell<String>)c).setBackgroundColor(color);
				invalidate();
				return;
			}

	}

	public interface onButtonPressedListener{
		public void onPress(Character c);
	}
	public void setButtonListener(onButtonPressedListener buttonListener) {
		this.buttonListener = buttonListener;
	}



}
