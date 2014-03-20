<?php
	define('INCLUDE_CHECK',true);
	include ("connect.php");
	@$user = $db->quote($_GET['user']);
        @$serverid = $db->quote($_GET['serverId']);
	
	try {
		if (!preg_match("/^[a-zA-Z0-9_-]+$/", $user) || !preg_match("/^[a-zA-Z0-9_-]+$/", $serverid)){
			echo "NO";	
			exit;
		}	
		$stmt = $db->prepare("Select $db_columnUser From $db_table Where $db_columnUser= :user");
		$stmt->bindValue(':user', $user);
		$stmt->execute();
		
		$row = $stmt->fetch(PDO::FETCH_ASSOC);
		$realUser = $row[$db_columnUser];

		if ($user !== $realUser) {
			exit ("NO");
		}
		
		$stmt = $db->prepare("Select $db_columnUser From $db_table Where $db_columnUser= :user And $db_columnServer= :serverid");
		$stmt->bindValue(':user', $user);
		$stmt->bindValue(':serverid', $serverid);
		$stmt->execute();

		if($stmt->fetchColumn() == 1) echo "YES";
		else echo "NO";
	} catch(PDOException $pe) {
		die("Ошибка".$logger->WriteLine($log_date.$pe));  //вывод ошибок MySQL в m.log
	}
?>
