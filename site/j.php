<?php
	define('INCLUDE_CHECK',true);
	include("connect.php");
	include_once("loger.php");
	if (($_SERVER['REQUEST_METHOD'] == 'POST' ) && (stripos($_SERVER["CONTENT_TYPE"], "application/json") === 0)) {
		$json = json_decode($HTTP_RAW_POST_DATA);
		
	}
    
	@$md5 = $json->selectedProfile; @$sessionid = @$json->accessToken; @$serverid = $json->serverId;
	$bad = array('error' => "Bad login",'errorMessage' => "Bad login");
	

	try {
		if (!preg_match("/^[a-zA-Z0-9_-]+$/", $md5) || !preg_match("/^[a-zA-Z0-9:_-]+$/", $sessionid) || !preg_match("/^[a-zA-Z0-9_-]+$/", $serverid)){
			exit(json_encode($bad));
		}

		$stmt = $db->prepare("Select md5,$db_columnUser From $db_table Where md5= :md5 And $db_columnSesId= :sessionid");
		$stmt->bindValue(':md5', $md5);
		$stmt->bindValue(':sessionid', $sessionid);
		$stmt->execute();
		$stmt->bindColumn($db_columnUser, $realUser);
		$stmt->bindColumn('md5', $realmd5);
		$stmt->fetch();
		$ok = array('id' => $realmd5, 'name' => $realUser);
		if($realmd5 == $md5)
		{
			$stmt = $db->prepare("Update $db_table SET $db_columnServer= :serverid Where $db_columnSesId = :sessionid And md5 = :md5");
			$stmt->bindValue(':md5', $md5);
			$stmt->bindValue(':sessionid', $sessionid);
			$stmt->bindValue(':serverid', $serverid);
			$stmt->execute();
			if($stmt->rowCount() == 1) echo json_encode($ok);
			else exit(json_encode($bad));
		}
		else exit(json_encode($bad));
	} catch(PDOException $pe) {
			die("errorsql".$logger->WriteLine($log_date.$pe));  //вывод ошибок MySQL в m.log
	}
?>
