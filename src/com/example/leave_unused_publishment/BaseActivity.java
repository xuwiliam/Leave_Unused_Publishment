package com.example.leave_unused_publishment;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
public class BaseActivity extends Activity {
  public void onCreate(Bundle bundle){
	  super.onCreate(bundle);
	  this.requestWindowFeature(Window.FEATURE_NO_TITLE);
  }
}
