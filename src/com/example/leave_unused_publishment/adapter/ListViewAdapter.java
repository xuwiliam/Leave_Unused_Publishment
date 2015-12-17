package com.example.leave_unused_publishment.adapter;

import java.util.List;

import com.example.leave_unused_publishment.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class ListViewAdapter  extends BaseAdapter{
	Context context;
	List arr;
	//ListView lv;
    public ListViewAdapter(Context context, List l){
    	this.context = context;
    	arr = l;
    
    }
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return arr.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return arr.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(convertView==null){
			convertView = LayoutInflater.from(context).inflate(R.layout.item_sale, null);
		}
		RelativeLayout l = (RelativeLayout)convertView.findViewById(R.id.Left);
		RelativeLayout r = (RelativeLayout)convertView.findViewById(R.id.Right);
		

		return convertView;
	}

}
