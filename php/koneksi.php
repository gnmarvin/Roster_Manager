<?php
$servername = "127.0.0.1";			//server IP address
$username = "root";					//username phpmyadmin
$password = "";						//password phpmyadmin
$dbname = "roster_management";		//database name

$conn = new mysqli($servername, $username, $password, $dbname);		//koneksi ke server

if ($conn->connect_error) {			//if koneksi gagal
		die("Connection failed: " . $conn->connect_error);
	}

//$sql = mysqli_query($conn,"SELECT Member_ID, Nama_depan FROM member"); 

//$json = array();
//while ($row = mysqli_fetch_assoc($sql)) {
	//$json []= $row;
//}

//echo  json_encode($json);

//$conn->close();	
?>

</body>
</html>

