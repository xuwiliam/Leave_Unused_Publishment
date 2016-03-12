package com.example.leave_unused_publishment;
import com.example.leave_unused_publishment.widget.PopList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
public class ClassifyActivity extends Activity {
  PopList list;
  TextView sure;
  ImageView back;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_classify);
	init();
  }
  public void init(){
	  list = (PopList)findViewById(R.id.Selectview);
	  sure = (TextView)findViewById(R.id.classifysubmit);
	  back = (ImageView)findViewById(R.id.classifyback);
  }
}
