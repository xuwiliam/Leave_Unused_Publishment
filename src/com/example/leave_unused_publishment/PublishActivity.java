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
     //������Ļ
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
				if( l.size() == 10) { //��һ��ΪĬ��ͼƬ
					Toast.makeText(PublishActivity.this, "ͼƬ��9������", Toast.LENGTH_SHORT).show();
				}
				else if(position == l.size()-1) { //���ͼƬλ��Ϊ+ 0��Ӧ0��ͼƬ
					Toast.makeText(PublishActivity.this, "���ͼƬ", Toast.LENGTH_SHORT).show();
					//ѡ��ͼƬ
					Intent intent = new Intent(Intent.ACTION_PICK,       
	                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);  
	                startActivityForResult(intent, IMAGE_OPEN);  
	                //ͨ��onResume()ˢ������
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
      //��ͼƬ  
      if(resultCode==RESULT_OK && requestCode==IMAGE_OPEN) {        
          Uri uri = data.getData();  
          if (!TextUtils.isEmpty(uri.getAuthority())) {  
              //��ѯѡ��ͼƬ  
              Cursor cursor = getContentResolver().query(  
                      uri,  
                      new String[] { MediaStore.Images.Media.DATA },  
                      null,   
                      null,   
                      null);  
              //���� û�ҵ�ѡ��ͼƬ  
              if (null == cursor) {  
                  return;  
              }  
              //����ƶ�����ͷ ��ȡͼƬ·��  
              cursor.moveToFirst();  
              pathImage = cursor.getString(cursor  
                      .getColumnIndex(MediaStore.Images.Media.DATA));  
          }
      }  //end if ��ͼƬ
    }
  protected void dialog(final int position) {
  	AlertDialog.Builder builder = new Builder(PublishActivity.this);
  	builder.setMessage("ȷ���Ƴ������ͼƬ��");
  	builder.setTitle("��ʾ");
  	builder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
  		@Override
  		public void onClick(DialogInterface dialog, int which) {
  			dialog.dismiss();
  			l.remove(position);
  	        simpleAdapter.notifyDataSetChanged();
  		}
  	});
  	builder.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
  		@Override
  		public void onClick(DialogInterface dialog, int which) {
  			dialog.dismiss();
  			}
  		});
  	builder.create().show();
  }
  }

