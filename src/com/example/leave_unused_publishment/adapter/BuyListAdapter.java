package com.example.leave_unused_publishment.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;

import com.example.leave_unused_publishment.R;

public class BuyListAdapter extends BaseAdapter{
	Context context;
	List arr;
	//ListView lv;
    public BuyListAdapter(Context context, List l){
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
			convertView = LayoutInflater.from(context).inflate(R.layout.item_want, null);
		}
		//RelativeLayout l = (RelativeLayout)convertView.findViewById(R.id.Left);
		//RelativeLayout r = (RelativeLayout)convertView.findViewById(R.id.Right);
		

		return convertView;
	}
}
