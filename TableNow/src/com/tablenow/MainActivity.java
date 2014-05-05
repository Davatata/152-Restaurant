package com.tablenow;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//test for john
// i hate git in general
public class MainActivity extends Activity {
	
	
	private ProgressDialog pDialog;
	 
    JSONParser jsonParser = new JSONParser();
    EditText inputEmail;
    EditText inputPassword;
    String temp = "";
    String auth = "";
    
    private static String url_create_User = "http://50.161.166.152/Restaurant/check_login.php";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_STATUS = "status";
	 
    
	@Override
	public void onCreate(Bundle savedInstanceState) {
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
	
	public void to_restaurant(View V)
	{
		startActivity(new Intent(MainActivity.this,Editor.class));
	}
	
	public void to_user(View V)
	{
		startActivity(new Intent(MainActivity.this,User.class));
	}
	
	
	
	public void login(View V)
	{
		inputEmail = (EditText) findViewById(R.id.EmailEditText);
		inputPassword = (EditText) findViewById(R.id.passwordEditText);
		
		Button loginButton = (Button) findViewById(R.id.logInButton);
		
		loginButton.setOnClickListener(new View.OnClickListener() {
			 
			 @Override
	            public void onClick(View view) {
	                // creating new product in background thread  
				 
				 	new login_User().execute();
		             
	            	
	            	
	            	if(temp.equals("Res") && auth.equals("yes")){
	            		
	            		startActivity(new Intent(MainActivity.this,Editor.class));
	            	}
	            	
	            	else if(temp.equals("User") && auth.equals("yes"))
	            	{
	            		startActivity(new Intent(MainActivity.this,User.class));
	            	}
	            	
	            	else{
	            		
	            		AlertDialog.Builder alertDialog1 = new AlertDialog.Builder(
	            		        MainActivity.this);

	            		// Setting Dialog Title
	            		alertDialog1.setTitle("Error");

	            		// Setting Dialog Message
	            		alertDialog1.setMessage("log was unsuccessful");

	            		// Setting Positive "Yes" Btn
	            		alertDialog1.setPositiveButton("OK",
	            		        new DialogInterface.OnClickListener() {
	            		            public void onClick(DialogInterface dialog, int which) {
	            		                // Write your code here to execute after dialog
	            		                Toast.makeText(getApplicationContext(),
	            		                        "You clicked on OK", Toast.LENGTH_SHORT)
	            		                        .show();
	            		            }
	            		        });

	            		// Showing Alert Dialog
	            		alertDialog1.show();
	            		startActivity(new Intent(MainActivity.this, MainActivity.class));
	            		
	            	}
	            
	           
	            
            
			
			
            }
			 
		});
	}
	
	  class login_User  extends AsyncTask<String, String, String>
	    {
	    	
	    	
	    	 @Override
	         protected void onPreExecute() {
	             super.onPreExecute();
	             pDialog = new ProgressDialog(MainActivity.this);
	             pDialog.setMessage("verifying login information..");
	             pDialog.setIndeterminate(false);
	             pDialog.setCancelable(true);
	             pDialog.show();
	         }
	    	
	    	
	    	
	    	
	    	
	    	
			@Override
			protected String doInBackground(String... arg0) {
				
				String userEmail = inputEmail.getText().toString();
	            String userPassword = inputPassword.getText().toString();
	            
	           
	            
	            String response = "";
	            BufferedReader reader;
	            
	           
	           
	            
	            List<NameValuePair> params = new ArrayList<NameValuePair>();
	            params.add(new BasicNameValuePair("userEmail", userEmail));
	            params.add(new BasicNameValuePair("userPassword", userPassword));
	            JSONObject json = jsonParser.makeHttpRequest(url_create_User,"POST",params);
	            
	            Log.d("All Products: ",json.toString());
	            
	            try
	            {
	            	int success = json.getInt(TAG_SUCCESS);
	            	int status = json.getInt(TAG_STATUS);
	            	
	            	if(success == 1 && status == 0 )
	            	{
	            		auth = "yes";
	            		temp = "User";
	            	}
	            	
	            	else if(success == 1 && status == 1)
	            	{
	            		auth ="yes";
	            		temp = "Res";
	            	}
	            	
	            	else
	            	{
	            		auth = "no";
	            		temp = "fail";
	            	}
	            	
	            	
	            }
	            
	            catch (JSONException e)
	            {
	            	e.printStackTrace();
	            }
	            
	            
				
				
	            Log.e("Response", response);
	            return response;
	            
	            
			}
			
			protected void onPostExecute(String file_url) {
	            // dismiss the dialog once done
	            pDialog.dismiss();
	        }
	    	
	    }
	


}
	