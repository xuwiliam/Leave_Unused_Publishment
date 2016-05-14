package com.example.leave_unused_publishment.DB;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

public class DBUtil {
  private DBhelper helper;
  public static final String DATABASEPATH = Environment.getExternalStorageDirectory().getPath() + "/leave_unused/leave_unused.db";
  public DBUtil(Context context){
	  helper = new DBhelper(context,DATABASEPATH,null,1);
	  
  }
  public void insertdata(String name,String content,String comment){
	SQLiteDatabase db = helper.getWritableDatabase();
	db.execSQL("insert into publishment (name,content,comment) values(?,?,?)", new Object[]{name,content,comment});
	db.close();
  }
  public Map readdata(int num,int primarykey){
	  SQLiteDatabase db  = helper.getReadableDatabase();
	  String sql = "select * from publishment";
	  Cursor cursor = db.rawQuery(sql, null);
	 	  Map map=null;
	  int id;
	  String v;
	  if( cursor.moveToPosition(cursor.getCount()-primarykey-1)){
		  map = new HashMap();
		  id = cursor.getInt(0);
		  v = cursor.getString(cursor.getColumnIndex("name"))+","+cursor.getString(cursor.getColumnIndex("content"))+","+cursor.getString(cursor.getColumnIndex("comment"));
		  map.put(id, v);
		  num--;
		  while(cursor.moveToPrevious()&&(num-1>0)){
			  id = cursor.getInt(0);
			   v = cursor.getString(cursor.getColumnIndex("name"))+","+cursor.getString(cursor.getColumnIndex("content"))+","+cursor.getString(cursor.getColumnIndex("comment"));
		       
			   map.put(id, v);
			   num--;
		  }
	  }
	  db.close();
	  return map;
	  
  }
  public boolean deletedata(int id){
	SQLiteDatabase db = helper.getWritableDatabase();
	if(db.isOpen()){
		db.execSQL("delete from publishment where id=?", new Object[]{id});
		db.close();
	    return true;
	}
      
	  return false;
  }
  public void updatedata(String comment,int id){
	SQLiteDatabase db = helper.getWritableDatabase();
	Cursor cursor = db.rawQuery("select * from publishment", null);
	
	
	if(cursor.moveToPosition(id)){
		String lastcmm = cursor.getString(3);
		lastcmm=lastcmm+"@"+comment;
		db.execSQL("update publishment set comment=? where id=?", new Object[]{lastcmm,id});
	}
	db.close();
  }
  public int getCount(){
	  SQLiteDatabase db = helper.getReadableDatabase();
	  Cursor cursor = db.rawQuery("select * from publishment", null);
	  int rel = cursor.getCount();
	  db.close();
	  return rel;
  }
  public void showdata(){
	  SQLiteDatabase db = helper.getReadableDatabase();
	  Cursor cursor = db.rawQuery("select * from publishment", null);
	  while(cursor.moveToNext()){
		  Log.e("record",String.valueOf(cursor.getInt(0))+" "+cursor.getString(1)+cursor.getString(2)+" "+cursor.getString(3));
	  }
  }
}
