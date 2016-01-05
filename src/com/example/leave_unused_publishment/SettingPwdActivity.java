package com.example.leave_unused_publishment;
import org.json.JSONObject;

import com.example.leave_unused_publishment.network.Transfer;
import com.example.leave_unused_publishment.network.TransferListener;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
public class SettingPwdActivity extends Activity {
  private EditText setpwd;
  private TextView show;
  private Button registerbtn;
  public void onCreate(Bundle savedInstanceState){
	  super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_setpwd);
      init();
  }
  public void init(){
	  setpwd = (EditText)findViewById(R.id.pwd);
	  show = (TextView)findViewById(R.id.show);
	  show.setOnClickListener(new OnClickListener(){
		 public void onClick(View v){
		   setpwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
		   show.setVisibility(View.INVISIBLE);
		 }
	  });
	  registerbtn = (Button)findViewById(R.id.register_btn);
	  registerbtn.setOnClickListener(new OnClickListener(){
         @Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
	       Transfer.register(getIntent().getStringExtra("username"),"", setpwd.getText().toString(),106,"", new TransferListener() {
	    	   
			
			@Override
			public void onSucceed(JSONObject obj) {
				// TODO Auto-generated method stub
			    	Log.e("setpwd",obj.toString());
			    	Toast.makeText(SettingPwdActivity.this, "用户创建成功", Toast.LENGTH_SHORT).show();
			    	finish();
			}
			
			@Override
			public void onFail(String desc) {
				// TODO Auto-generated method stub
				
			}
		});	
		}
	  });
	 
  }
}
