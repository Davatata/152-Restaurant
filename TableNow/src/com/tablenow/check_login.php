<?php
 
/*
 * Following code will create a new product row
 * All product details are read from HTTP Post Request
 */
 
// array for JSON response
$response = array();

$test = urldecode($_POST['firstName']);


// check for required fields
if (isset($_GET['userEmail']) && isset($_GET['userPassword'])) {
    
    	$email = urldecode($_POST['userEmail']);
	$password = urldecode($_POST['userPassword']);
	
 -
    // include db connect class
    require_once __DIR__ . '/db_connect.php';
 
    // connecting to db
    $d = new DB_CONNECT();
 
    // mysql inserting a new row
    $result1 = mysql_query("SELECT userEmail FROM peeps WHERE userEmail = $email and auth = 'User'");
    $result2 = mysql_query("SELECT userPassword FROM peeps WHERE userEmail = $email and auth = 'User'");
    $result3 = mysql_query("SELECT userEmail FROM peeps WHERE userEmail = $email and auth = 'Res'");
    $result4 = mysql_query("SELECT userPassword FROM peeps WHERE userEmail = $email and auth = 'Res'");
    
 
    // check if row inserted or not
    if (!empty($result1) && !empty(result2)) {
        // successfully inserted into database
        $response["success"] = 1;
        $response["message"] = "User successfully created.";
 
        // echoing JSON response
        echo json_encode($response);

    else if(!empty($result3) && !empty(result4))
	{
	   $response["success"] = 2;
           $response["message"] = "Restauraunt successfully created.";


	}
    } else {
        // failed to insert row
        $response["success"] = 0;
        $response["message"] = "An error has occurred.";
 
        // echoing JSON response
        echo json_encode($response);
    }
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";
 
    // echoing JSON response
    echo json_encode($response);
}

?>