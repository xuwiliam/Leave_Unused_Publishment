package com.example.leave_unused_publishment.adapter;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.leave_unused_publishment.DetailActivity;
import com.example.leave_unused_publishment.FirstPageActivity;
import com.example.leave_unused_publishment.R;
import com.example.leave_unused_publishment.network.Transfer;
import com.example.leave_unused_publishment.network.TransferListener;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
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
	List images;
	//ListView lv;
    public ListViewAdapter(Context context, List l,List I){
    	this.context = context;
    	arr = l;
        images=I;
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
		 //Bitmap bmp;
		if(convertView==null){
			convertView = LayoutInflater.from(context).inflate(R.layout.item_sale, null);
		}
		final View view = convertView;
		RelativeLayout l = (RelativeLayout)convertView.findViewById(R.id.Left);
		ImageView leftitem=(ImageView)convertView.findViewById(R.id.itemleft);
		Log.e("pos",String.valueOf(position));
		if(position==0&&(context instanceof FirstPageActivity)){
		  leftitem.setImageBitmap((Bitmap)images.get(position));
		
		}
		l.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				context.startActivity(new Intent(context,DetailActivity.class));
			}
		});
		RelativeLayout r = (RelativeLayout)convertView.findViewById(R.id.Right);
		r.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				context.startActivity(new Intent(context,DetailActivity.class));
			}
		});

		return convertView;
	}

}
