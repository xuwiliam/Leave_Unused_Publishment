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

public class CommentListAdapter extends BaseAdapter {
    Context context;
    List list;
    public CommentListAdapter(Context c,List l){
    	context=c;
    	list=l;
    }
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = new ViewHolder();
		if(convertView==null)
			convertView = LayoutInflater.from(context).inflate(R.layout.item_comment, null);
		    holder.uname = (TextView)convertView.findViewById(R.id.uname);
		    holder.cont = (TextView)convertView.findViewById(R.id.commentcontent);
		    Map<String, String> map = (Map<String, String>) list.get(position);
		    holder.uname.setText(map.get("name").toString());
		    holder.cont.setText(map.get("content").toString());
		// TODO Auto-generated method stub
		return convertView;
	}
    public static class ViewHolder{
      TextView 	uname;
      TextView cont;
    }
}
