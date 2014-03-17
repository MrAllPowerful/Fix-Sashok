<?php
	define('INCLUDE_CHECK',true);
	include("connect.php");
    include("loger.php");
	$sess = $_GET['sessionId'];
	$sessionid   = str_replace('%3A', ':', $sess);
	$user = $_GET['user']);
	$serverid = $_GET['serverId'];
	
	try {
		if (sizeof($_GET)!=3 || empty ( $_GET['sessionId'] ) ||  empty ( $_GET['user'] ) || empty ( $_GET['serverId'] ) || !preg_match("/^[a-zA-Z0-9_-]+$/", $user) || !preg_match("/^[a-zA-Z0-9:_-]+$/", $sessionid) || !preg_match("/^[a-zA-Z0-9_-]+$/", $serverid)){
			echo "Bad login";
			exit;
		}
		
		$stmt = $db->prepare("Select $db_columnUser From $db_table Where $db_columnUser= :user");
		$stmt->bindValue(':user', $user);
		$stmt->execute();
		
		$row = $stmt->fetch(PDO::FETCH_ASSOC);
		$realUser = $row[$db_columnUser];

		if ($user !== $realUser)
		{
			exit ("Bad login");
		}

		$stmt = $db->prepare("Select $db_columnUser From $db_table Where $db_columnSesId= :sessionid And $db_columnUser= :user And $db_columnServer= :serverid");
		$stmt->bindValue(':user', $user);
		$stmt->bindValue(':sessionid', $sessionid);
		$stmt->bindValue(':serverid', $serverid);
		$stmt->execute();
		
		if($stmt->fetchColumn() == 1) echo "OK";
		else {
			$stmt = $db->prepare("Update $db_table SET $db_columnServer= :serverid Where $db_columnSesId= :sessionid And $db_columnUser= :user");
			$stmt->bindValue(':user', $user);
			$stmt->bindValue(':sessionid', $sessionid);
			$stmt->bindValue(':serverid', $serverid);
			$stmt->execute();
			if($stmt->rowCount() == 1) echo "OK";
			else echo "Bad login";
		}
	} catch(PDOException $pe) {
			die("Ошибка".$logger->WriteLine($log_date.$pe));  //вывод ошибок MySQL в m.log
	}
?>