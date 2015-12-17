package com.example.leave_unused_publishment;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
public class LoginActivity extends Activity implements OnClickListener{
	    private TextView registeracc;
	    private Button login;
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.base_login_layout);
			init();
		}
		public void init(){
			registeracc=(TextView)findViewById(R.id.registeraccount);
			registeracc.setOnClickListener(this);
			login=(Button)findViewById(R.id.login_btn);
			login.setOnClickListener(this);
		}

		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.main, menu);
			return true;
		}

		@Override
		public boolean onOptionsItemSelected(MenuItem item) {
			// Handle action bar item clicks here. The action bar will
			// automatically handle clicks on the Home/Up button, so long
			// as you specify a parent activity in AndroidManifest.xml.
			int id = item.getItemId();
			if (id == R.id.action_settings) {
				return true;
			}
			return super.onOptionsItemSelected(item);
		}
		public void onClick(View v){
			int id = v.getId();
			switch(id){
			case R.id.registeraccount:
				startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
				break;
			case R.id.login_btn:
				startActivity(new Intent(LoginActivity.this,MainActivity.class));
				break;
			}
		}
		
}
