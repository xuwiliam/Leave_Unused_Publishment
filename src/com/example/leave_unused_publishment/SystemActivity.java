package com.example.leave_unused_publishment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.leave_unused_publishment.adapter.MyMsgListAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class SystemActivity extends Activity {
    private ListView mylist;
    private List<Map> arr = new ArrayList<Map>();
    private MyMsgListAdapter adapter;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.msgtablayout);
        init();
    }
    public void init(){
      mylist=(ListView)findViewById(R.id.mymsglist);
      adapter = new MyMsgListAdapter(arr, SystemActivity.this);
      mylist.setAdapter(adapter);
    }

}
