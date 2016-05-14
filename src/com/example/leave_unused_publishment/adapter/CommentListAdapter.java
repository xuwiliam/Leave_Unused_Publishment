package com.example.leave_unused_publishment.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.leave_unused_publishment.R;
import com.example.leave_unused_publishment.Common.Global;
import com.example.leave_unused_publishment.DB.DBUtil;
import com.example.leave_unused_publishment.widget.ChildListView;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class CommentListAdapter extends BaseAdapter {
    Context context;
    List list;
    int row;
    //public static int positem=-1;
    Map map; 
    Handler handler;
    public CommentListAdapter(Context c,Map l,Handler h){
    	context=c;
    	//list=l;
    	map=l;
    	row = getCount();
    	handler = h;
    	//du=new DBUtil(context);
    	//map = du.readdata(50, du.getCount());
    }
    public int getRow(){
    	return row;
    }
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return map.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return map.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
    public void setRow(int t){
    	row = t;
    }
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if(convertView==null){
			convertView = LayoutInflater.from(context).inflate(R.layout.item_comment, null);
			holder = new ViewHolder();
			holder.uname = (TextView)convertView.findViewById(R.id.uname);
		    holder.cont = (TextView)convertView.findViewById(R.id.commentcontent);
		    holder.l=(LinearLayout)convertView.findViewById(R.id.responselist);
		    ResponseAdapter adapter = new ResponseAdapter(context, new ArrayList<String>());
		    //holder.l.setAdapter(adapter);
		    //holder.l.setClickable(false);
		    //holder.l.setFocusable(false);
		   
		    convertView.setTag(holder);
		    
		}
		else{ 
			holder = (ViewHolder)convertView.getTag();
			holder.l.removeAllViews();
			//holder.l.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT));
			
		}
		  //  Map<String, String> map = (Map<String, String>) list.get(position);
		  //  holder.uname.setText(map.get("name").toString());
		  //  holder.cont.setText(map.get("content").toString());
		Object o = map.get(Global.du.getCount()-position);
		holder.id=Global.du.getCount()-position;
		//Log.e("posi",String.valueOf(Global.du.getCount())+" "+String.valueOf(position));
		//Log.e("adaptercont",o.toString());
		holder.l.setTag(holder.id);
		if(o!=null){
		  String value = o.toString();
		 
		  String col[] = value.split(",");
		  Log.e("collenth",String.valueOf(col.length));
		  if(col!=null&&col.length>=2){
		    holder.uname.setText(col[0]);
		    holder.cont.setText(col[1]);
		    if(col.length==3){
		      /*Bundle data = new Bundle();
		      data.putString("commenttext", col[2]);
		      Message msg = handler.obtainMessage();
		      msg.what=1;
		      msg.setData(data);
		      handler.sendMessage(msg);*/
		      String name_cmm[]=col[2].split("@");
		      
		      if(name_cmm!=null){
		      
			    int i = 1;
			    for(; i<name_cmm.length; i++){
			      Log.e("addcomment","add");
				  TextView tv = new TextView(context);
				  SpannableString ss = new SpannableString(Global.username+":"+name_cmm[i]);
				  int len = 11;
				  int len2 = name_cmm[i].length();
				  ss.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.surecolor)), 0, len+1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
				  ss.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.text_content)),len+1,len+len2+1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
				  tv.setText(ss);
				  //holder.l.addView(tv);
			      holder.l.addView(tv);	   
			  }
		    }
		  }
		  }
		}
		/*    	 holder.l.setOnItemClickListener(new OnItemClickListener() {

						@Override
						public void onItemClick(AdapterView<?> parent, View view,
								int position, long id) {
							Toast.makeText(context, "Child Listview" + String.valueOf(position), Toast.LENGTH_SHORT).show();
							
						}
					});*/
		// TODO Auto-generated method stub
		return convertView;
	}
    public static class ViewHolder{
      TextView 	uname;
      TextView cont;
      LinearLayout l;
      int id = 0;
    }
}
