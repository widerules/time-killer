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
package com.tecnojin.timekiller.games.impiccato;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tecnojin.timekiller.R;
import com.tecnojin.timekiller.activity.GameActivity;
import com.tecnojin.timekiller.games.GameManager;
import com.tecnojin.timekiller.games.descriptors.GameDescriptor;
import com.tecnojin.timekiller.util.ActivityUtil;
import com.tecnojin.timekiller.util.DatabaseManager;
import com.tecnojin.timekiller.views.matrix.MatrixKeyBoard;
import com.tecnojin.timekiller.views.matrix.MatrixKeyBoard.onButtonPressedListener;

public class ImpiccatoActivity extends GameActivity {
	private TextView word;
	private ImageView diagram;
	private EditText input;
	private Impiccato im;
	private MatrixKeyBoard m;

	

	private void initGame() {

		GameDescriptor gd=GameManager.instance(this).getGame(GameManager.HANGMAN, this);

		String o=gd.getOptions().findOptionForKey("lang").getCurrentValue();

		String word=resolveWord(o);

		im=new Impiccato(word);

		updateWord();

	}
	private void updateWord() {
		word.setText(separate(im.getCurrentWord()));	
	}
	private String separate(String s) {
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<s.length();i++)
			sb.append(s.charAt(i)+" ");
		sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}
	private String resolveWord(String o) {
		try {
			DatabaseManager db=DatabaseManager.instance(this);
			String table="imp_"+o;
			int size=db.sizeOfTable(table);
			int id=new Random().nextInt(size);
			if(id==0)
				id++;
			List<HashMap<String, String>> s=db.query("SELECT * FROM "+table+" WHERE ID = "+id+";");
			Log.d("Jin", "Word is "+ s.get(0).get("value"));
			return s.get(0).get("value");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "lol";
	}
	@Override
	public void loadGame() {
		LayoutInflater l=ActivityUtil.getLayoutInflater(this);
		View v=l.inflate(R.layout.impiccato_layout, null);
		gamelayout.addView(v);
		word=(TextView) v.findViewById(R.id.word);
		diagram=(ImageView) v.findViewById(R.id.hang_diagram);

		LinearLayout ll=(LinearLayout) findViewById(R.id.keyboardContainer);
		m=new MatrixKeyBoard(this);
		m.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		ll.addView(m);
		m.setButtonListener(new onButtonPressedListener() {

			public void onPress(Character c) {
				m.disableButton(c);
				int result=	im.tryChar(c);
				if(result==Impiccato.CHAR_FOUND)
					m.changeBackground(c+"", Color.GREEN);
				if(result==Impiccato.CHAR_NOT_FOUND)
					m.changeBackground(c+"", Color.RED);
				
				if(im.isComplete() || im.getErrors()>5){
					gameTerminated(getResources().getString(R.string.wordis)+" \n"+im.getoriginalWord());
					m.setEnabled(false);
					
					
						
				}
				updateWord();

			}
		});

		initGame();
		
	}

}
