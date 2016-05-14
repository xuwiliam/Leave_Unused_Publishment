package com.example.leave_unused_publishment.DB;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteDatabase;
public class DBhelper extends SQLiteOpenHelper {
  public DBhelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}
@Override
public void onCreate(SQLiteDatabase db) {
	// TODO Auto-generated method stub
	db.execSQL("create table if not exists publishment ("+
	           "id integer primary key autoincrement ,"
	           +"name text ,"
			   +"content text ,"
	           +"comment text )"
			   );
	
}
  @Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
}
