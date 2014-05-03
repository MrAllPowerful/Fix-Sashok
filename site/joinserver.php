<?php
	define('INCLUDE_CHECK',true);
	include("connect.php");
    include_once("loger.php");
	@$sess       = $_GET['sessionId'];
    @$sessionid  = str_replace('%3A', ':', $sess);
    @$user       = $_GET['user'];
    @$serverid   = $_GET['serverId'];
	
	try {
		if (sizeof($_GET)!=3 || empty ( $_GET['sessionId'] ) ||  empty ( $_GET['user'] ) || empty ( $_GET['serverId'] ) || !preg_match("/^[a-zA-Z0-9_-]+$/", $user) || !preg_match("/^[a-zA-Z0-9:_-]+$/", $sessionid) || !preg_match("/^[a-zA-Z0-9_-]+$/", $serverid)){
          exit ("Bad login");
		}

		$stmt = $db->prepare("Select $db_columnUser From $db_table Where $db_columnSesId= :sessionid And $db_columnUser= :user");
		$stmt->bindValue(':user', $user);
		$stmt->bindValue(':sessionid', $sessionid);
		$stmt->execute();
		$result = $stmt->fetchColumn();
		if($result == $user)
		{
			$stmt = $db->prepare("Update $db_table SET $db_columnServer= :serverid Where $db_columnSesId= :sessionid And $db_columnUser= :user");
			$stmt->bindValue(':user', $user);
			$stmt->bindValue(':sessionid', $sessionid);
			$stmt->bindValue(':serverid', $serverid);
			$stmt->execute();
			if($stmt->rowCount() == 1) echo "OK";
			else echo "Bad login";
		}
		else echo "Bad login";
	} catch(PDOException $pe) {
			die("Ошибка".$logger->WriteLine($log_date.$pe));  //вывод ошибок MySQL в m.log
	}
?>
