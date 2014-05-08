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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class login extends Activity {
	
	
	
		private ProgressDialog pDialog;
	 
	    JSONParser jsonParser = new JSONParser();
	    EditText inputEmail;
	    EditText inputPassword;
	    String temp = "";
	    String auth = "";
	    
	    
	    
	    private static String url_create_User = "http://50.161.166.152/Restaurant/check_login.php";
	   
	    private static final String TAG_SUCCESS = "success";
	    
	    protected void onCreate(Bundle savedInstanceState)
	    {
	    	super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main);
			
			inputEmail = (EditText) findViewById(R.id.EmailTextView);
			inputPassword = (EditText) findViewById(R.id.passwordEditText);
			
			Button loginButton = (Button) findViewById(R.id.logInButton);
			
			loginButton.setOnClickListener(new View.OnClickListener() {
				 
				 @Override
		            public void onClick(View view) {
		                // creating new product in background thread  
					 
					 	new login_User().execute();
			             
		            	
		            	if(temp.equals("res") && auth.equals("yes")){
		            		
		            		startActivity(new Intent(login.this,Editor.class));
		            	}
		            	
		            	else if(temp.equals("user") && auth.equals("yes"))
		            	{
		            		startActivity(new Intent(login.this,User.class));
		            	}
		            	
		            	else{
		            		
		            		AlertDialog.Builder alertDialog1 = new AlertDialog.Builder(
		            		        login.this);

		            		// Setting Dialog Title
		            		alertDialog1.setTitle("Error");

		            		// Setting Dialog Message
		            		alertDialog1.setMessage("log in unsuccessful");

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
		            		startActivity(new Intent(login.this, MainActivity.class));
		            		
		            	}
		            
		           
		            
	            
				
				
	            }
				 
			});
			
		}
	    

	       
		
	    
	    class login_User  extends AsyncTask<String, String, String>
	    {
	    	
	    	
	    	 @Override
	         protected void onPreExecute() {
	             super.onPreExecute();
	             pDialog = new ProgressDialog(login.this);
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
	            
	           
	            
	            try{
	            	
	            	String data = URLEncoder.encode("userEmail", "UTF-8") + "=" + URLEncoder.encode(userEmail, "UTF-8");
	            	data += "&" + URLEncoder.encode("userPassword", "UTF-8") + "=" + URLEncoder.encode(userPassword, "UTF-8");
	            	
	            	
	            	URL url = new URL(url_create_User);
	            	URLConnection con = url.openConnection();
	            	con.setDoOutput(true);
	            	OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
	            	writer.write(data);
	            	writer.flush();
	            	
	            	
	            	reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
	            	StringBuilder sb = new StringBuilder();
	            	String line = "";
	            	
	            	while ((line = reader.readLine()) != null)
	            	{
	            		sb.append(line + "\n");
	            	}
	            	
	                response = sb.toString();
	                reader.close();
	            	
	            }
	            
	            
	            catch (Exception ex)
	            {
	            	Log.e("Error", ex.toString());
	            }
	            
	            System.out.println(response);
	            
	            
	            	
	            List<NameValuePair> params = new ArrayList<NameValuePair>();
	            JSONObject json = jsonParser.makeHttpRequest(url_create_User,"POST",params);
	            Log.d("All Products: ",json.toString());
	            
	            try
	            {
	            	int success = json.getInt(TAG_SUCCESS);
	            	
	            	if(success ==1)
	            	{
	            		auth = "yes";
	            	}
	            	
	            	else
	            	{
	            		auth = "no";
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
