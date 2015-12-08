package com.example.leave_unused_publishment;
import android.app.Activity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
public class SettingPwdActivity extends Activity {
  private EditText setpwd;
  private TextView show;
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
  }
}
