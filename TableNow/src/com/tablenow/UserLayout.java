package com.tablenow;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;

public class UserLayout extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_layout);
		// Show the Up button in the action bar.
		setupActionBar();
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
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
        		        UserLayout.this);

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
        		                startActivity(new Intent(UserLayout.this, MainActivity.class));
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
	        	startActivity(new Intent(UserLayout.this, User_update.class));
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

}
