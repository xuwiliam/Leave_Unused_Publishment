package com.example.leave_unused_publishment;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

public class EditCommentActivity extends Activity{
  EditText editc;
  TextView certain;
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	setContentView(R.layout.editcomment);
	super.onCreate(savedInstanceState);
	init();
  }
  public void init(){
	editc=(EditText)findViewById(R.id.editcom);
	certain=(TextView)findViewById(R.id.certaintext);
	certain.setOnClickListener(new OnClickListener(){
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
		  String cont=editc.getText().toString();
		  Intent data=new Intent();
		  data.putExtra("comment", cont);
		  setResult(4,data);
		  finish();
		}
	});
  }
}
