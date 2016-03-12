package com.example.leave_unused_publishment.widget;

import java.util.ArrayList;
import java.util.List;

import com.example.leave_unused_publishment.R;
import com.example.leave_unused_publishment.Common.Global;
import com.example.leave_unused_publishment.adapter.ExpandAdapter;
import com.example.leave_unused_publishment.adapter.ExpandAdapter.onSelectedListener;

import android.content.Context;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow.OnDismissListener;

public class PopList extends LinearLayout{
   ListView kind,detail;
   List<String> childrenitem = new ArrayList<String>();
   SparseArray<List<String>> array = new SparseArray<List<String>>();
   private String people[]={"拿快递","饭堂打包","陪运动","陪逛街","打比赛","学技能","求解答","其它"};
   ExpandAdapter firstadapter,secondadapter;
   int firstdefaultpos,secdefaultpos;
   public PopList(Context context) {
	   
		super(context);
		// TODO Auto-generated constructor stub
		init(context,null);
	 }
   public PopList(Context context,AttributeSet attr){
	   super(context, attr);
	   init(context,attr);
   }
   @Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		super.onLayout(changed, l, t, r, b);
		int w = getWidth();
	    LayoutParams param=(LayoutParams) kind.getLayoutParams();
	    param.width=w/4;
	    param.height=LayoutParams.MATCH_PARENT;
	    kind.setLayoutParams(param);
	   
	    param=(LayoutParams)detail.getLayoutParams();
	    param.width=w*3/4;
	    param.height=LayoutParams.MATCH_PARENT;
	    detail.setLayoutParams(param);
	    
	}

  
  public void init(Context context,AttributeSet attri){
	  LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	  inflater.inflate(R.layout.view_region, this,true);
	  kind=(ListView)findViewById(R.id.listView);
	  detail=(ListView)findViewById(R.id.listView2);
	  int i = 0;
	  List<String> tmp = new ArrayList<String>();
	  for(; i<Global.group.length;i++ )
		  tmp.add(Global.group[i]);
      array.append(0, tmp);
      tmp = new ArrayList<String>();
      for(i = 0; i<people.length; i++)
    	  tmp.add(people[i]);
      array.append(1, tmp);
      firstadapter=new ExpandAdapter(context, new ArrayList(){{add("物");add("人");}}, 1);
      childrenitem.addAll(array.get(0));
      secondadapter=new ExpandAdapter(context,childrenitem,2); 
      firstadapter.setOnSelectedListener(new onSelectedListener(){
    	  @Override
    	public void itemselected(View view, int pos) {
    		// TODO Auto-generated method stub
    	   if(pos<2){
    		  childrenitem.clear();
    		  childrenitem.addAll(array.get(pos));
    		  secondadapter.notifyDataSetChanged();
    	   }
    	   
    	}
      });
      secondadapter.setOnSelectedListener(new onSelectedListener() {
		
		@Override
		public void itemselected(View view, int pos) {
			// TODO Auto-generated method stub
		  String data = childrenitem.get(pos);	
		}
	});
      setDefaultPos(0,0);
      firstadapter.setDefaultPosition(firstdefaultpos);
      secondadapter.setDefaultPosition(secdefaultpos);
      kind.setAdapter(firstadapter);
      detail.setAdapter(secondadapter);
      kind.setSelection(firstdefaultpos);
      detail.setSelection(secdefaultpos);
    }
    public void setDefaultPos(int f, int s){
    	firstdefaultpos = f;
    	secdefaultpos = s;
    }
}
