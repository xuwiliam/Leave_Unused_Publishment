package com.example.leave_unused_publishment.DB;

import android.content.Context;
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
  public void readdata(int num){
	  SQLiteDatabase db  = helper.getReadableDatabase();
	  String sql = "select * from publishment";
  }
  public boolean deletedata(String name, String content){
	  
	  return false;
  }
  public void updatedata(String name, String content){
	  
  }
}
