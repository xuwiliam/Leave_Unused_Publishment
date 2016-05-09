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

public class ResponseAdapter extends BaseAdapter {
	Context context;
	List<String> list;
	public ResponseAdapter(Context c, List<String> l) {
		context=c;
		list=l;
		// TODO Auto-generated constructor stub
	}
	public List<String> getList(){
		return list;
	}
  @Override
public int getCount() {
	// TODO Auto-generated method stub
	return list.size();
}
@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}
@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
	  ViewHolder viewholder;
	  if(convertView==null){
		  convertView=LayoutInflater.from(context).inflate(R.layout.item_tv, null);
	      viewholder = new ViewHolder();
	      viewholder.content = (TextView)convertView.findViewById(R.id.myitem);
	      convertView.setTag(viewholder);
	  }
	  else{
		  viewholder =(ViewHolder)convertView.getTag();
	  }
	  viewholder.content.setText((String)list.get(position));
	  return convertView;
	}
    static class ViewHolder{
    	TextView content;
    }
}
