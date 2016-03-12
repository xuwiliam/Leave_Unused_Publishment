package com.example.leave_unused_publishment.adapter;

import java.util.List;
import java.util.Map;

import com.example.leave_unused_publishment.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyMsgListAdapter extends BaseAdapter {
  private List<Map> l;
  private Context c;
  public MyMsgListAdapter(List list,Context context){
	  l = list;
	  c = context;
  }
  public int getCount(){
	  return l.size();
  }
  public long getItemId(int id){
	  return id;
  }
  @Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return l.get(position);
	}
  
	
  public View getView(int position,View convertview,ViewGroup parent ){
	  //Map map = l.get(position);
	  
	  if(convertview==null){
		  convertview = LayoutInflater.from(c).inflate(R.layout.item_msg, null);
	      
	  }
	      TextView uname=(TextView)convertview.findViewById(R.id.msguname);
	      TextView time=(TextView)convertview.findViewById(R.id.mymsgtime);
	  return convertview;
  }
}
