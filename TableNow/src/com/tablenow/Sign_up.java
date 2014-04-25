package com.tablenow;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


public class Sign_up extends Activity {
	// Progress Dialog
    private ProgressDialog pDialog;
 
    JSONParser jsonParser = new JSONParser();
    EditText inputFirstName;
    EditText inputLastName;
    EditText inputEmail;
    EditText inputPassowrd;
    String temp = "";
    
	// url to create new product
    
    private static String url_create_User = "http://50.161.166.152/restauraunt/create_product.php";
 
    // JSON Node names
    private static final String TAG_SUCCESS = "success";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);
		
		// Edit Text
		inputFirstName = (EditText) findViewById(R.id.first_name_editText);
		inputLastName = (EditText) findViewById(R.id.last_name_editText);
		inputEmail = (EditText) findViewById(R.id.email_editText);
		inputPassowrd = (EditText) findViewById(R.id.password_editText);
		
        // Create button
        Button joinNowBtn = (Button) findViewById(R.id.join_button);
 
        // button click event
        joinNowBtn.setOnClickListener(new View.OnClickListener() {
 
            @Override
            public void onClick(View view) {
                // creating new product in background thread         
            	
            	if(temp == ""){
            		AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(
            		        Sign_up.this);

            		// Setting Dialog Title
            		alertDialog2.setTitle("Warning");

            		// Setting Dialog Message
            		alertDialog2.setMessage("Check a box fool!");

            		// Setting Positive "Yes" Btn
            		alertDialog2.setPositiveButton("OK",
            		        new DialogInterface.OnClickListener() {
            		            public void onClick(DialogInterface dialog, int which) {
            		                // Write your code here to execute after dialog
            		                Toast.makeText(getApplicationContext(),
            		                        "You clicked on OK", Toast.LENGTH_SHORT)
            		                        .show();
            		            }
            		        });

            		// Showing Alert Dialog
            		alertDialog2.show();
            	}
            	
            	else{
            		new CreateNewUser().execute();
                    startActivity(new Intent(Sign_up.this, MainActivity.class));            		
            	}
            }
        });
		
		// Show the Up button in the action bar.
		setupActionBar();
	}

	public void check_res(View view){
		boolean checked = ((CheckBox) view).isChecked();
		boolean user_check = ((CheckBox) findViewById(R.id.checkBox_user)).isChecked();
		if(!checked){
				((CheckBox) findViewById(R.id.checkBox_res)).setChecked(false);
				temp = "";
		}
		else{
			if(user_check){
				((CheckBox) findViewById(R.id.checkBox_user)).setChecked(false);
			}
			((CheckBox) view).setChecked(true);
			temp = "Res";
		}
	}
	
	public void check_user(View view){
		boolean checked = ((CheckBox) view).isChecked();
		boolean res_check = ((CheckBox) findViewById(R.id.checkBox_res)).isChecked();
		if(!checked){
				((CheckBox) findViewById(R.id.checkBox_user)).setChecked(false);
				temp = "";	
		}
		else{
			if(res_check){
				((CheckBox) findViewById(R.id.checkBox_res)).setChecked(false);
			}
			((CheckBox) view).setChecked(true);
			temp = "User";
		}
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
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sign_up, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	/**
     * Background Async Task to Add new user
     * */
    class CreateNewUser extends AsyncTask<String, String, String> {
 
        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Sign_up.this);
            pDialog.setMessage("Creating Product..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
 
        /**
         * Adding new user
         * */        
        protected String doInBackground(String... args) {
            String firstName = inputFirstName.getText().toString();
            String lastName = inputLastName.getText().toString();
            String userEmail = inputEmail.getText().toString();
            String userPassword = inputPassowrd.getText().toString();
            String auth = temp;
 
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("firstName", firstName));
            params.add(new BasicNameValuePair("lastName", lastName));
            params.add(new BasicNameValuePair("userEmail", userEmail));
            params.add(new BasicNameValuePair("userPassowrd", userPassword));
            params.add(new BasicNameValuePair("authority", auth));
 
            // getting JSON Object
            // Note that create product url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest(url_create_User,
                    "POST", params);
 
            // check log cat for response
            Log.d("Create Response", json.toString());
 
            // check for success tag
            try {
                int success = json.getInt(TAG_SUCCESS);
 
                if (success == 1) {
                    // successfully created user
                    // notify previous activity by sending code 100
                    Intent i = getIntent();
                    // send result code 100 to notify about product saving
                    setResult(100, i);
                    finish();
 
                    // closing this screen
                    finish();
                } else {
                    // failed to create user
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
 
            return null;
        }
        
        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            pDialog.dismiss();
        }
    }
}
