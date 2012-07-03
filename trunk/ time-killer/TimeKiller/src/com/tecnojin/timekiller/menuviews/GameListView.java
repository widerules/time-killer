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
package com.tecnojin.timekiller.menuviews;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.tecnojin.timekiller.games.descriptors.GameDescriptor;

public class GameListView extends ListView{
	private GameListAdapter gameListAdapter;
	public GameListView(Context context) {
		super(context);
		gameListAdapter=new GameListAdapter(context,android.R.layout.simple_list_item_1);
		setAdapter(gameListAdapter);
		
		setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				gameListAdapter.touchOn(arg2);
				invalidate();
				gameListAdapter.notifyDataSetInvalidated();
				
			}
		});
	}
	public void rollTo(GameDescriptor g){
		int index=gameListAdapter.getPosition(g);
		if(index>=0)
			setSelection(index);
	}

}
