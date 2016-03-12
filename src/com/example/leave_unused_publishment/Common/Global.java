package com.example.leave_unused_publishment.Common;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.example.leave_unused_publishment.FirstPageActivity;
import com.example.leave_unused_publishment.PublishActivity;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

public class Global {
  public static int height=0;
  public static int width=0;
  public static PublishActivity pubactivity=null;
  public static FirstPageActivity firstpageactivity=null;
 
  public static String zone[]={"全部","中山大学","广东外语外贸大学","华南师范大学","华南理工大学","星海音乐学院","广州美术学院","广州工业大学","广州大学","广州中医药大学","广东药学院"};
  public static String group[]={"书籍","家用电器","文体用品","自行车","手机","数码","生活服务","其它"};
  public static String sort[]={"默认排序","最新发布","价格最低","价格最高"};
  public static String select[]={"0-50","50-100","100-200","200以上"};
  public static Handler handler;
  public static int good = 0;
  public static String token;
  public static String username;
  public static String password;
  public static void MeasureListview(ListView listView) {    
      ListAdapter listAdapter = listView.getAdapter();    
      
      if (listAdapter == null) {    
          return;    
      }    
      int totalHeight = 0;    
      for (int i = 0, len = listAdapter.getCount(); i < len; i++) { // listAdapter.getCount()返回数据项的数目    
          View listItem = listAdapter.getView(i, null, listView);    
          listItem.measure(0, 0); // 计算子项View 的宽高    
          totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度    
      }    
      ViewGroup.LayoutParams params = listView.getLayoutParams();    
      params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));    
      listView.setLayoutParams(params);    
  } 
  public static String getMD5(String raw) {
      MessageDigest md5 = null;
      try {
          md5 = MessageDigest.getInstance("MD5");
      } catch (Exception e) {
          Log.e("cryerror","Something terrible happen, MessageDigest failed to find md5 method");
          return "";
      }

      byte[] byteArray = raw.getBytes();
      byte[] md5Bytes = md5.digest(byteArray);

      StringBuilder hexValue = new StringBuilder();
      for (byte b : md5Bytes){
          int val = ((int) b) & 0xff;
          if (val < 16)
              hexValue.append("0");
          hexValue.append(Integer.toHexString(val));
      }

      return hexValue.toString();
  }
  public static File saveBitmapToFile(Bitmap bitmap){
	String path = Environment.getExternalStorageDirectory()+"/pub/";
	Date date = new Date(System.currentTimeMillis());
	SimpleDateFormat df = new SimpleDateFormat("yyyymmddHHMMSS");
	String name=df.format(date);
	path = path+name+".jpg";
	File file = new File(path);
	if(!file.exists())file.mkdirs();
	try {
		file.createNewFile();
		BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(file));
		bitmap.compress(CompressFormat.JPEG, 100, os);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return file;
  }
  public static byte[] Bitmap2Bytes(Bitmap bm) {
     ByteArrayOutputStream baos = new ByteArrayOutputStream();
	 bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
	 return baos.toByteArray();
}
  public static byte[] File2byte(String filePath)  
  {  
      byte[] buffer = null;  
      try  
      {  
    	  Log.e("filepath",filePath);
          File file = new File(filePath);  
          FileInputStream fis = new FileInputStream(file);  
          ByteArrayOutputStream bos = new ByteArrayOutputStream();  
          byte[] b = new byte[1024];  
          int n;  
          while ((n = fis.read(b)) != -1)  
          {  
              bos.write(b, 0, n);  
          }  
          fis.close();  
          bos.close();  
          buffer = bos.toByteArray();
          return buffer;
      }  
      catch (FileNotFoundException e)  
      {  
          e.printStackTrace();  
      }  
      catch (IOException e)  
      {  
          e.printStackTrace();  
      }  
      return null;  
  }  
}
