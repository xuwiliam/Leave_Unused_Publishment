package com.example.leave_unused_publishment.adapter;

import java.util.List;

import com.example.leave_unused_publishment.R;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class ExpandAdapter extends BaseAdapter{
  Context context;
  List<String> firstcolumn,secondcolumn;
  String selecttext;
  int selectpos;
  onSelectedListener selectedlistener;
  @Override
  public int getCount() {
	// TODO Auto-generated method stub
	if(firstcolumn!=null)return firstcolumn.size();
	else if(secondcolumn!=null)return secondcolumn.size();
	return 0;
  }
  @Override
	public Object getItem(int position) {
		if(firstcolumn!=null)return firstcolumn.get(position);
		else if(secondcolumn!=null)return secondcolumn.get(position);
		return null;
	}
  public ExpandAdapter(Context c, List<String> l,int which) {
	if(which==1)firstcolumn=l;
	else secondcolumn=l;
	context=c;
	
	// TODO Auto-generated constructor stub
  }
  @Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
	  if(convertView==null)
		  convertView=(TextView)LayoutInflater.from(context).inflate(R.layout.item_select_kind, null);
	  convertView.setTag(position);
	  String content="";
	  if(firstcolumn!=null){
		   content = firstcolumn.get(position);
		  ((TextView)convertView).setText(content);
	  }
	  else if(secondcolumn!=null){
		  content = secondcolumn.get(position);
		  ((TextView)convertView).setText(content);
	  }
	  convertView.setOnClickListener(new OnClickListener(){
		 @Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
		  	selectpos=(Integer)v.getTag();
		  	setSelectedPosition(selectpos);
		  	if(selectedlistener!=null)
		  		selectedlistener.itemselected(v, selectpos);
		  	
		}
	        
	  });
	  if(selecttext!=null&&selecttext.equals(content)){
		Log.e("textcontent",content);
		convertView.setBackgroundColor(context.getResources().getColor(R.color.choose_item));
	  }
	  else
	    convertView.setBackgroundColor(context.getResources().getColor(R.color.general));
	  return convertView;
	}
  @Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
  
  public interface onSelectedListener{
	  public void itemselected(View view,int pos);
  }
  public void setOnSelectedListener(onSelectedListener l){
	selectedlistener=l;  
  }
  public void setSelectedPosition(int pos) {
		if (firstcolumn != null && pos < firstcolumn.size()) {
			selectpos = pos;
			selecttext = firstcolumn.get(pos);
			notifyDataSetChanged();
		} else if (secondcolumn != null && pos < secondcolumn.size()) {
			selectpos = pos;
			selecttext = secondcolumn.get(pos);
			notifyDataSetChanged();
		}

	}
  public void setDefaultPosition(int pos){
	  if(firstcolumn != null && pos < firstcolumn.size()){
		  selectpos=pos;
		  selecttext=firstcolumn.get(pos);
	  }
	  else if(secondcolumn != null && pos < secondcolumn.size()){
		  selectpos=pos;
		  selecttext=secondcolumn.get(pos);
	  }
		  
  }
 public int getPos(){
	 if(firstcolumn!=null||secondcolumn!=null)
		 return selectpos;
	 else return -1;
 }
}
