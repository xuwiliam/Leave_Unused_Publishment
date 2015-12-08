package com.example.leave_unused_publishment.widget;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import com.example.leave_unused_publishment.R;
import com.viewpagerindicator.CirclePageIndicator;

public class BannerViewPager extends RelativeLayout {
	private LoopViewPager pager;
	private CirclePageIndicator indicator;
	
	public BannerViewPager(Context context) {
		this(context, null);
	}

	public BannerViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		View rootView = View.inflate(context, R.layout.banner_view_pager, null);
		addView(rootView);
		
		pager = (LoopViewPager) rootView.findViewById(R.id.pager);
		indicator = (CirclePageIndicator) rootView.findViewById(R.id.indicator);
	}
	
	public void setAdapter(PagerAdapter adapter) {
		pager.setAdapter(adapter);
		indicator.setViewPager(pager);
	}
	
	public CirclePageIndicator getIndicator() {
		return indicator;
	}
	
	public ViewPager getViewPager() {
		return pager;
	}

}
