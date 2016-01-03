package com.example.leave_unused_publishment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.leave_unused_publishment.adapter.MyMsgListAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class PrivateMsgActivity extends Activity{
  ListView mylist;
  MyMsgListAdapter adapter;
  List<Map> arr = new ArrayList<Map>();
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
    setContentView(R.layout.msgtablayout);
    init();
  }
  public void init(){
	adapter = new MyMsgListAdapter(arr, PrivateMsgActivity.this);
	mylist=(ListView)findViewById(R.id.mymsglist);
	mylist.setAdapter(adapter);
  }
}
