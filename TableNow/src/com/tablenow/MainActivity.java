package com.tablenow;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

//test for john
// i hate git in general
public class MainActivity extends Activity {
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void to_signUp(View V){
		startActivity(new Intent(MainActivity.this, Sign_up.class));
	}
	
	public void to_restaurant(View V){
		startActivity(new Intent(MainActivity.this, Editor.class));
	}
	
	public void to_user(View V){
		startActivity(new Intent(MainActivity.this, User.class));
	}

}
