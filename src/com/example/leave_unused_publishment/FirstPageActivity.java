package com.example.leave_unused_publishment;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import com.example.leave_unused_publishment.Common.Global;
import com.example.leave_unused_publishment.adapter.ListViewAdapter;
import com.example.leave_unused_publishment.widget.BannerViewPager;
import com.example.leave_unused_publishment.widget.CircleImageView;
import com.example.leave_unused_publishment.widget.MenuWindow;
import com.example.leave_unused_publishment.widget.StickyScrollView;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
public class FirstPageActivity extends BaseActivity {
  RelativeLayout zonelayout,grouplayout,sortlayout,selectionlayout;
  BannerViewPager banner;
  BannerAdapter adapter_ban;
  private int bannerIndex;
  private Timer timer;
  private GridView gr;
  private List<Integer> typel;
  int imgid[]={R.drawable.beijing1,R.drawable.beijing2,R.drawable.beijing3};
  int typeid[] = {R.drawable.book,R.drawable.electronic,R.drawable.pingpong,R.drawable.hiking,
		  R.drawable.clothes,R.drawable.digitproduct,R.drawable.lifeservice,R.drawable.others};
  private String name[]={"书籍资料","家用电器","文体用品","出行工具","服装鞋裙","数码产品","生活服务","其它"};
  ListViewAdapter ladapter;
  StickyScrollView sv;
  GridAdapter ga ;
  ListView pl;
  LinearLayout ly;
  List<Map<String, Object>> list_sale = new ArrayList<Map<String, Object>>();
  int y;
  List<Integer>list;
 
  final Handler handler  = new Handler(){
	  public void handleMessage(Message msg){
		    ladapter.notifyDataSetChanged();
		    ga.notifyDataSetChanged();
		    Global.MeasureListview(pl);
	  }
  };
  final Handler handler_ban = new Handler(){
	public void handleMessage(Message msg){  
		
	}
  };
  public void onCreate(Bundle savedInstanceState){
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.activity_firstpage);
	  Global.firstpageactivity=this;
	  init();
  }
  public void init(){
	  
	 ly=(LinearLayout)findViewById(R.id.conditionlayout);
	 pl = (ListView)findViewById(R.id.productlist);
	// pl.setOnItemClickListener(this);
	// mask = (View)findViewById(R.id.mask);
	 banner = (BannerViewPager)findViewById(R.id.picwall);
	 banner.setVisibility(View.VISIBLE);
	 ((LinearLayout)findViewById(R.id.slayout)).setVisibility(View.GONE);
	 gr = (GridView)findViewById(R.id.type);
	 typel = new ArrayList<Integer>();
	 list = new ArrayList<Integer>();
	 ImageView img;
	 for(int i = 0; i<3; i++){
	   list.add(imgid[i]);
	 }
	 
	
	 adapter_ban = new BannerAdapter(list);
	 banner.setAdapter(adapter_ban);
	 sv = (StickyScrollView)findViewById(R.id.scroll);
	 zonelayout=(RelativeLayout)findViewById(R.id.zonelayout);
	 grouplayout=(RelativeLayout)findViewById(R.id.grouplayout);
	 sortlayout=(RelativeLayout)findViewById(R.id.sortlayout);
	 selectionlayout=(RelativeLayout)findViewById(R.id.selectlayout);
	  String str[]=new String[200];
	     for(int j = 0; j<200; j++){
	    	 if(j<190)
	    	 str[j]="xxx";
	    	 else
	         str[j]="aaa";
	     }
	     
	     Map<String,Object>map;
	    /* for(int j = 0; j<5; j++){
	    	 map = new HashMap<String,Object>();
	    	 map.put("itemname", str[j]);
	    	 list_sale.add(map);
	     }*/
	     ladapter = new ListViewAdapter(FirstPageActivity.this, list_sale);
	     pl.setAdapter(ladapter);
        // MeasureListview(pl);
         pl.setDividerHeight(0);
        /* for(int i = 0; i<1; i++){
			 typel.add(typeid[i]);
		 }*/
        ga = new GridAdapter(typel,FirstPageActivity.this);
	    gr.setAdapter(ga);
	
    
     
	 zonelayout.setOnClickListener(new OnClickListener() {
    
	     public void onClick(View v){
	    	 Log.e("getTop",String.valueOf(Global.height));
	    	 sv.scrollTo(0, Global.height);
             //backgroundAlpha(0.7f);
	    	 openMenuWindow(v,Global.zone);
	    	 
	     }
	 });
	 grouplayout.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			 sv.scrollTo(0, Global.height);
             //backgroundAlpha(0.7f);
	    	 openMenuWindow(v,Global.group);
		}
	});
	sortlayout.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			 sv.scrollTo(0, Global.height);
             //backgroundAlpha(0.7f);
	    	 openMenuWindow(v,Global.sort);	
		}
	});
	selectionlayout.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			 sv.scrollTo(0, Global.height);
             //backgroundAlpha(0.7f);
	    	 openMenuWindow(v,Global.select);
		}
	});
	/* if (!list.isEmpty()) {
			// 刷新的时候，先把原来的去掉
			if (timer != null) {
				timer.cancel();
			}
			timer = new Timer();
			TimerTask task = new TimerTask() {
				
				@Override
				public void run() {
					bannerIndex = banner.getViewPager().getCurrentItem() + 1;
					if (bannerIndex >= adapter_ban.getCount()) {
						bannerIndex = 0;
					}
					runOnUiThread(new Runnable() {
						public void run() {
//							Log.d(Constants.DEBUG_TAG, "set banner, index= " + bannerIndex);
							banner.getViewPager().setCurrentItem(bannerIndex);
						}
					});
				}
			};
			timer.scheduleAtFixedRate(task, 0, 3000);
		}*/
	 handler.postDelayed(runnable, 3000);
	handler_ban.postDelayed(runnable2, 2000);
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
 
  public void onResume(){
	  super.onResume();
	  
	 
  }
  public void onWindowFocusChanged(boolean hasFocus){
	  super.onWindowFocusChanged(hasFocus);
	  y=ly.getTop();
	  Log.e("y",String.valueOf(y));
	  Global.width=pl.getWidth();
	  //mask.setVisibility(View.GONE);
	}
  private class BannerAdapter extends PagerAdapter {
		private List<Integer> banners;
		
		public BannerAdapter(List<Integer> banners) {
			this.banners = banners;
		}
		
		@Override
		public boolean isViewFromObject(View view, Object obj) {
			return view == obj;
		}
		
		@Override
		public int getCount() {
			return banners.size();
		}
		
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			ImageView imageView=new ImageView(FirstPageActivity.this);
			imageView.setImageResource(banners.get(position));
			imageView.setScaleType(ScaleType.CENTER_CROP);
			container.addView(imageView);
			imageView.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					//to-do
				}
			});
			
			return imageView;
		}
		
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}
	}
  protected void onDestroy() {
		super.onDestroy();
		if (timer != null) {
			timer.cancel();
		}
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
          tv.setText(name[position]);
          //img.setScaleType(ScaleType.CENTER_CROP);
          //TextView text = (TextView)convertView.findViewById(R.id.itemtext);
          //text.setText(cont);
          return convertView;
      }
  }
  final Runnable runnable = new Runnable(){
	  public void run(){
		  String str[]=new String[200];
		     for(int j = 0; j<200; j++){
		    	 if(j<190)
		    	 str[j]="xxx";
		    	 else
		         str[j]="aaa";
		     }
		     
		     Map<String,Object>map;
		     for(int j = 0; j<200; j++){
		    	 map = new HashMap<String,Object>();
		    	 map.put("itemname", str[j]);
		    	 list_sale.add(map);
		     }
		    // ladapter = new ListViewAdapter(FirstPageActivity.this, list_sale);
		     for(int i = 0; i<8; i++){
				 typel.add(typeid[i]);
			 }
		     handler.sendEmptyMessage(0);
	  }
  };
  final Runnable runnable2 = new Runnable(){
	  public void run() {
			bannerIndex = banner.getViewPager().getCurrentItem() + 1;
			if (bannerIndex >= adapter_ban.getCount()) {
				bannerIndex = 0;
			}
			runOnUiThread(new Runnable() {
				public void run() {
//					Log.d(Constants.DEBUG_TAG, "set banner, index= " + bannerIndex);
					banner.getViewPager().setCurrentItem(bannerIndex);
				}
			});
			handler_ban.postDelayed(this, 3000);
		}
  };

}
