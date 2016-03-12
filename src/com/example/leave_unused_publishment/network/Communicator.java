package com.example.leave_unused_publishment.network;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.leave_unused_publishment.Common.Global;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;
import android.os.Handler;
import android.util.Log;

public class Communicator {
	public static final String BACKEND_IP = "http://www.robertshome.com.cn/infopublisher/api/";

	public static void sendGet(final String method, final String content,
			final TransferListener listener) {
		new Thread() {

			@Override
			public void run() {
				String result = "";

				URL url = null;
				HttpURLConnection connection = null;
				InputStream in = null;
				try {
					url = new URL(BACKEND_IP + method + content);
					connection = (HttpURLConnection) url.openConnection();
					connection.setRequestMethod("GET");
					connection.setDoInput(true);
					in = connection.getInputStream();
					InputStreamReader r = new InputStreamReader(in);
					BufferedReader br = new BufferedReader(r);
					String line = "";
					while ((line = br.readLine()) != null) {
						result += line;
					}

					r.close();
					br.close();
					connection.disconnect();

					Log.e("publish", "send: " + content + "result: "
							+ result);
					final JSONObject obj = new JSONObject(result);
					String status = obj.getString("status");
					if (status.equals("success")) {
						handler.post(new Runnable() {
							@Override
							public void run() {
								Log.e("success","status");
								listener.onSucceed(obj);
							}
						});
					} else {
						handler.post(new Runnable() {

							@Override
							public void run() {
								try {
									String desc = obj.getString("description");
									listener.onFail(desc);
								} catch (JSONException e) {
									e.printStackTrace();
								}
							}
						});
					}
				} catch (Exception e) {
					e.printStackTrace();
					handler.post(new Runnable() {

						@Override
						public void run() {
							listener.onFail("网络错误，请检查");
						}
					});
				}
			}
		}.start();
	}
    public static void downLoadImage(final String method,TransferListener listener,Bitmap bitmap,List images){
    	try{
    	  URL url = new URL(BACKEND_IP+method);
    	  HttpURLConnection connection = (HttpURLConnection)url.openConnection(); 
    	  connection.setRequestMethod("GET");
    	  connection.setDoInput(true);
    	  InputStream in = connection.getInputStream();
          bitmap = BitmapFactory.decodeStream(in);
          images.add(bitmap);
          //Map map = new HashMap();
    	  //map.put("image", bmp);
    	  if(bitmap!=null){
    		Log.e("status","successbitmap");
    		Map map = new HashMap();
    		map.put("status", "success");
    	    JSONObject object = new JSONObject(map);
    	    listener.onSucceed(object);
     	   }
    	  else{
    	    listener.onFail("get Image failed");
        	}
    	  }catch(Exception e){
    		 e.printStackTrace();
    	 }
      }
	public static void sendPost(final String method, final String content,
			final TransferListener listener) {
		new Thread() {

			@Override
			public void run() {
				URL url = null;
				HttpURLConnection connection = null;
				InputStreamReader in = null;
				try {
					url = new URL(BACKEND_IP + method);
					connection = (HttpURLConnection) url.openConnection();
					connection.setDoInput(true);
					connection.setDoOutput(true);
					connection.setRequestMethod("POST");
					connection.setRequestProperty("Content-Type",
							"application/x-www-form-urlencoded");
					connection.setRequestProperty("Charset", "utf-8");
					Log.e("communicator","1");
				
					OutputStream os = connection.getOutputStream();
					DataOutputStream dop = new DataOutputStream(os);
                    Log.e("communicator","2");
					byte[] buffer = content.getBytes();
					dop.write(buffer);
					dop.flush();
					dop.close();
                    Log.e("com","5");
					in = new InputStreamReader(connection.getInputStream());
					BufferedReader bufferedReader = new BufferedReader(in);
					Log.e("com","3");
					StringBuilder sb = new StringBuilder();
					String line = null;
					
					while ((line = bufferedReader.readLine()) != null) {
						sb.append(line);
					}
					Log.e("com","4");
					final String result = sb.toString();

					Log.e("publish", "send: " + content + " result: "
							+ result);
					if(result.equals("201")){
						handler.post(new Runnable(){
							public void run(){
								try {
									listener.onSucceed(new JSONObject(result));
								} catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						});
					}
					/*final JSONObject obj = new JSONObject(result);
					String status = obj.getString("status");
					if (status.equals("success")) {
						handler.post(new Runnable() {

							@Override
							public void run() {
								listener.onSucceed(obj);
							}
						});
					} else {
						handler.post(new Runnable() {

							@Override
							public void run() {
								try {
									String desc = obj.getString("description");
									listener.onFail(desc);
								} catch (JSONException e) {
									e.printStackTrace();
								}
							}
						});
					}*/
				} catch (Exception e) {
					e.printStackTrace();
					Log.e("error", e.toString());
					handler.post(new Runnable() {

						@Override
						public void run() {
							listener.onFail("网络错误，请检查");
						}
					});
				} finally {
					if (connection != null) {
						connection.disconnect();
					}
					if (in != null) {
						try {
							in.close();
						} catch (IOException e) {
							e.printStackTrace();
							Log.e("error1", e.toString());
						}
					}
				}
			}
		}.start();
	}

	public static void sendImage(final String method,
			final Map<String, Object> params, final byte[] image,
			final String name, final TransferListener listener) {
		new Thread() {

			@Override
			public void run() {
				String result = "";

				String end = "\r\n";
				String uploadUrl = BACKEND_IP + method;
				String MULTIPART_FORM_DATA = "multipart/form-data";
				String BOUNDARY = "---------7d4a6d158c9";

				try {
					URL url = new URL(uploadUrl);
					HttpURLConnection conn = (HttpURLConnection) url
							.openConnection();
					conn.setDoInput(true);
					conn.setDoOutput(true);
					conn.setRequestMethod("POST");
					conn.setRequestProperty("Content-Type", MULTIPART_FORM_DATA
							+ "; boundary=" + BOUNDARY);

					/*StringBuilder sb = new StringBuilder();
					for (Map.Entry<String, Object> entry : params.entrySet()) {
						sb.append("--");
						sb.append(BOUNDARY);
						sb.append("\r\n");
						sb.append("Content-Disposition: form-data; name=\""
								+ entry.getKey() + "\"\r\n\r\n");
						sb.append(entry.getValue());
						sb.append("\r\n");
					}

					sb.append("--");
					sb.append(BOUNDARY);
					sb.append("\r\n");*/

					DataOutputStream dos = new DataOutputStream(
							conn.getOutputStream());
					//dos.write(sb.toString().getBytes());
                    dos.writeBytes("--"+BOUNDARY+"\r\n");
					dos.writeBytes("Content-Disposition: form-data; "
							+ "name=\"file\"; filename=\"" + name + ".jpg"
							+ "\"\r\n");
					dos.writeBytes("Content-Type: image/jpg\r\n");
					dos.writeBytes("\r\n");
					//dos.writeBytes("Media Type: image/jpg ("+String.valueOf(image.length)+" bytes)");
					dos.write(image, 0, image.length);
					
					Log.e("writepic","here");
					dos.writeBytes(end);

					dos.writeBytes("--" + BOUNDARY + "--\r\n");
					dos.flush();
                    Log.e("flush","here");
					InputStream is = conn.getInputStream();
					Log.e("inputstream","here");
					InputStreamReader isr = new InputStreamReader(is, "utf-8");
					BufferedReader br = new BufferedReader(isr);
					String tmpstr=null;
					while((tmpstr=br.readLine())!=null){
						result+=tmpstr;
					}
                    Log.e("read","here");
					dos.close();
					br.close();
					conn.disconnect();
                   
					Log.e("publish", "result: " + result);
					final JSONObject obj = new JSONObject(result);
					String status = obj.getString("status_code");
					if (status.equals("201")) {
						handler.post(new Runnable() {

							@Override
							public void run() {
								listener.onSucceed(obj);
							}
						});
					} else {
						handler.post(new Runnable() {

							@Override
							public void run() {
								try {
									String desc = obj.getString("description");
									listener.onFail(desc);
								} catch (JSONException e) {
									e.printStackTrace();
								}
							}
						});
					}
				} catch (Exception e) {
					e.printStackTrace();
					Log.e("imageerror",e.toString());
					handler.post(new Runnable() {

						@Override
						public void run() {
							listener.onFail("网络错误，请检查");
						}
					});
				}
			}
		}.start();
	}

	private static Handler handler = new Handler();
    public static void postForm(final String url,final Map map,final TransferListener listener){
       
      new Thread(new Runnable(){
    	 public void run(){
    		 HttpClient httpClient= new DefaultHttpClient();
    		 try{  
    	            // 创建HttpPost对象。  
    			    
    	            HttpPost post = new HttpPost(BACKEND_IP+url);  
    	            // 如果传递参数个数比较多的话可以对传递的参数进行封装  
    	            List<NameValuePair> params = new ArrayList<NameValuePair>();  
    	            for(Object key : map.keySet())  
    	            {  
    	                //封装请求参数  
    	                params.add(new BasicNameValuePair(key.toString() , map.get(key).toString()));  
    	            }  
    	            // 设置请求参数  
    	            post.setEntity(new UrlEncodedFormEntity(  
    	                params,HTTP.UTF_8));  
    	            // 发送POST请求  
    	            HttpResponse httpResponse = httpClient.execute(post);  
    	            // 如果服务器成功地返回响应  
    	            String result = EntityUtils  
    	                    .toString(httpResponse.getEntity());  
    	            Log.e("rel",result);
    	            if (httpResponse.getStatusLine()  
    	                .getStatusCode() == 201||httpResponse.getStatusLine().getStatusCode()==200)  
    	            {  
    	                // 获取服务器响应字符串  
    	               
    	               listener.onSucceed(new JSONObject(result)); 
    	            } 
    	            else{
    	            	JSONObject obj = new JSONObject(result);
    	            	String msg = obj.getString("message");
    	            	listener.onFail(msg);
    	            }
    	        }catch(Exception e){  
    	            e.printStackTrace();  
    	            Log.e("errorform",e.toString());
    	        }finally{  
    	            //httpClient.getConnectionManager().shutdown();  
    	        }  
    	        
    	 }
      }).start();
        
      
    }
    public static void postFormWithImage(final String url,final Bitmap bitmap,final TransferListener listener){
        
        new Thread(new Runnable(){
      	 public void run(){
      		 HttpClient httpClient= new DefaultHttpClient();
      		 try{  
      	            // 创建HttpPost对象。  
      			    
      	            HttpPost post = new HttpPost(BACKEND_IP+url);  
      	            // 如果传递参数个数比较多的话可以对传递的参数进行封装  
      	        //  MultipartEntityBuilder entity = MultipartEntityBuilder.create();
      	         // entity.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
      	          List<NameValuePair> params = new ArrayList<NameValuePair>();  
      	         // entity.addTextBody("token", "", ContentType.TEXT_PLAIN.withCharset("UTF-8")); 
      	        File file = Global.saveBitmapToFile(bitmap);
  	            Log.e("filename",file.getCanonicalPath());
  	           
  	            byte filedata[] = null;
  	            Log.e("file2byte", "here");
  	            ByteArrayOutputStream baos = new ByteArrayOutputStream();
  	            bitmap.compress(CompressFormat.JPEG, 100, baos);
  	            filedata = baos.toByteArray();
  	            if(filedata==null)Log.e("nullbyte","null");
  	            NameValuePair myfile = new BasicNameValuePair("file", new String(filedata));
  	            NameValuePair token = new BasicNameValuePair("token", Global.token);
  	            params.add(myfile);
  	            params.add(token);
  	            Log.e("add","here");
  	            post.setEntity(new UrlEncodedFormEntity(  
    	                params,HTTP.UTF_8));
  	          /*  NameValuePair file = new Basic
      	            // 设置请求参数  
      	            post.setEntity(new UrlEncodedFormEntity(  
      	                params,HTTP.UTF_8));*/
      	           
      	         //   entity.addPart("file", new FileBody(file));
      	           // entity.addPart("token", new StringBody(""));
      	           // post.setEntity(entity.build());
      	            // 发送POST请求  
      	            HttpResponse httpResponse = httpClient.execute(post);  
      	            // 如果服务器成功地返回响应  
      	            Log.e("execute","here");
      	            String result = EntityUtils  
      	                    .toString(httpResponse.getEntity());  
      	            Log.e("rel",result);
      	            if (httpResponse.getStatusLine()  
      	                .getStatusCode() == 201||httpResponse.getStatusLine().getStatusCode()==200)  
      	            {  
      	                // 获取服务器响应字符串  
      	               
      	               listener.onSucceed(new JSONObject(result)); 
      	            } 
      	            else{
      	            	JSONObject obj = new JSONObject(result);
      	            	String msg = obj.getString("message");
      	            	listener.onFail(msg);
      	            }
      	        }catch(Exception e){  
      	            e.printStackTrace();  
      	            Log.e("errorform",e.toString());
      	        }finally{  
      	            //httpClient.getConnectionManager().shutdown();  
      	        }  
      	        
      	 }
        }).start();
          
        
      }
}
