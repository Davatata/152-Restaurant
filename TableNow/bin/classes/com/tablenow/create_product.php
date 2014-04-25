<?php
 
/*
 * Following code will create a new product row
 * All product details are read from HTTP Post Request
 */
 
// array for JSON response
$response = array();
 
// check for required fields
if (isset($_POST['firstName']) && isset($_POST['lastName']) && isset($_POST['userEmail']) && isset($_POST['userPassword'])) {
    $first_name = $_POST['firstName'];
    $last_name= $_POST['lastName'];
    $email = $_POST['userEmail'];
	$password = $_POST['userPassword'];
	
 -
    // include db connect class
    require_once __DIR__ . '/db_connect.php';
 
    // connecting to db
    $d = new DB_CONNECT();
 
    // mysql inserting a new row
    $result = mysql_query("INSERT INTO peeps(firstName, lastName, userEmail, userPassword) VALUES('$first_name', '$last_name', '$email', '$password')");
 
    // check if row inserted or not
    if ($result) {
        // successfully inserted into database
        $response["success"] = 1;
        $response["message"] = "User successfully created.";
 
        // echoing JSON response
        echo json_encode($response);
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