package com.example.leave_unused_publishment.network;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.example.leave_unused_publishment.Common.Global;

import android.graphics.Bitmap;

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
  public static void uploadImage(Bitmap bitmap,TransferListener listener){
	  String url="upload/";
	  Map map = new HashMap();
	  map.put("token", "");
	  byte image[] = Global.Bitmap2Bytes(bitmap);
	  Date date = new Date(System.currentTimeMillis());
	  SimpleDateFormat df = new SimpleDateFormat("yyyymmddHHMMSS");
	  
	  Communicator.sendImage(url, map,image,df.format(date), listener);
  }
}
