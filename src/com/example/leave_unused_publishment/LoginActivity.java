package com.example.leave_unused_publishment;
import java.security.NoSuchAlgorithmException;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.leave_unused_publishment.Common.Global;
import com.example.leave_unused_publishment.network.Transfer;
import com.example.leave_unused_publishment.network.TransferListener;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
public class LoginActivity extends BaseActivity implements OnClickListener{
	    private TextView registeracc;
	    private Button login;
	    private String username;
	    private String password;
	    private EditText account;
	    private EditText pwd;
	    
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			SharedPreferences sp = getSharedPreferences("publishconfig", MODE_PRIVATE);
			String uname = sp.getString("username", null);
			String pword = sp.getString("password", null);
			String token = sp.getString("token", null);
			Log.e("uname pw",uname+" "+pword+" "+token);
			if(uname!=null&&pword!=null&&token!=null){
			  startActivity(new Intent(LoginActivity.this,MainActivity.class));
			  Global.token=token;
			  Global.username=uname;
			  Global.password=pword;
			  finish();
			}
			setContentView(R.layout.base_login_layout);
			init();
		}
		
		public void init(){
			account=(EditText)findViewById(R.id.account);
			pwd=(EditText)findViewById(R.id.password);
			registeracc=(TextView)findViewById(R.id.registeraccount);
			registeracc.setOnClickListener(this);
			login=(Button)findViewById(R.id.login_btn);
			login.setOnClickListener(this);
			Global.handler = new Handler(){
				public void handleMessage(Message msg){
				 	if(msg.what==0){
				 		String m = msg.getData().getString("tips");
				 		Toast.makeText(LoginActivity.this,m,Toast.LENGTH_SHORT).show();
				 	}
				}
			};
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
				username = account.getText().toString();
				password = pwd.getText().toString();
			
				try {
					password = Global.getMD5(password);
					Log.e("username ",username+" "+password);
					Transfer.login(username, password, new TransferListener() {
						
						@Override
						public void onSucceed(JSONObject obj) {
							// TODO Auto-generated method stub
					       	Log.e("onsucceed",obj.toString());
					       	Message msg = Global.handler.obtainMessage();
					       	msg.what=0;
					       	try {
								Global.token=obj.getString("token");
								Log.e("token",Global.token);
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								Log.e("jer",e.toString());
								e.printStackTrace();
							}
					       	Bundle data = new Bundle();
					       	data.putString("tips", "µÇÂ½³É¹¦");
					       	msg.setData(data);
					       	Global.handler.sendMessage(msg);
					       	SharedPreferences sp = getSharedPreferences("publishconfig", MODE_PRIVATE);
					       	Editor editor = sp.edit();
					       	editor.putString("username", username);
					       	editor.putString("password", password);
					       	editor.putString("token", Global.token);
					       	editor.commit();
					       	startActivity(new Intent(LoginActivity.this,MainActivity.class));
						}
						
						@Override
						public void onFail(String desc) {
							// TODO Auto-generated method stub
							Message msg = Global.handler.obtainMessage();
					       	msg.what=0;
					       	Bundle data = new Bundle();
					       	data.putString("tips", "µÇÂ½Ê§°Ü");
					       	msg.setData(data);
					       	Global.handler.sendMessage(msg);
						}
					});
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				break;
			}
		}
		
}
