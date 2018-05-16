<?php

if ($_SERVER['REQUEST_METHOD']=='POST') {			//tipe request ke server
	//variable yang dikirim ke server
	$event_id = $_POST['event_id'];					
	$event_name = $_POST['event_name'];
	$cod_id = $_POST['cod_id'];
	$team_id = $_POST['team_id'];
	//query insert 
	$sql = "INSERT INTO event (Event_ID, Event_name, COD_ID, Team_ID) VALUES ('$event_id', 'event_name', 'cod_id', 'team_id')";
	//import file koneksi php
	required_once('koneksi.php');
	//eksekusi query ke database
	if (mysqli_query($conn, $sql)) {
		echo "Berhasil";
	}else {
		echo "Gagal";
	}

	mysqli_close($conn);	//tutup koneksi
}