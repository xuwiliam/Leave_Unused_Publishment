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
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
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
public class DetailActivity extends Activity implements OnClickListener,OnItemClickListener{
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
	private SelectPopWindow menuWindow;
	private RelativeLayout like,comment,chat;
	private int imgid[]={R.drawable.chicken1,R.drawable.chicken2,R.drawable.cake1,
			R.drawable.cake2,R.drawable.cake3
	};
	final Handler handler = new Handler(){
		public void handleMessage(Message msg){
		  if(msg.what==0){
			  cmadapter.notifyDataSetChanged();
			  Global.MeasureListview(cmmlist);
		  }
		}
	};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
    		break;
    	case R.id.comment:
         threebtn.setVisibility(View.GONE);
   		 input.setVisibility(View.VISIBLE);
   		 input.bringToFront();
    		break;
    	case R.id.chat:
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
	     CreateMenuWindow();
		// TODO Auto-generated method stub
		
	}
}
