<?php
    error_reporting(0);
	define('INCLUDE_CHECK',true);
	include ("connect.php");
	@$user     = $_GET['username'];
    @$serverid = $_GET['serverId'];
	
	$bad = array('error' => "Bad login",'errorMessage' => "Bad login");
	$ok = array('id' => $user);
	try {
		if (!preg_match("/^[a-zA-Z0-9_-]+$/", $user) || !preg_match("/^[a-zA-Z0-9_-]+$/", $serverid)){
			exit(json_encode($bad));
		}
		
		$stmt = $db->prepare("Select $db_columnUser From $db_table Where $db_columnUser= :user");
		$stmt->bindValue(':user', $user);
		$stmt->execute();
		$row = $stmt->fetch(PDO::FETCH_ASSOC);
		$realUser = $row[$db_columnUser];

		if ($user !== $realUser)
		{
			exit(json_encode($bad));
		}	
		
		$stmt = $db->prepare("Select $db_columnUser From $db_table Where $db_columnUser= :user And $db_columnServer= :serverid");
		$stmt->bindValue(':user', $user);
		$stmt->bindValue(':serverid', $serverid);
		$stmt->execute();

        $result = $stmt->fetchColumn();
		if($result == $user)
		{
			echo json_encode($ok);
		}
		else exit(json_encode($bad));

	} catch(PDOException $pe) {
			die("Ошибка".$logger->WriteLine($log_date.$pe));  //вывод ошибок MySQL в m.log
	}
?>
