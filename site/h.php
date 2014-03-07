<?php
    error_reporting(0);
	define('INCLUDE_CHECK',true);
	include ("connect.php");
	@$user = mysql_real_escape_string($_GET['username']);
	@$serverid = mysql_real_escape_string($_GET['serverId']);
	
	$bad = array('error' => "Bad login",'errorMessage' => "Bad login");
	$ok = array('id' => $user);
	
	if (!preg_match("/^[a-zA-Z0-9_-]+$/", $user) || !preg_match("/^[a-zA-Z0-9_-]+$/", $serverid)){
		exit(json_encode($bad));
    }

	$query = mysql_query("Select $db_columnUser From $db_table Where $db_columnUser='$user'") or die ("Ошибка");
	$row = mysql_fetch_assoc($query);
	$realUser = $row[$db_columnUser];

	if ($user !== $realUser)
    {
		exit(json_encode($bad));
    }	
	
	$result = mysql_query("Select $db_columnUser From $db_table Where $db_columnUser='$user' And $db_columnServer='$serverid'") or die (mysql_error());

	if(mysql_num_rows($result) == 1) echo json_encode($ok);
	else exit(json_encode($bad));
?>
