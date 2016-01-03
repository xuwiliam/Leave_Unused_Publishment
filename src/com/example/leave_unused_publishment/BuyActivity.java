package com.example.leave_unused_publishment;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.leave_unused_publishment.Common.Global;
import com.example.leave_unused_publishment.adapter.BuyListAdapter;
import com.example.leave_unused_publishment.adapter.CommentListAdapter;
import com.example.leave_unused_publishment.adapter.ListViewAdapter;
import com.example.leave_unused_publishment.widget.BannerViewPager;
import com.example.leave_unused_publishment.widget.CircleImageView;
import com.example.leave_unused_publishment.widget.MenuWindow;
import com.example.leave_unused_publishment.widget.StickyScrollView;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.RelativeLayout;
public class BuyActivity extends Activity implements OnClickListener{
  private GridAdapter ga;
  int y;
  LinearLayout ly;
  BuyListAdapter ladapter;
  ListView pl;
  private TextView thing,people;
  private RelativeLayout llayout,rlayout, zonelayout,grouplayout,sortlayout,selectionlayout;
  StickyScrollView sv;
  private GridView gv;
  private List<Map<String,String>> bl = new ArrayList<Map<String,String>>();
  private List<String> ls = new ArrayList<String>();
  private List<Integer> l = new ArrayList<Integer>();
  String zone[]={"全部","中山大学","广外","广工","广中医"};
  private int thingarr[]={R.drawable.book,R.drawable.electronic,R.drawable.pingpong,R.drawable.hiking,
		  R.drawable.clothes,R.drawable.digitproduct,R.drawable.lifeservice,R.drawable.others
  };
  private String thingname[]={"书籍资料","家用电器","文体用品","出行工具","服装鞋裙","数码产品","生活服务","其它"};
  private int peoplearr[]={R.drawable.deliver,R.drawable.canteen,R.drawable.exercise,R.drawable.shop,R.drawable.contest,
		  R.drawable.skill,R.drawable.ask,R.drawable.other
		  
  };
  private String peoplename[]={"拿快递","饭堂打包","陪运动","陪逛街","打比赛","学技能","求解答","其它"};
  final Handler handler = new Handler(){
	  public void handleMessage(Message msg){
	    if(msg.what==0){
	    	ga.notifyDataSetChanged();
	    	ladapter.notifyDataSetChanged();
	    	 Global.MeasureListview(pl);
	    }
	  }
  };
  protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_firstpage);
    init();
  }
  public void init(){
	((BannerViewPager)findViewById(R.id.picwall)).setVisibility(View.GONE);
	pl=(ListView)findViewById(R.id.productlist);
	ladapter=new BuyListAdapter(BuyActivity.this, bl);
	pl.setAdapter(ladapter);
	ly=(LinearLayout)findViewById(R.id.conditionlayout);
	llayout=(RelativeLayout)findViewById(R.id.thing);
	rlayout=(RelativeLayout)findViewById(R.id.people);
	sv = (StickyScrollView)findViewById(R.id.scroll);
	zonelayout=(RelativeLayout)findViewById(R.id.zonelayout);
	grouplayout=(RelativeLayout)findViewById(R.id.grouplayout);
	sortlayout=(RelativeLayout)findViewById(R.id.sortlayout);
	selectionlayout=(RelativeLayout)findViewById(R.id.selectlayout);
	zonelayout.setOnClickListener(this);
	grouplayout.setOnClickListener(this);
	sortlayout.setOnClickListener(this);
	selectionlayout.setOnClickListener(this);
	thing=(TextView)findViewById(R.id.thingtext);
	gv=(GridView)findViewById(R.id.type);
	people=(TextView)findViewById(R.id.peopletext);
	ga = new GridAdapter(l,BuyActivity.this);
	gv.setAdapter(ga);
	llayout.setOnClickListener(this);
	rlayout.setOnClickListener(this);
	thing.setOnClickListener(this);
	people.setOnClickListener(this);
	handler.postDelayed(new Runnable(){
		public void run(){
		  int i = 0;
		  for(; i<thingarr.length; i++){
			  l.add(thingarr[i]);
		      ls.add(thingname[i]);
		  }
		  i = 0;
		  Map map;
		  for(; i<40; i++){
			  map = new HashMap();
			  map.put("url", "http//:sfsdfsdf");
			  map.put("title", "vvv");
			  map.put("price", "1");
			  map.put("location","zzzz");
			  bl.add(map);
		  }
			  
		  handler.sendEmptyMessage(0);
		}
	}, 2000);
  }
  @Override
 	public void onClick(View v) {
 		// TODO Auto-generated method stub
	  int id = v.getId();
	  switch(id){
	  case R.id.thing:
		  llayout.setBackgroundResource(R.drawable.stroke_half_corner_left);
		  thing.setTextColor(getResources().getColor(R.color.general));
		  rlayout.setBackgroundResource(R.drawable.half_corner_right);
		  people.setTextColor(getResources().getColor(R.color.select_text));
		  l.clear();
		  ls.clear();
		  int j = 0;
		  for(;j<thingarr.length; j++){
			  l.add(thingarr[j]);
		      ls.add(thingname[j]);
		  }
		  ga.notifyDataSetChanged();
	      break;
	  case R.id.people:
		  llayout.setBackgroundResource(R.drawable.half_corner);
		  thing.setTextColor(getResources().getColor(R.color.select_text));
		  rlayout.setBackgroundResource(R.drawable.stroke_half_corner_right);
		  people.setTextColor(getResources().getColor(R.color.general));
		  l.clear();
		  ls.clear();
		  int t= 0;
		  for(; t<peoplearr.length; t++){
			  l.add(peoplearr[t]);
		      ls.add(peoplename[t]);
		  }
		  ga.notifyDataSetChanged();
		  break;
	  case R.id.thingtext:
		  llayout.setBackgroundResource(R.drawable.stroke_half_corner_left);
		  thing.setTextColor(getResources().getColor(R.color.general));
		  rlayout.setBackgroundResource(R.drawable.half_corner_right);
		  people.setTextColor(getResources().getColor(R.color.select_text));
	      break;
	  case R.id.peopletext:
		  llayout.setBackgroundResource(R.drawable.stroke_half_corner_left);
		  thing.setTextColor(getResources().getColor(R.color.general));
		  rlayout.setBackgroundResource(R.drawable.half_corner_right);
		  people.setTextColor(getResources().getColor(R.color.select_text));
	      break;
	  case R.id.zonelayout:
		  sv.scrollTo(0, Global.height);
		  openMenuWindow(v,Global.zone);
	      break;
	  case R.id.grouplayout:
		  sv.scrollTo(0, Global.height);
          //backgroundAlpha(0.7f);
	      openMenuWindow(v,Global.group);
	      break;
	  case R.id.sortlayout:
		  sv.scrollTo(0, Global.height);
          //backgroundAlpha(0.7f);
	      openMenuWindow(v,Global.sort);
	      break;
	  case R.id.selectlayout:
		  sv.scrollTo(0, Global.height);
          //backgroundAlpha(0.7f);
	      openMenuWindow(v,Global.select);
	      break;
	  }
 		
 	}
  public void onWindowFocusChanged(boolean hasFocus){
	  super.onWindowFocusChanged(hasFocus);
	  y=ly.getTop();
	  Log.e("y",String.valueOf(y));
	  //mask.setVisibility(View.GONE);
	}
  private class GridAdapter extends BaseAdapter{
      List<Integer> contentlist;
      Context con;
      public GridAdapter(List<Integer> l,Context c){
        contentlist = l;
        con = c;
      }
      public int getCount(){
        return contentlist.size();
      }
      public Object getItem(int pos){
         return contentlist.get(pos);
      }
      public long getItemId(int pos){
          return pos;
      }
      public View getView(int position,View convertView,ViewGroup parent){
          if(convertView==null){
              convertView = LayoutInflater.from(con).inflate(R.layout.item_type,null);
          }
          /*int width = gr.getWidth();
          int height = gr.getHeight();
          GridView.LayoutParams params = new GridView.LayoutParams(width/3,height/3);
          convertView.setLayoutParams(params);*/
          ImageView img = (ImageView)convertView.findViewById(R.id.typeimg);
          img.setImageResource(contentlist.get(position));
          TextView tv = (TextView)convertView.findViewById(R.id.imgcontent);
          tv.setText(ls.get(position));
          //img.setScaleType(ScaleType.CENTER_CROP);
          //TextView text = (TextView)convertView.findViewById(R.id.itemtext);
          //text.setText(cont);
          return convertView;
      }
  }
  public void openMenuWindow(View v,String namearr[]){
	    //  backgroundAlpha(0.7f);
		  View view = LayoutInflater.from(this).inflate(R.layout.menu_window, null);
		  ListView l = (ListView)view.findViewById(R.id.mylist);
		  List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		  Map<String,Object>map = null;
		  int i = 0;
		  for(; i<namearr.length; i++){
			  
		    map = new HashMap<String,Object>();
		    map.put("itemname", namearr[i]);
		    list.add(map);
		  }
		 
		  SimpleAdapter adapter = new SimpleAdapter(this, list,
	              R.layout.item_tv, new String[] { "itemname" },
	              new int[] {R.id.myitem });
		  l.setAdapter(adapter);
	       
		  WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
	      final MenuWindow menuWindow = new MenuWindow(view,v);
	      menuWindow.setLayout(windowManager.getDefaultDisplay().getWidth(), ActionBar.LayoutParams.MATCH_PARENT);
	      menuWindow.show();
	      //backgroundAlpha(0.7f);
	      //mask.setVisibility(View.VISIBLE);
	  }
	 
}
