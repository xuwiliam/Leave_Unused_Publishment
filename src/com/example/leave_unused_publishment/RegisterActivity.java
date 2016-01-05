package com.example.leave_unused_publishment;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
public class RegisterActivity extends Activity implements OnClickListener{
	private EditText phonenumber;
	
	public void onCreate(Bundle savedInstanceState){
	   super.onCreate(savedInstanceState);	
	   this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	   setContentView(R.layout.activity_register);
	   init();
	}
    public void init(){
       ((Button)findViewById(R.id.next_btn)).setOnClickListener(this); 
       phonenumber=(EditText)findViewById(R.id.phone);
    }
    public void onClick(View v){
      int id = v.getId();
      switch(id){
      case R.id.next_btn:
    	  if(phonenumber.getText().toString().equals("")==false){
    	    Intent intent = new Intent(RegisterActivity.this,SettingPwdActivity.class); 
    	    intent.putExtra("username", phonenumber.getText().toString());
    	    startActivity(intent);
    	    finish();
    	  }
      }
    }
}
