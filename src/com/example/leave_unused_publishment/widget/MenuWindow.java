package com.example.leave_unused_publishment.widget;

import android.app.ActionBar;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.PopupWindow;

public class MenuWindow {
	 private static PopupWindow popupWindow;
	    private View view;
	    private View parentView;
	    private int width;
	    private int height;
	    private int xoff;
	    private int yoff;
	    private int backgroundcolor;

	    boolean isShow = false;

	    private final int DEFAULT_XOFF = 10;
	    private final int DEFAULT_YOFF = 0;
	    private final int DEFAULT_WIDTH = ActionBar.LayoutParams.WRAP_CONTENT;
	    private final int DEFAULT_HEIGHT = ActionBar.LayoutParams.WRAP_CONTENT;
	    private final int DEFAULT_BACKGROUND = 0x0000000000;


	    public MenuWindow(View view,View parent){
	        this.view = view;
	        this.parentView = parent;
	        this.width = DEFAULT_WIDTH;
	        this.height = DEFAULT_HEIGHT;
	        this.xoff = DEFAULT_XOFF;
	        this.yoff = DEFAULT_YOFF;
	        this.backgroundcolor = DEFAULT_BACKGROUND;
	    }

	    public void setLayout(int width, int height){
	        this.width = width;
	        this.height = height;

	    }

	    public void setOffset(int xoff, int yoff){
	        this.xoff = xoff;
	        this.yoff = yoff;
	    }

	    public void setBackgroundColor(int color){
	        this.backgroundcolor = color;
	    }

	    public void show(){
	        if(isShow){
	            this.dismiss();
	            isShow = false;
	        }

	        popupWindow = new PopupWindow(view,width, height);
	        popupWindow.setFocusable(true);
	        popupWindow.setOutsideTouchable(true);

	        // 实例化一个ColorDrawable
	        ColorDrawable dw = new ColorDrawable(0xb0000000);
			//设置SelectPicPopupWindow弹出窗体的背景
			//setBackgroundDrawable(dw);
	        popupWindow.setBackgroundDrawable(dw);

	        popupWindow.showAsDropDown(parentView, xoff, yoff);

	        isShow = true;

	    }
	    public void dismiss(){
	        if(isShow) {
	            popupWindow.dismiss();
	            isShow = false;
	        }
	    }
}
