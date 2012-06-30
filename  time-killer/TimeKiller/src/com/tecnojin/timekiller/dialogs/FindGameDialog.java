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
package com.tecnojin.timekiller.dialogs;

import java.util.LinkedList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.tecnojin.timekiller.R;
import com.tecnojin.timekiller.games.GameManager;
import com.tecnojin.timekiller.games.descriptors.GameDescriptor;

public class FindGameDialog extends AlertDialog {
	private gameFindListener listener;
	private LayoutInflater li;
	private ListView list;
	private TextView tv;
	private List<String> names;
	private ArrayAdapter<String> adapter;
	private LinkedList<String> found;
	
	public FindGameDialog(Context context,gameFindListener listener) {
		super(context);
		this.listener=listener;
		init();
	}
	
	private void init() {
		names=GameManager.instance(getContext()).getGameNameList();
		li=(LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout=li.inflate(R.layout.game_find_dialog, null);
		setView(layout);
		
		
		
		list=(ListView) layout.findViewById(R.id.list);
		tv=(TextView) layout.findViewById(R.id.text);
		found=new LinkedList<String>();
		setCancelable(true);
		
		adapter=new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,found);
		list.setAdapter(adapter);
		
		fillAdapter();
		
		
		
		tv.setOnEditorActionListener(new OnEditorActionListener() {
			
			public boolean onEditorAction(TextView arg0, int arg1, KeyEvent arg2) {
				search();
				return false;
			}

			
		});
		
		
	}
	private void search() {
		String s=tv.getText().toString();
		found.clear();
		for(String ss:names)
			if(ss.toLowerCase().contains(s.toLowerCase()))
				found.add(ss);
		fillAdapter();
		
	}
	private void fillAdapter() {
		adapter=new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,found);
		list.invalidate();
		adapter.notifyDataSetInvalidated();
		
		
	}

	public interface gameFindListener{
		public void gameFound(GameDescriptor g);
	}

}
