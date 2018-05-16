<?php
$servername = "127.0.0.1";
$username = "root";
$password = "";
$dbname = "roster_management";

$conn = new mysqli($servername, $username, $password, $dbname);

if ($conn->connect_error) {
		die("Connection failed: " . $conn->connect_error);
	}

$sql = mysqli_query($conn,"SELECT Member_ID, Nama_depan FROM member"); 

$json = array();
while ($row = mysqli_fetch_assoc($sql)) {
	$json []= $row;
}

echo  json_encode($json);

$conn->close();	
?>

</body>
</html>

