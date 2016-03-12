package com.example.leave_unused_publishment;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.leave_unused_publishment.Common.Global;
import com.example.leave_unused_publishment.adapter.ListViewAdapter;
import com.example.leave_unused_publishment.widget.MenuWindow;
import com.example.leave_unused_publishment.widget.SelectPopWindow;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
public class AsortmentActivity extends Activity implements OnClickListener{
  EditText edit;
  ListView lv;
  SelectPopWindow Popup;
  RelativeLayout zone,group,sort,select;
  ListViewAdapter adapter;
  List<Map<String,Object>> onsale=new ArrayList<Map<String,Object>>();
  final Handler handler = new Handler(){
	  public void handleMessage(Message msg){
	    if(msg.what==0){
	    	adapter.notifyDataSetChanged();
	           
	    }
	    Global.MeasureListview(lv);
	  }
  };
  public void onCreate(Bundle data){
	  super.onCreate(data);
	  setContentView(R.layout.activity_asortment);
	  init();
	  
  }
  public void init(){
	edit=(EditText)findViewById(R.id.searchedit);
	adapter=new ListViewAdapter(AsortmentActivity.this,onsale,null);
	
	lv=(ListView)findViewById(R.id.saleitemlist);
	lv.setAdapter(adapter);
	zone=(RelativeLayout)findViewById(R.id.zonelayout);
	group=(RelativeLayout)findViewById(R.id.grouplayout);
	sort=(RelativeLayout)findViewById(R.id.sortlayout);
	select=(RelativeLayout)findViewById(R.id.selectlayout);
	zone.setOnClickListener(this);
	group.setOnClickListener(this);
	sort.setOnClickListener(this);
	select.setOnClickListener(this);
	handler.postDelayed(runnable, 2000);
  }
  public void onWindowFocusChanged(boolean hasFocus){
	  super.onWindowFocusChanged(hasFocus);
	 // Global.height=
  }
  @Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	   	int id=v.getId();
	   	switch(id){
	   	case R.id.zonelayout:
	   		openMenuWindow(v,Global.zone);
	   	    break;
	   	case R.id.grouplayout:
	   		openMenuWindow(v,Global.group);
	   		break;
	   	case R.id.sortlayout:
	   		openMenuWindow(v,Global.sort);
	   		break;
	   	case R.id.selectlayout:
	   		openMenuWindow(v,Global.select);
	   		break;
	   	}
	}
  public void openMenuWindow(View v,String namearr[]){
	    //  backgroundAlpha(0.7f);
		  View view = LayoutInflater.from(this).inflate(R.layout.menu_window, null);
		  ListView l = (ListView)view.findViewById(R.id.mylist);
		  List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		  Map<String,Object>map = null;
		  int i = 0;
		  for(; i<namearr.length; i++){
			  
		    map = new HashMap<String,Object>();
		    map.put("itemname", namearr[i]);
		    list.add(map);
		  }
		 
		  SimpleAdapter adapter = new SimpleAdapter(this, list,
	              R.layout.item_tv, new String[] { "itemname" },
	              new int[] {R.id.myitem });
		  l.setAdapter(adapter);
	       
		  WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
	      final MenuWindow menuWindow = new MenuWindow(view,v);
	      menuWindow.setLayout(windowManager.getDefaultDisplay().getWidth(), ActionBar.LayoutParams.MATCH_PARENT);
	      menuWindow.show();
	      //backgroundAlpha(0.7f);
	      //mask.setVisibility(View.VISIBLE);
	  }
	 final Runnable runnable=new Runnable(){
		 public void run() {
		   String  arr[] = new String[200];
		   int i = 0;
		   Map map;
		   for(; i<200; i++){
			   arr[i]="aaa";
		       map = new HashMap();
		       map.put("itemname", arr[i]);
		       onsale.add(map);
		   }
		   handler.sendEmptyMessage(0);
		 };
	 };
}
