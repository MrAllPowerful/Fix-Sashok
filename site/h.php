<?php
    error_reporting(0);
	define('INCLUDE_CHECK',true);
	include ("connect.php");
	include("loger.php");
	@$user     = $_GET['username'];
    @$serverid = $_GET['serverId'];

	$bad = array('error' => "Bad login",'errorMessage' => "Bad login");
	try {
		if (!preg_match("/^[a-zA-Z0-9_-]+$/", $user) || !preg_match("/^[a-zA-Z0-9_-]+$/", $serverid)){
			exit(json_encode($bad));
		}
		$logger->WriteLine($user.' '.$serverid);
		
		$stmt = $db->prepare("SELECT $db_columnUser,md5 FROM $db_table WHERE $db_columnUser = :login and $db_columnServer = :serverid");
		$stmt->bindValue(':login', $user);
		$stmt->bindValue(':serverid', $serverid);
		$stmt->execute();
		$stmt->bindColumn($db_columnUser, $realUser);
		$stmt->bindColumn('md5', $md5);
		$stmt->fetch();
		if($user == $realUser)
		{
			$time = time();
			$base64 = '{"timestamp":'.$time.'","profileId":"'.$md5.'","profileName":"'.$realUser.'","textures":{"SKIN":{"url":"http://alexandrage.dyndns.org/site/MinecraftSkins/'.$realUser.'.png"}}}';
            echo '{"id":"'.$md5.'","name":"'.$realUser.'","properties":[{"name":"textures","value":"'.base64_encode($base64).'","signature":""}]}';
		}
		else exit(json_encode($bad));

	} catch(PDOException $pe) {
			die("errorsql".$logger->WriteLine($log_date.$pe));  //вывод ошибок MySQL в m.log
	}
?>
