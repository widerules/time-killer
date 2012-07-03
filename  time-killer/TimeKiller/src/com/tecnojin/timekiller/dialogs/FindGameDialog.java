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
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.tecnojin.timekiller.R;
import com.tecnojin.timekiller.games.GameManager;
import com.tecnojin.timekiller.games.descriptors.GameDescriptor;


public class FindGameDialog extends AlertDialog {
	private gameFindListener listener;
	private LayoutInflater li;
	private ListView list;
	private TextView tv;
	private List<GameDescriptor> descriptors;
	private ArrayAdapter<GameDescriptor> adapter;
	private LinkedList<GameDescriptor> founds;

	public FindGameDialog(Context context,gameFindListener listener) {
		super(context);
		this.listener=listener;
		init();
	}

	private void init() {
		descriptors=GameManager.instance(getContext()).getGameList(getContext());
		li=(LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout=li.inflate(R.layout.game_find_dialog, null);
		setView(layout);
		list=(ListView) layout.findViewById(R.id.list);
		tv=(TextView) layout.findViewById(R.id.text);
		founds=new LinkedList<GameDescriptor>();
		setCancelable(true);
		adapter=new SimpleGameAdapter(getContext(), R.layout.sliim_game_list_item);
		list.setAdapter(adapter);

		fillAdapter();


		tv.addTextChangedListener(new TextWatcher() {
			
			public void onTextChanged(CharSequence s, int start, int before, int count) {
								
			}
			
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
								
			}
			
			public void afterTextChanged(Editable s) {
				search();
				
			}
		});
		
		list.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				listener.gameFound(founds.get(arg2));
				dismiss();
				
			}
		
		});


	}
	private void search() {		
		String s=tv.getText().toString();
		if(s.length()==0)
			return;
		founds.clear();
		for(GameDescriptor g:descriptors){
			String name=getContext().getString(g.getName());
			if(name.toLowerCase().contains(s.toLowerCase()))				
				founds.add(g);
		}
		fillAdapter();

	}
	private void fillAdapter() {
		list.invalidate();
		adapter.notifyDataSetInvalidated();
	}

	public interface gameFindListener{
		public void gameFound(GameDescriptor g);
	}
	class SimpleGameAdapter extends ArrayAdapter<GameDescriptor>{

		public SimpleGameAdapter(Context context, int textViewResourceId) {
			super(context, textViewResourceId);
		}

		@Override
		public int getCount() {
			return founds.size();
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View v = convertView;
			GameDescriptor g=founds.get(position);
			v=li.inflate(R.layout.sliim_game_list_item, null);
			ImageView icon=(ImageView) v.findViewById(R.id.gameIcon);
			icon.setImageResource(g.getIcon());
			TextView name=(TextView) v.findViewById(R.id.game_name);
			name.setText(g.getName());


			return v;
		}

	}
}
