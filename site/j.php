<?php
	define('INCLUDE_CHECK',true);
	include("connect.php");
	include("loger.php");
	if (($_SERVER['REQUEST_METHOD'] == 'POST' ) && (stripos($_SERVER["CONTENT_TYPE"], "application/json") === 0)) {
		$json = json_decode($HTTP_RAW_POST_DATA);
		
	}

	@$aT = $json->accessToken; @$sP = @$json->selectedProfile; @$sI = $json->serverId;
	@$user                      = $aT;
    @$sessionid                 = $sP;
    @$serverid                  = $sI;

	$bad = array('error' => "Bad login",'errorMessage' => "Bad login");
	$ok = array('id' => $user);

	try {
		if (!preg_match("/^[a-zA-Z0-9_-]+$/", $user) || !preg_match("/^[a-zA-Z0-9:_-]+$/", $sessionid) || !preg_match("/^[a-zA-Z0-9_-]+$/", $serverid)){
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
			if($stmt->rowCount() == 1) echo echo json_encode($ok);
			else exit(json_encode($bad));
		}
		else exit(json_encode($bad));
	} catch(PDOException $pe) {
			die("Ошибка".$logger->WriteLine($log_date.$pe));  //вывод ошибок MySQL в m.log
	}
?>
