<?php
define('INCLUDE_CHECK',true);
include("connect.php");
//include("loger.php");
if (($_SERVER['REQUEST_METHOD'] == 'POST' ) && (stripos($_SERVER["CONTENT_TYPE"], "application/json") === 0)) {
    $json = json_decode($HTTP_RAW_POST_DATA);
    
}

@$aT = $json->accessToken; @$sP = @$json->selectedProfile; @$sI = $json->serverId;
@$user                      = mysql_real_escape_string($aT);
@$sessionid                 = mysql_real_escape_string($sP);
@$serverid                  = mysql_real_escape_string($sI);
//$logger->WriteLine($user.' '.$sessionid.' '.$serverid);

$bad = array('error' => "Bad login",'errorMessage' => "Bad login");
$ok = array('id' => $user);

if (!preg_match("/^[a-zA-Z0-9_-]+$/", $user) || !preg_match("/^[a-zA-Z0-9:_-]+$/", $sessionid) || !preg_match("/^[a-zA-Z0-9_-]+$/", $serverid)){
	exit(json_encode($bad));
}
	
	$query = mysql_query("Select $db_columnUser From $db_table Where $db_columnUser='$user'") or die ("Ошибка");
	$row = mysql_fetch_assoc($query);
	$realUser = $row[$db_columnUser];

	if ($user !== $realUser)
    {
		exit(json_encode($bad));
    }
	
	$result = mysql_query("Select $db_columnUser From $db_table Where $db_columnSesId='$sessionid' And $db_columnUser='$user' And $db_columnServer='$serverid'") or die ("Ошибка");
	if(mysql_num_rows($result) == 1) echo json_encode($ok);
	else
	{
		$result = mysql_query("Update $db_table SET $db_columnServer='$serverid' Where $db_columnSesId='$sessionid' And $db_columnUser='$user'") or die ("Ошибка");
		if(mysql_affected_rows() == 1) echo json_encode($ok);
		else exit(json_encode($bad));
	}
?>
