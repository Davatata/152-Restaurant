package com.tablenow;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class User_results extends ListActivity {

	String[] restaurants = {
			"Bar",
			"BBQ",
			"Home Town"
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, restaurants));
	}

	public void onListItemClick(ListView list, View v, int position, long id){
		
		super.onListItemClick(list, v, position, id);
		String storyName = restaurants[position];		
		try{
			Class selected = Class.forName("com.example.eventure." + storyName);
			Intent selectedIntent = new Intent(this, selected);
			startActivity(selectedIntent);
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_results, menu);
		return true;
	}

}
