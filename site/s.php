<?php
define('INCLUDE_CHECK',true);
include("connect.php");
@$get = $_GET['user'];
list($md5) = explode('?', $get);

$stmt = $db->prepare("SELECT $db_columnUser,md5 FROM $db_table WHERE md5= :md5");
$stmt->bindValue(':md5', $md5);
$stmt->execute();
$stmt->bindColumn($db_columnUser, $realUser);
$stmt->fetch();
$time = time();
$base64 = '{"timestamp":1397399426425,"profileId":"064e1199393744c69c92854f8daa0e01","profileName":"zenit_","textures":{"SKIN":{"url":"http://alexandrage.dyndns.org/site/MinecraftSkins/zenit.png"}}}';
echo '{"id":"064e1199393744c69c92854f8daa0e01","name":"zenit_","properties":[{"name":"textures","value":"'.base64_encode($base64).'","signature":""}]}';