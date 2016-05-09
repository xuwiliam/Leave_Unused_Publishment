package com.example.leave_unused_publishment.DB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBUtil {
  private DBhelper helper;	
  public DBUtil(Context context){
	  helper = new DBhelper(context,"leave_unused.db",null,1);
	  
  }
  public void insertdata(String name,String content){
	SQLiteDatabase db = helper.getWritableDatabase();
	db.execSQL("insert into publishment (name,content) values(?,?)", new Object[]{name,content});
  }
  public Cursor readdata(int num, int id){
	  SQLiteDatabase db  = helper.getReadableDatabase();
	  String sql = "select * from publishment";
	  Cursor cursor = db.rawQuery(sql, null);
	  cursor.moveToPosition(id);
	  return cursor;
	  
  }
  public boolean deletedata(int id){
	SQLiteDatabase db = helper.getWritableDatabase();
	if(db.isOpen()){
		db.execSQL("delete from publishment where id=?", new Object[]{id});
	    return true;
	}
	  return false;
  }
  public void updatedata(String name, String content,int id){
	SQLiteDatabase db = helper.getWritableDatabase();
	if(db.isOpen()){
		db.execSQL("update publishment set name=?,content=? where id=?", new Object[]{id,name,content});
	}
	db.close();
  }
}
