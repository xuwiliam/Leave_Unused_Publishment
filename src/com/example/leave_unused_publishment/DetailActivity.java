package com.example.leave_unused_publishment;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import com.example.leave_unused_publishment.Common.Global;
import com.example.leave_unused_publishment.adapter.CommentListAdapter;
import com.example.leave_unused_publishment.widget.BannerViewPager;
import com.example.leave_unused_publishment.widget.SelectPopWindow;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
public class DetailActivity extends BaseActivity implements OnClickListener,OnItemClickListener{
	private BannerViewPager banner;
	private BannerAdapter adapter_ban;
	private int bannerIndex;
	private List<Integer>ban_list;
	private Timer timer;
	private LinearLayout threebtn;
	private RelativeLayout input;
	private CommentListAdapter cmadapter;
	private List<Map<String, String>> list;
	private EditText edit;
	private ListView cmmlist;
	private Button submit;
	private ImageView likeimg;
	private SelectPopWindow menuWindow;
	private TextView dytext;
	private boolean hasgood=false;
	private RelativeLayout like,comment,chat,Viewholder;
	private int imgid[]={R.drawable.chicken1,R.drawable.chicken2,R.drawable.cake1,
			R.drawable.cake2,R.drawable.cake3
	};
	final Handler handler = new Handler(){
		public void handleMessage(Message msg){
		  if(msg.what==0){
			  cmadapter.notifyDataSetChanged();
			  if(cmadapter.getCount()>0)cmmlist.setVisibility(View.VISIBLE);
			  Global.MeasureListview(cmmlist);
		  }
		}
	};
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_comment);
        init();
    }
    public void onBackPressed(){
    	if(input.getVisibility()==View.VISIBLE){
    	  input.setVisibility(View.GONE);
    	  threebtn.setVisibility(View.VISIBLE);
    	}
    	else finish();
    }
    public void init(){
      dytext=(TextView)findViewById(R.id.dynamictext);
      dytext.setVisibility(View.INVISIBLE);
      likeimg=(ImageView)findViewById(R.id.likeorselledimg);
      list=new ArrayList<Map<String,String>>();
      cmmlist=(ListView)findViewById(R.id.commentlist);
      cmmlist.setOnItemClickListener(this);
      cmadapter = new CommentListAdapter(DetailActivity.this,list);
      cmmlist.setAdapter(cmadapter);
      banner=(BannerViewPager)findViewById(R.id.imgwall);
      threebtn=(LinearLayout)findViewById(R.id.threebtnlayout);
      submit=(Button)findViewById(R.id.submit);
      submit.setOnClickListener(this);
      threebtn.bringToFront();
      like=(RelativeLayout)findViewById(R.id.likeorselled);
      comment=(RelativeLayout)findViewById(R.id.comment);
      chat=(RelativeLayout)findViewById(R.id.chat);
      like.bringToFront();
      comment.bringToFront();
      chat.bringToFront();
      comment.setOnClickListener(this);
      like.setOnClickListener(this);
      chat.setOnClickListener(this);
      input=(RelativeLayout)findViewById(R.id.inputlayout);
      edit=(EditText)findViewById(R.id.input);
      edit.setFocusable(true);
      edit.setFocusableInTouchMode(true);
      ban_list=new ArrayList<Integer>();
      int i = 0;
      for(; i<5; i++)ban_list.add(imgid[i]);
      adapter_ban = new BannerAdapter(ban_list);
 	  banner.setAdapter(adapter_ban);
 	  if (!ban_list.isEmpty()) {
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
		}
    }
    public void onClick(View v){
    	int id = v.getId();
    	switch(id){
      case R.id.likeorselled:
    	  if(hasgood==false){
 			 hasgood=true;
 			 Global.good++;
 			 dytext.setText(String.valueOf(Global.good));
 			 likeimg.setBackgroundResource(R.drawable.haslike);
 			 dytext.setVisibility(View.VISIBLE);
 		 }
    	  else{
    		  hasgood=false;
  			 Global.good--;
  			 dytext.setVisibility(View.INVISIBLE);
  			 likeimg.setBackgroundResource(R.drawable.good);
    	  }
    		break;
    	case R.id.comment:
         threebtn.setVisibility(View.GONE);
   		 input.setVisibility(View.VISIBLE);
   		 input.bringToFront();
    		break;
    	case R.id.chat:
    		startActivity(new Intent(DetailActivity.this,ChatActivity.class));
    		break;
    	case R.id.submit:
    	  	handler.post(new Runnable(){
    	  		public void run(){
    	  			String cont = edit.getText().toString();
    	  			edit.setText("");
    	  			Map map = new HashMap();
    	  			map.put("content", cont);
    	  			map.put("name","Cici");
    	  			list.add(0, map);
    	  			handler.sendEmptyMessage(0);
    	  		}
    	  	});
    
    		
    	}
    }
    public void CreateMenuWindow(){
    	menuWindow = new SelectPopWindow(
				DetailActivity.this, itemsOnClick);

		menuWindow.showAtLocation(DetailActivity.this.findViewById(R.id.main_),
				Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }
    public OnClickListener itemsOnClick = new OnClickListener(){
    	public void onClick(View v){
    	  int id = v.getId();
    	  switch(id){
    	  case R.id.response:
    		  startActivityForResult(new Intent(DetailActivity.this,EditCommentActivity.class), 1);
    		  menuWindow.dismiss();
    	
    		 break;
    	  case R.id.cancel:
    		  menuWindow.dismiss();
    	  }
    	}
    };
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
			ImageView imageView=new ImageView(DetailActivity.this);
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
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		 Viewholder = (RelativeLayout)view;
	     CreateMenuWindow();
		
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if(resultCode==4){
			String comment=data.getStringExtra("comment");
			SpannableString ss = new SpannableString(Global.username+":"+comment);
			int len = Global.username.length();
			int len2 = comment.length();
			
			ss.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.surecolor)), 0, len+1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
			ss.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.text_content)),len+1,len+len2+1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
			TextView tv = new TextView(DetailActivity.this);
			tv.setText(ss);
			LinearLayout la = (LinearLayout)Viewholder.findViewById(R.id.responselist);
			la.addView(tv);
			la.setVisibility(View.VISIBLE);
			Global.MeasureListview(cmmlist);
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
}
