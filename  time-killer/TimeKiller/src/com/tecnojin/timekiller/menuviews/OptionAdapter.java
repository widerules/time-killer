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

import java.util.LinkedList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.tecnojin.timekiller.R;
import com.tecnojin.timekiller.activity.OptionActivity;
import com.tecnojin.timekiller.games.descriptors.options.Option;
import com.tecnojin.timekiller.games.descriptors.options.OptionSet;

public class OptionAdapter extends ArrayAdapter<Option>{
	private LayoutInflater li;
	private OptionSet options;
	private OptionActivity act;


	public OptionAdapter(Context context, int textViewResourceId,OptionSet options,OptionActivity o) {
		super(context, textViewResourceId);
		li=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.act=o;

		this.options=options;
	}
	@Override
	public Option getItem(int position) {
		return options.getOptionAt(position);
	}
	@Override
	public int getCount() {
		return options.getOptionCount();
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;

		Option o=options.getOptionAt(position);

		if(v!=null)
			return v;
		if(o.getType()==Option.ON_OFF){
			v=li.inflate(R.layout.on_off_option_item,null);
			TextView optionName=(TextView) v.findViewById(R.id.optionName);
			TextView option2=(TextView) v.findViewById(R.id.option2);
			option2.setVisibility(View.INVISIBLE);
			ToggleButton toggle=(ToggleButton) v.findViewById(R.id.toggle);	 
			optionName.setText(o.getViewName());
			boolean b=Boolean.parseBoolean(o.getCurrentValue());
			toggle.setChecked(b);	    	
			toggle.setOnCheckedChangeListener(new myCheckedChangeListener(options, o));
		}
		if(o.getType()==Option.MULTIPLE){
			v=li.inflate(R.layout.multi_option_item,null);
			TextView optionName=(TextView) v.findViewById(R.id.optionName);
			TextView option2=(TextView) v.findViewById(R.id.option2);
			TextView option3=(TextView) v.findViewById(R.id.option3);	  

			option3.setVisibility(View.INVISIBLE);
			optionName.setText(o.getViewName());
			option2.setText(o.getViewStringForValue(o.getCurrentValue()));	 


		}

		return v;
	}

	public void clickOn(int index){
		Option o=options.getOptionAt(index);
		if(o.getType()==Option.MULTIPLE){
			new myOptionDialog(getContext(), options, o).show();
		}
	}

	class myCheckedChangeListener implements OnCheckedChangeListener{
		private OptionSet os;
		private Option o;

		public myCheckedChangeListener(OptionSet os, Option o) {
			super();
			this.os = os;
			this.o = o;
		}
		public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
			String a=arg1+"";
			o.setCurrentValue(a);
			os.save(OptionAdapter.this.getContext());
		}
	}

	class myOptionDialog extends AlertDialog{
		private OptionSet os;
		private Option o;

		public myOptionDialog(Context context, OptionSet os, Option o) {
			super(context);
			this.os = os;
			this.o = o;

			init();
		}

		private void init() {
			ListView l=new ListView(getContext());
			LinkedList<String> ll=new LinkedList<String>();
			for(int i=0;i<o.getOptionsViewName().length;i++){
				ll.add(getContext().getResources().getString(o.getOptionsViewName()[i]));
			}
			ArrayAdapter<String> adapter=new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,ll);
			l.setAdapter(adapter);
			setView(l);
			setCancelable(true);
			l.setOnItemClickListener(new OnItemClickListener() {

				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					String s=(String) arg0.getItemAtPosition(arg2);

					int pos=-1;
					for(int i=0;i<o.getOptionsViewName().length;i++){
						String ss=getContext().getString(o.getOptionsViewName()[i]);
						if(s.equals(ss)){
							pos=i;
							break;
						}
					}

					o.setCurrentValue(o.getPossiblesValue()[pos]);
					os.save(getContext());
					
					dismiss();
					Intent i=act.getIntent();
					 
					act.startActivity(i);
					act.finish();
					
				}

			});
		}



	}



}
