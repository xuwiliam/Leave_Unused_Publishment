package com.example.leave_unused_publishment;

import java.util.ArrayList;
import java.util.List;

import com.example.leave_unused_publishment.Common.Global;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.app.ActivityGroup;
import android.app.LocalActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TextView;

public class MainActivity extends ActivityGroup{
    
	List<View> listViews;
    Context context = null;
    LocalActivityManager manager = null;
    TabHost tabHost = null;
    private ViewPager pager = null;
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        manager = new LocalActivityManager(this,true);
        manager.dispatchCreate(savedInstanceState);
        init();
    }
	public void init(){
		context=MainActivity.this;
        pager = (ViewPager)findViewById(R.id.viewpager);
        listViews = new ArrayList<View>();
        Intent fpa = new Intent(MainActivity.this,FirstPageActivity.class);
        listViews.add(getView("f",fpa));
        Intent buy = new Intent(MainActivity.this,BuyActivity.class);
        listViews.add(getView("b",buy));
        Intent publish = new Intent(MainActivity.this,PublishActivity.class);
        listViews.add(getView("p",publish));
        Intent message = new Intent(MainActivity.this,MessageActivity.class);
        listViews.add(getView("me",message));
        Intent myinfo = new Intent(MainActivity.this,MyInfoActivity.class);
        listViews.add(getView("my",myinfo));
        tabHost = (TabHost)findViewById(R.id.tabhost);
        //tabHost.setup();
        tabHost.setup(manager);
       // LayoutInflater inflater = (LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE);
        RelativeLayout tabIndicator1 = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.tabwidget, null);  
		ImageView img1 = (ImageView)tabIndicator1.findViewById(R.id.iv_mark);
		img1.setBackgroundResource(R.drawable.firstpage);
		TextView tvTab1 = (TextView)tabIndicator1.findViewById(R.id.tv_title);
		tvTab1.setText("首页");
		tvTab1.setTextColor(R.drawable.textcolor_selector);
		RelativeLayout tabIndicator2 = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.tabwidget, null);  
		ImageView img2 = (ImageView)tabIndicator2.findViewById(R.id.iv_mark);
		img2.setBackgroundResource(R.drawable.buy);
		TextView tvTab2 = (TextView)tabIndicator2.findViewById(R.id.tv_title);
		tvTab2.setText("求购");
		tvTab2.setTextColor(R.drawable.textcolor_selector);
		RelativeLayout tabIndicator3 = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.tabadd, null);  
		ImageView img3 = (ImageView)tabIndicator3.findViewById(R.id.iv_img);
		img3.setBackgroundResource(R.drawable.publish);
		RelativeLayout tabIndicator4 = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.tabwidget, null);  
		ImageView img4 = (ImageView)tabIndicator4.findViewById(R.id.iv_mark);
		img4.setBackgroundResource(R.drawable.message);
		TextView tvTab4 = (TextView)tabIndicator4.findViewById(R.id.tv_title);
		tvTab4.setText("消息");
		tvTab4.setTextColor(R.drawable.textcolor_selector);
		RelativeLayout tabIndicator5 = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.tabwidget, null);  
		ImageView img5 = (ImageView)tabIndicator5.findViewById(R.id.iv_mark);
		img5.setBackgroundResource(R.drawable.myinfo);
		TextView tvTab5 = (TextView)tabIndicator5.findViewById(R.id.tv_title);
		tvTab5.setText("我");
		tvTab5.setTextColor(R.drawable.textcolor_selector);
		Intent intent = new Intent(context,EmptyActivity.class);
		//注意这儿Intent中的activity不能是自身 比如“A”对应的是T1Activity，后面intent就new的T3Activity的。
		tabHost.addTab(tabHost.newTabSpec("f").setIndicator(tabIndicator1).setContent(intent));
		tabHost.addTab(tabHost.newTabSpec("b").setIndicator(tabIndicator2).setContent(intent));
		tabHost.addTab(tabHost.newTabSpec("p").setIndicator(tabIndicator3).setContent(intent));
		tabHost.addTab(tabHost.newTabSpec("me").setIndicator(tabIndicator4).setContent(intent));
		tabHost.addTab(tabHost.newTabSpec("my").setIndicator(tabIndicator5).setContent(intent));
		 pager .setAdapter(new MyPageAdapter(listViews));
	        pager .setOnPageChangeListener(new OnPageChangeListener() {
	            @Override
	            public void onPageSelected(int position) {
	                //当viewPager发生改变时，同时改变tabhost上面的currentTab
	                tabHost.setCurrentTab(position);
	            }
	            @Override
	            public void onPageScrolled(int arg0, float arg1, int arg2) {
	            }
	            @Override
	            public void onPageScrollStateChanged(int arg0) {
	            }
	        });


	        tabHost.setOnTabChangedListener(new OnTabChangeListener() {
	            @Override
	            public void onTabChanged(String tabId) {

	                if ("f".equals(tabId)) {
	                	
	                    pager.setCurrentItem(0);
	                }
	                if ("b".equals(tabId)) {

	                    pager.setCurrentItem(1);
	                }
	                if ("p".equals(tabId)) {
	                    pager.setCurrentItem(2);
	                }
	                if ("me".equals(tabId)) {
	                    pager.setCurrentItem(3);
	                }
	                if ("my".equals(tabId)) {
	                    pager.setCurrentItem(4);
	                }
	            }
	        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	public void onClick(View v){
		
	}
	private View getView(String id, Intent intent) {
        return manager.startActivity(id, intent).getDecorView();
    }
private class MyPageAdapter extends PagerAdapter {
		
		private List<View> list;

		private MyPageAdapter(List<View> list) {
			this.list = list;
		}

		@Override
        public void destroyItem(View view, int position, Object arg2) {
            ViewPager pViewPager = ((ViewPager) view);
            pViewPager.removeView(list.get(position));
        }

        @Override
        public void finishUpdate(View arg0) {
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object instantiateItem(View view, int position) {
            ViewPager pViewPager = ((ViewPager) view);
            pViewPager.addView(list.get(position));
            return list.get(position);
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {
        }

        @Override
        public Parcelable saveState() {
            return null;
        }

        @Override
        public void startUpdate(View arg0) {
        }

	}
 @Override
public void onWindowFocusChanged(boolean hasFocus) {
	if(Global.firstpageactivity!=null){
		Global.height=Global.firstpageactivity.ly.getTop();
	    Log.e("focus",String.valueOf(Global.height));
	}
	super.onWindowFocusChanged(hasFocus);
}
  @Override
 public void onActivityResult(int requestCode, int resultCode, Intent data) {
	// TODO Auto-generated method stub
	// Log.e(null,null);
	  Log.e("here","here");
	 if(Global.pubactivity!=null){
		 Global.pubactivity.onActivityResult_pub(requestCode, resultCode, data);
	   
	 }
	 
	
}
}
