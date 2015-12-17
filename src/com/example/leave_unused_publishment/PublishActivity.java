package com.example.leave_unused_publishment;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SimpleAdapter.ViewBinder;

public class PublishActivity extends Activity{
  GridView gv;
  List<Map<String,Object>> l;
  private final int IMAGE_OPEN = 1;        
  private String pathImage;                       
  private Bitmap bmp;
  private SimpleAdapter simpleAdapter;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	 getWindow().setSoftInputMode(WindowManager.LayoutParams.
     		SOFT_INPUT_ADJUST_PAN);
     //锁定屏幕
     setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
     setContentView(R.layout.activity_publish);
	setContentView(R.layout.activity_publish);
    init();
  }
  public void init(){
	 gv=(GridView)findViewById(R.id.gridView1);
	 l=new ArrayList<Map<String,Object>>();
	 bmp=BitmapFactory.decodeResource(getResources(), R.drawable.addpic);
	 Map map = new HashMap();
	 map.put("itemImage", bmp);
	 l.add(map);
	 simpleAdapter = new SimpleAdapter(this, 
     		l, R.layout.item_addpic, 
             new String[] { "itemImage"}, new int[] { R.id.imageView1}); 
	 simpleAdapter.setViewBinder(new ViewBinder() {  
		    @Override  
		    public boolean setViewValue(View view, Object data,  
		            String textRepresentation) {  
		        // TODO Auto-generated method stub  
		        if(view instanceof ImageView && data instanceof Bitmap){  
		            ImageView i = (ImageView)view;  
		            i.setImageBitmap((Bitmap) data);  
		            return true;  
		        }  
		        return false;  
		    }
     });  
     gv.setAdapter(simpleAdapter);
     gv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position, long id)
			{
				if( l.size() == 10) { //第一张为默认图片
					Toast.makeText(PublishActivity.this, "图片数9张已满", Toast.LENGTH_SHORT).show();
				}
				else if(position == l.size()-1) { //点击图片位置为+ 0对应0张图片
					Toast.makeText(PublishActivity.this, "添加图片", Toast.LENGTH_SHORT).show();
					//选择图片
					Intent intent = new Intent(Intent.ACTION_PICK,       
	                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);  
	                startActivityForResult(intent, IMAGE_OPEN);  
	                //通过onResume()刷新数据
				}
				else {
					dialog(position);
					
				}
				
			}
		});  
 }
  @Override
 	protected void onResume() {
 		super.onResume();
 		if(!TextUtils.isEmpty(pathImage)){
 			Bitmap addbmp=BitmapFactory.decodeFile(pathImage);
 			Map<String, Object> map = new HashMap<String, Object>();
 	        map.put("itemImage", addbmp);
 	        l.add(0, map);
 	      
 	        simpleAdapter.notifyDataSetChanged();
 	        pathImage = null;
 		}
 	}
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
      super.onActivityResult(requestCode, resultCode, data);        
      //打开图片  
      if(resultCode==RESULT_OK && requestCode==IMAGE_OPEN) {        
          Uri uri = data.getData();  
          if (!TextUtils.isEmpty(uri.getAuthority())) {  
              //查询选择图片  
              Cursor cursor = getContentResolver().query(  
                      uri,  
                      new String[] { MediaStore.Images.Media.DATA },  
                      null,   
                      null,   
                      null);  
              //返回 没找到选择图片  
              if (null == cursor) {  
                  return;  
              }  
              //光标移动至开头 获取图片路径  
              cursor.moveToFirst();  
              pathImage = cursor.getString(cursor  
                      .getColumnIndex(MediaStore.Images.Media.DATA));  
          }
      }  //end if 打开图片
    }
  protected void dialog(final int position) {
  	AlertDialog.Builder builder = new Builder(PublishActivity.this);
  	builder.setMessage("确认移除已添加图片吗？");
  	builder.setTitle("提示");
  	builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
  		@Override
  		public void onClick(DialogInterface dialog, int which) {
  			dialog.dismiss();
  			l.remove(position);
  	        simpleAdapter.notifyDataSetChanged();
  		}
  	});
  	builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
  		@Override
  		public void onClick(DialogInterface dialog, int which) {
  			dialog.dismiss();
  			}
  		});
  	builder.create().show();
  }
  }

