package com.example.leave_unused_publishment;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.leave_unused_publishment.adapter.BuyListAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ListView;
import android.widget.TextView;
public class MyWantedActivity extends Activity{
ListView wantedlist;
BuyListAdapter adapter;
TextView wantedtext;
List<Map>wantedarr = new ArrayList<Map>();
final Handler handler = new Handler(){
@Override
public void handleMessage(Message msg) {
	// TODO Auto-generated method stub
	if(msg.what==0){
		adapter.notifyDataSetChanged();
	}
  }
};
  @Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.layout_exchangeandwant);
	init();
  }
  public void init(){
	wantedlist=(ListView)findViewById(R.id.myinfoitemlist);
	wantedtext=(TextView)findViewById(R.id.ewtitle);
	wantedtext.setText("ÎÒµÄÇó¹º");
	adapter = new BuyListAdapter(MyWantedActivity.this, wantedarr);
	wantedlist.setAdapter(adapter);
	handler.postDelayed(runnable, 2000);
	
  }
  final Runnable runnable = new Runnable(){
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Map map;
		int i;
		for(i=0; i<20; i++){
		  map = new HashMap();
		  map.put("url", "http//:sfsdfsdf");
		  map.put("title", "vvv");
		  map.put("price", "1");
		  map.put("location","zzzz");
		  wantedarr.add(map);
		}
		handler.sendEmptyMessage(0);
	}  
  };
}
