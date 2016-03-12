package com.example.leave_unused_publishment;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.RelativeLayout;
public class MyInfoActivity extends Activity implements OnClickListener{
	RelativeLayout lendlayout,wantedlayout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_myinfo);
		init();
	}
    public void init(){
    	lendlayout=(RelativeLayout)findViewById(R.id.mylend);
    	lendlayout.setOnClickListener(this);
    	wantedlayout=(RelativeLayout)findViewById(R.id.mywant);
    	wantedlayout.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
    	// TODO Auto-generated method stub
      int id = v.getId();
      switch(id){
      case R.id.mylend:
    	  startActivity(new Intent(MyInfoActivity.this,CollectionActivity.class));
    	  break;
      case R.id.mywant:
    	  startActivity(new Intent(MyInfoActivity.this,MyWantedActivity.class));
    	  break;
      }
    }
}
