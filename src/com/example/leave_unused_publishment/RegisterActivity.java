package com.example.leave_unused_publishment;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
public class RegisterActivity extends Activity implements OnClickListener{
	public void onCreate(Bundle savedInstanceState){
	   super.onCreate(savedInstanceState);	
	   setContentView(R.layout.activity_register);
	   init();
	}
    public void init(){
       ((Button)findViewById(R.id.next_btn)).setOnClickListener(this);    	
    }
    public void onClick(View v){
      int id = v.getId();
      switch(id){
      case R.id.next_btn:
    	  startActivity(new Intent(RegisterActivity.this,SettingPwdActivity.class));
      }
    }
}
