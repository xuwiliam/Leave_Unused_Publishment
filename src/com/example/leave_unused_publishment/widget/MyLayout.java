package com.example.leave_unused_publishment.widget;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
public class MyLayout extends RelativeLayout{
  public MyLayout(Context context,AttributeSet attrs) {
	  this(context,attrs,0);
	// TODO Auto-generated constructor stub
  }
  public MyLayout(Context context){
	  this(context,null);
  }
  public MyLayout(Context context, AttributeSet attrs,int defStyleAttr){
	  super(context,attrs,defStyleAttr);
  }
}
