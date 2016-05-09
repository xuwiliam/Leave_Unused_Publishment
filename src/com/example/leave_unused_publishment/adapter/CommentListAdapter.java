package com.example.leave_unused_publishment.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.leave_unused_publishment.R;
import com.example.leave_unused_publishment.widget.ChildListView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class CommentListAdapter extends BaseAdapter {
    Context context;
    List list;
    public static int positem=-1;
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
		ViewHolder holder;
		if(convertView==null){
			convertView = LayoutInflater.from(context).inflate(R.layout.item_comment, null);
			holder = new ViewHolder();
			holder.uname = (TextView)convertView.findViewById(R.id.uname);
		    holder.cont = (TextView)convertView.findViewById(R.id.commentcontent);
		    holder.l=(ChildListView)convertView.findViewById(R.id.responselist);
		    ResponseAdapter adapter = new ResponseAdapter(context, new ArrayList<String>());
		    holder.l.setAdapter(adapter);
		    holder.l.setClickable(false);
		    holder.l.setFocusable(false);
		   
		    convertView.setTag(holder);
		    
		}
		else holder = (ViewHolder)convertView.getTag();
		    
		    Map<String, String> map = (Map<String, String>) list.get(position);
		    holder.uname.setText(map.get("name").toString());
		    holder.cont.setText(map.get("content").toString());
		    if(positem==position)
		    	 holder.l.setOnItemClickListener(new OnItemClickListener() {

						@Override
						public void onItemClick(AdapterView<?> parent, View view,
								int position, long id) {
							Toast.makeText(context, "Child Listview" + String.valueOf(position), Toast.LENGTH_SHORT).show();
							
						}
					});
		// TODO Auto-generated method stub
		return convertView;
	}
    public static class ViewHolder{
      TextView 	uname;
      TextView cont;
      ListView l;
    }
}
