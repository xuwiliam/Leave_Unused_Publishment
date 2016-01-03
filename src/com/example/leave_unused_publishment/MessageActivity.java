package com.example.leave_unused_publishment;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ActivityGroup;
import android.app.LocalActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TabHost.OnTabChangeListener;
public class MessageActivity extends ActivityGroup{
 Context context;
 LocalActivityManager manager;
 
 TabHost tabHost;
 TextView tv1,tv2,tv3;
 List<View> listViews;
  @Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_message);
     manager = new LocalActivityManager(this,true);
     manager.dispatchCreate(savedInstanceState);
     init();
   }
  public void init(){
	  context=MessageActivity.this;
	 // pager=(ViewPager)findViewById(R.id.viewpager);
	  Intent discuss = new Intent(MessageActivity.this,CommentActivity.class);
	  Intent privatemsg = new Intent(MessageActivity.this,PrivateMsgActivity.class);
	  Intent systemact = new Intent(MessageActivity.this,SystemActivity.class);
	  tabHost=(TabHost)findViewById(R.id.tabhost);
	  tabHost.setup(manager);
	  RelativeLayout tabIndicator1 = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.tabmsg, null);  
      tv1 = (TextView)tabIndicator1.findViewById(R.id.msgtabtag);
      tv1.setText("评论");
      RelativeLayout tabIndicator2 = (RelativeLayout)LayoutInflater.from(this).inflate(R.layout.tabmsg, null);
      tv2 = (TextView)tabIndicator2.findViewById(R.id.msgtabtag);
      tv2.setText("私信");
      RelativeLayout tabIndicator3 = (RelativeLayout)LayoutInflater.from(this).inflate(R.layout.tabmsg, null);
      tv3 = (TextView)tabIndicator3.findViewById(R.id.msgtabtag);
      tv3.setText("系统");
      tabHost.addTab(tabHost.newTabSpec("dis").setIndicator(tabIndicator1).setContent(discuss));
      tabHost.addTab(tabHost.newTabSpec("pr").setIndicator(tabIndicator2).setContent(privatemsg));
      tabHost.addTab(tabHost.newTabSpec("sys").setIndicator(tabIndicator3).setContent(systemact));
      
      tabHost.setOnTabChangedListener(new OnTabChangeListener() {
          @Override
          public void onTabChanged(String tabId) {

              if ("dis".equals(tabId)) {
              	
                  tabHost.setCurrentTab(0);
                  tv1.setTextColor(getResources().getColor(R.color.select_text));
                  tv2.setTextColor(getResources().getColor(R.color.text_content));
                  tv3.setTextColor(getResources().getColor(R.color.text_content));
              }
              if ("pr".equals(tabId)) {

                  tabHost.setCurrentTab(1);
                  tv1.setTextColor(getResources().getColor(R.color.text_content));
                  tv2.setTextColor(getResources().getColor(R.color.select_text));
                  tv3.setTextColor(getResources().getColor(R.color.text_content));
              }
              if ("sys".equals(tabId)) {
                  tabHost.setCurrentTab(2);
                  tv1.setTextColor(getResources().getColor(R.color.text_content));
                  tv2.setTextColor(getResources().getColor(R.color.text_content));
                  tv3.setTextColor(getResources().getColor(R.color.select_text));            
          }
      }
      });
  }
  private View getView(String id, Intent intent) {
      return manager.startActivity(id, intent).getDecorView();
  }
}
