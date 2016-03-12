package com.example.leave_unused_publishment.network;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.example.leave_unused_publishment.Common.Global;

import android.graphics.Bitmap;
import android.util.Log;

public class Transfer {
  public static void login(String username,String password,final TransferListener listener){
	  Map map = new HashMap();
	  map.put("username", username);
	  map.put("password", password);
	  JSONObject obj = new JSONObject(map);
	  Communicator.postForm("auth/", map, listener);	  
  }
  public static void register(String username,String nickname, String password,int school,String profile,final TransferListener listener){
	Map map = new HashMap();
	map.put("nickname", "chicken");
	map.put("password", password);
	map.put("school", 106);
	  map.put("profile", "");
	  String path = "users/"+username;
	  JSONObject obj = new JSONObject(map);
	  Communicator.postForm(path, map, listener);
  }
  public static void uploadImage(byte[] bitmap,TransferListener listener){
	String url="pics/upload?token=";
	url+=Global.token;
	Map map = new HashMap();
	//map.put("token", Global.token);
	//byte image[] = Global.Bitmap2Bytes(bitmap);
	Date date = new Date(System.currentTimeMillis());
	SimpleDateFormat df = new SimpleDateFormat("yyyymmddHHMMSS");
	Log.e("imagelength",String.valueOf(bitmap.length));
	Log.e("uploadtoken",Global.token);
	Communicator.sendImage(url, map, bitmap, df.format(date), listener);
  }
  public static void getImage(String picid,TransferListener listener, Bitmap bmp,List images){
	  String url="pics/";
	  String code="e98fdb350bc12fe6ba1af0bd43cfbc58";
	  url+=picid;
	  Communicator.downLoadImage(url, listener,bmp,images);
	      
  }
}
