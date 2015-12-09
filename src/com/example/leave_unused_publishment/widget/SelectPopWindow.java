package com.example.leave_unused_publishment.widget;


import com.example.leave_unused_publishment.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

public class SelectPopWindow extends PopupWindow {
	public static Activity con;
	private View mMenuView;

	public RelativeLayout response,cancel;
	
	
	public SelectPopWindow(Activity context,OnClickListener itemsOnClick) {
		super(context);
		con = context;
	  
	 
	    	
	      mMenuView = LayoutInflater.from(con).inflate(R.layout.layout_dialog, null);
	 		  
	 	  response = (RelativeLayout)mMenuView.findViewById(R.id.response);
	 	  cancel=(RelativeLayout)mMenuView.findViewById(R.id.cancel);
	 	  response.setOnClickListener(itemsOnClick);
	 	  cancel.setOnClickListener(itemsOnClick);
	      this.setContentView(mMenuView);
		  this.setWidth(LayoutParams.MATCH_PARENT);
		  this.setHeight(LayoutParams.MATCH_PARENT);
		  this.setFocusable(true);
		  this.setAnimationStyle(R.style.AnimBottom);
		  ColorDrawable dw = new ColorDrawable(0xb0000000);
		  this.setBackgroundDrawable(dw);
		  mMenuView.setOnTouchListener(new OnTouchListener() {
			
			public boolean onTouch(View v, MotionEvent event) {
				
				int height = mMenuView.findViewById(R.id.popup).getTop();
				int y=(int) event.getY();
				if(event.getAction()==MotionEvent.ACTION_UP){
					if(y<height){
						dismiss();
					}
				}				
				return true;
			}
		  });

	}	
}
