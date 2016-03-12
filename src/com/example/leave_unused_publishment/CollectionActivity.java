package com.example.leave_unused_publishment;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.leave_unused_publishment.adapter.ListViewAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
public class CollectionActivity extends Activity implements OnClickListener{
  ListView itemlist;
  ImageView back; 
  ListViewAdapter adapter;
  TextView ewtext;
  List<Map<String,Object>>listsale=new ArrayList<Map<String,Object>>();
  @Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.layout_exchangeandwant);
	init();
  }
  final Handler handler = new Handler(){
	  public void handleMessage(Message msg) {
		if(msg.what==0){
			adapter.notifyDataSetChanged();
		}
	  }
  };
  public void init(){
	itemlist=(ListView)findViewById(R.id.myinfoitemlist);
	back=(ImageView)findViewById(R.id.exback);
	adapter=new ListViewAdapter(CollectionActivity.this, listsale,null);
	itemlist.setAdapter(adapter);
	ewtext=(TextView)findViewById(R.id.ewtitle);
	ewtext.setText("ÎÒµÄ×ªÈÃ");
	handler.postDelayed(runnable, 2000);
  }
   @Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	  int id = v.getId();
	  switch(id){
	  case R.id.exback:
		  finish();
		  break;
		  
	  }
	}
   final Runnable runnable=new Runnable(){
	   public void run() {
		 Map map;
		 int i=0;
		 for(; i<20; i++){
			 map = new HashMap();
			 map.put("itemname", "name");
			 listsale.add(map);
		 }
		 handler.sendEmptyMessage(0);	 
		   
	   };
   };
}
