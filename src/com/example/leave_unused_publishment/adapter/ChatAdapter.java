package com.example.leave_unused_publishment.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.leave_unused_publishment.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ChatAdapter extends BaseAdapter {
	Context c;
	ArrayList<Map> l;
  public ChatAdapter(ArrayList list,Context context){
	  l = list;
	  c = context;
  }
@Override
   public int getCount() {
	
	return l.size();
}
@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return l.get(position);
	}
@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
	    Map map = (Map)l.get(position);
	    ViewHolder holder = null;
		if(convertView==null){
			if(getItemViewType(position)==1){
				Log.e("position",String.valueOf(position));
				convertView=LayoutInflater.from(c).inflate(R.layout.chat_left, null);
				TextView chatcontent = (TextView)convertView.findViewById(R.id.chatcontent);
				chatcontent.setText(map.get("content").toString());
				Log.e("content",map.get("content").toString());
				TextView name = (TextView)convertView.findViewById(R.id.chatuname);
				name.setText(map.get("name").toString());
			}
			else{
				convertView=LayoutInflater.from(c).inflate(R.layout.chat_right, null);
				TextView chatcontent = (TextView)convertView.findViewById(R.id.chatcontent);
				chatcontent.setText(map.get("content").toString());
				TextView name=(TextView)convertView.findViewById(R.id.chatuname);
				name.setText(map.get("name").toString());
			}
			  
		}
		else{
			TextView chatcontent = (TextView)convertView.findViewById(R.id.chatcontent);
			chatcontent.setText(map.get("content").toString());
			TextView name=(TextView)convertView.findViewById(R.id.chatuname);
			name.setText(map.get("name").toString());
		}
			
		return convertView;
	}
@Override
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return 2;
	}
@Override
	public int getItemViewType(int position) {
		// TODO Auto-generated method stub
	   Map map = (Map)l.get(position);
	   if(map.get("type").toString().equals("1")){
		   return 1;
	   }
	   else 
		   return 2;
	}
static class ViewHolder{
    public  TextView tname;
    public  TextView chatcontent;
    public  TextView date;
}
}
