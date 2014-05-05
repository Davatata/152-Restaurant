package com.tablenow;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class User_results extends ListActivity {

	String[] restaurants = {
			"Test"
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, restaurants));
	}

	public void onListItemClick(ListView list, View v, int position, long id){
		startActivity(new Intent(User_results.this, UserLayout.class));
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.actions, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.action_sign_out:
	        	AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(
        		        User_results.this);

        		// Setting Dialog Title
        		alertDialog2.setTitle("Warning");

        		// Setting Dialog Message
        		alertDialog2.setMessage("Are You Sure You Want To Sign Out?");

        		// yes button
        		alertDialog2.setPositiveButton("Yes",
        		        new DialogInterface.OnClickListener() {
        		            public void onClick(DialogInterface dialog, int which) {
        		                // Write your code here to execute after dialog
        		                Toast.makeText(getApplicationContext(),
        		                        "You Have Signed Out", Toast.LENGTH_SHORT)
        		                        .show();
        		                startActivity(new Intent(User_results.this, MainActivity.class));
        		            }
        		        });
        		
        		// no button
        		alertDialog2.setNegativeButton("No", 
        				new DialogInterface.OnClickListener() {
        					public void onClick(DialogInterface dialog, int which) {
        						dialog.cancel();
        			}
        		});

        		// Showing Alert Dialog
        		alertDialog2.show();
	            return true;
	        case R.id.action_settings:
	        	startActivity(new Intent(User_results.this, User_update.class));
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }

	}

}
