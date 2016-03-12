package com.example.leave_unused_publishment;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.example.leave_unused_publishment.adapter.ChatAdapter;
import com.example.leave_unused_publishment.adapter.MyMsgListAdapter;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
public class ChatActivity extends Activity implements OnClickListener {
  EditText input;
  Button btn;
  ListView chatlist;
  ChatAdapter adapter;
  LinearLayout morelayout;
  RelativeLayout flayout;
  boolean addfunction,issend;
  ImageView takephoto,capturephoto;
  ArrayList<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
  @Override
  public void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_chatroom);
	init();
  }
  public void init(){
	adapter=new ChatAdapter(list, ChatActivity.this);  
	input=(EditText)findViewById(R.id.editchat);
	
	btn=(Button)findViewById(R.id.submitchat);
	addfunction=true;
	chatlist=(ListView)findViewById(R.id.chatpad);
	issend=false;
	flayout=(RelativeLayout)findViewById(R.id.functionlayout);
	morelayout=(LinearLayout)findViewById(R.id.morefunction);
	takephoto=(ImageView)findViewById(R.id.takephoto);
	capturephoto=(ImageView)findViewById(R.id.capturephoto);
	takephoto.setOnClickListener(this);
	capturephoto.setOnClickListener(this);
	btn.setOnClickListener(this);
	chatlist.setAdapter(adapter);
	chatlist.setDividerHeight(0);
	input.addTextChangedListener(new TextWatcher() {
		
		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			// TODO Auto-generated method stub
		    	
		}
		
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub
			
			if(s.toString().length()==0){
				  btn.setText("");
				  btn.setBackgroundResource(R.drawable.addfile);
				  RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)btn.getLayoutParams();
				  params.height=90;
				  params.width=90;
				  btn.setLayoutParams(params);
				  addfunction=true;
				  issend=false;
				}
				else{
				 btn.setText("·¢ËÍ");
				 btn.setTextColor(getResources().getColor(R.color.general));
				 btn.setBackgroundResource(R.drawable.bg_button_green);
				 RelativeLayout.LayoutParams params=(RelativeLayout.LayoutParams)btn.getLayoutParams();
				 params.width=120;
				 params.height=90;
				 btn.setLayoutParams(params);
				 issend=true;
				 addfunction=false;
				}
		}
	});
  }
  public void onWindowFocusChanged(boolean hasFocus){
	  super.onWindowFocusChanged(hasFocus);
      Log.e("morelayoutheight",String.valueOf(morelayout.getHeight()));
      int length=morelayout.getWidth();
      Log.e("length",String.valueOf(length));
    //  int w = ((RelativeLayout)morelayout.findViewById(R.id.functionlayout)).getWidth();
    //  Log.e("w",String.valueOf(w));
      int ldistance=(length-360)/2;
      LinearLayout.LayoutParams params=(LinearLayout.LayoutParams)flayout.getLayoutParams();
      int btnw=btn.getWidth();
      int btnh=btn.getHeight();
      Log.e("btnw",String.valueOf(btnw));
      Log.e("btnh",String.valueOf(btnh));
      params.leftMargin=ldistance;
      flayout.setLayoutParams(params);
    		  
  }
   @Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int id=v.getId();
		switch(id){
		case R.id.submitchat:
			if(issend==true&&addfunction==false){
			    Map map = new HashMap<String, Object>();
			    map.put("content", input.getText().toString());
			    Log.e("input",input.getText().toString());
			    map.put("name", "cyy");
			    map.put("type", "1");
			    input.setText("");
			    list.add(map);
			    adapter.notifyDataSetChanged();
			    chatlist.setSelection(list.size()-1);
			    Log.e("count",String.valueOf(adapter.getCount()));
			}
			else if(addfunction=true&&issend==false){
			  ShowAddFunction(addfunction);
			}
			break;
		case R.id.takephoto:
		  	TakePhoto();
		  	break;
		case R.id.capturephoto:
			CapturePhoto();
			break;
		}
	}
   @Override
	public void finish() {
		// TODO Auto-generated method stub
	   if(addfunction==true){
			RelativeLayout.LayoutParams params=(RelativeLayout.LayoutParams)morelayout.getLayoutParams();
		   	 params.width=LayoutParams.MATCH_PARENT;
		   	 params.height=0;
		   	 morelayout.setLayoutParams(params);
		   	 addfunction=false;
		   	 
		}
	   
	   else 
		   super.finish();
		
	}
   
   public void ShowAddFunction(boolean add){
	 if (add==true) {
	   	 RelativeLayout.LayoutParams params=(RelativeLayout.LayoutParams)morelayout.getLayoutParams();
	   	 params.width=LayoutParams.MATCH_PARENT;
	   	 params.height=240;
	   	 morelayout.setLayoutParams(params);
	   	 ((RelativeLayout)morelayout.findViewById(R.id.functionlayout)).setVisibility(View.VISIBLE);
	 }
	 else{
		 RelativeLayout.LayoutParams params=(RelativeLayout.LayoutParams)morelayout.getLayoutParams();
	   	 params.width=LayoutParams.MATCH_PARENT;
	   	 params.height=0;
	   	 morelayout.setLayoutParams(params);
	 }
   }
   
   public void onActivityResult(int requestCode,  int resultCode,Intent data){
	   super.onActivityResult(requestCode, resultCode, data);
	   if(requestCode==1){
		   Uri uri = data.getData();
		   String[] filecolumn={MediaStore.Images.Media.DATA};
		   Cursor cur = getContentResolver().query(uri, filecolumn, null, null, null);
		   cur.moveToFirst();
		   int index = cur.getColumnIndex(filecolumn[0]);
		   String path = cur.getString(index);
		   Bitmap bmp = BitmapFactory.decodeFile(path);
	   }
	   else if(requestCode==2){
		   Bundle bundle = data.getExtras();
		   Bitmap bmp = (Bitmap)bundle.get("data");
	   }
   }
   public void TakePhoto(){
	   Intent intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
	   startActivityForResult(intent,1);
   }
   public void CapturePhoto(){
	   Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
	   startActivityForResult(intent,2);
   }
}
