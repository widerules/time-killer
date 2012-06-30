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
package com.tecnojin.timekiller.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

public class DatabaseManager {
	private static DatabaseManager instance;
	private static final File DB_FOLDER=new File(Environment.getExternalStorageDirectory(),"tecnojin"+File.separatorChar+"timeKiller"+File.separatorChar);
	private static final File DB_FILE=new File(DB_FOLDER,"dataBase.db");
	
	private static final String pass="l4C0n1C0";
	private static final int DB_VERSION=1;

	private DatabaseManager(Context c) throws IOException{

		if(needCopy(c)){
			Log.d("Jin","Recopy db");
			DB_FOLDER.mkdirs();
			copyDbFromAssets(c);
		}
		

	}
	private boolean needCopy(Context c) {
		if(!DB_FILE.exists())
			return true;

		List<HashMap<String, String>> l=query("select * from conf where val1='ver';");
		if(l.isEmpty())
			return true;
		HashMap<String, String> a=l.get(0);
		int b=Integer.parseInt(a.get("val2"));
		if(b!=DB_VERSION)
			return true;
		return false;
	}
	private SQLiteDatabase connect() {
		return SQLiteDatabase.openOrCreateDatabase(DB_FILE.getAbsolutePath(),null);		
	}
	private void close(SQLiteDatabase db){
		if(db==null)
			return;
		if(!db.isOpen())
			return;
		db.close();
	}
	private void copyDbFromAssets(Context c) throws IOException {		
		AssetManager am = c.getAssets();
		DB_FILE.createNewFile();
		OutputStream os = new FileOutputStream(DB_FILE);

		byte []b = new byte[1024];
		int i, r;
		String []Files = am.list("database");
		Arrays.sort(Files);
		for(i=0;i<Files.length;i++)
		{		
			String fn = Files[i];
			if(fn.endsWith(".db")){
				InputStream is = am.open("database"+File.separatorChar+fn);
				while((r = is.read(b)) != -1)
					os.write(b, 0, r);
				is.close();
			}
		}
		os.close();
	}
	public static synchronized DatabaseManager instance(Context c) throws IOException{
		if(instance==null)
			instance=new DatabaseManager(c);
		return instance;
	}
	public synchronized int sizeOfTable(String table){
		SQLiteDatabase db=connect();
		Cursor c=db.rawQuery("Select count(*) from "+table+";", null);
		int size=-1;

		if(c.moveToFirst())
			size=c.getInt(0);

		c.close();
		close(db);
		return size;
	}
	public List<HashMap<String, String>> query(String sql){
		return query(sql,null);

	}
	public List<HashMap<String, String>> query(String sql,boolean [] columnToDecrypt){
		LinkedList<HashMap<String, String>> map=new LinkedList<HashMap<String,String>>();
		SQLiteDatabase db=connect();
		try{

			Cursor c=db.rawQuery(sql,null);
			if(c.moveToFirst()){
				while(!c.isAfterLast()){
					map.add(convert(c,columnToDecrypt));
					c.moveToNext();
				}
			}
			c.close();
		}
		catch (Exception e) {

		}
		close(db);
		return map;


	}
	private HashMap<String, String> convert(Cursor c, boolean[] columnToDecrypt) {
		HashMap<String, String> map=new HashMap<String, String>();
		for(int i=0;i<c.getColumnCount();i++){
			String s=c.getString(i);
			if(columnToDecrypt!=null && columnToDecrypt[i]){
				//decrypt
			}
			map.put(c.getColumnName(i), s);
		}
		return map;
	}
 
}
