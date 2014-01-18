<?php
	define('INCLUDE_CHECK',true);
	include ("connect.php");
	$user = mysql_real_escape_string($_GET['user']);
	$serverid = mysql_real_escape_string($_GET['serverId']);
	
	if (!preg_match("/^[a-zA-Z0-9_-]+$/", $user) || !preg_match("/^[a-zA-Z0-9_-]+$/", $serverid)){

		echo "NO";	
		
	exit;
    }	
    
    $query = mysql_query("Select $db_columnUser From $db_table Where $db_columnUser='$user'") or die ("Ошибка");
	$row = mysql_fetch_assoc($query);
	$realUser = $row[$db_columnUser];

	if ($user !== $realUser)
    {
    exit ("NO");
    }
	
	$result = mysql_query("Select $db_columnUser From $db_table Where $db_columnUser='$user' And $db_columnServer='$serverid'") or die (mysql_error());

	if(mysql_num_rows($result) == 1) echo "YES";
	else echo "NO";
?>