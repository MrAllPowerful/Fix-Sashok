<?php
	if(!defined('INCLUDE_CHECK')) die("You don't have permissions to run this");
	/* ����� ����������� ������ ��� ���������� � ���������� ���������/�������/cms/��������
	'hash_md5' 			- md5 �����������
	'hash_authme'   	- ���������� � �������� AuthMe
	'hash_cauth' 		- ���������� � �������� Cauth
	'hash_xauth' 		- ���������� � �������� xAuth
	'hash_joomla' 		- ���������� � Joomla (v1.6- v1.7)
	'hash_ipb' 			- ���������� � IPB
	'hash_xenforo' 		- ���������� � XenForo
	'hash_wordpress' 	- ���������� � WordPress
	'hash_vbulletin' 	- ���������� � vBulletin
	'hash_dle' 			- ���������� � DLE
	'hash_drupal'     	- ���������� � Drupal (v.7)
	'hash_launcher'		- ���������� � ��������� sashok724 (����������� ����� �������)
	*/
	$crypt 				= 'hash_md5';
	
	$db_host			= 'localhost'; // Ip-����� MySQL
	$db_port			= '3306'; // ���� ���� ������
	$db_user			= 'root'; // ������������ ���� ������
	$db_pass			= 'root'; // ������ ���� ������
	$db_database		= 'webmcr-2.25'; //���� ������
	
	$db_table       	= 'accounts'; //������� � ��������������
	$db_group           = 'group'; //������� � ������� ������
	$db_columnId  		= 'id'; //������� � ID �������������
	$db_columnUser  	= 'login'; //������� � ������� �������������
	$db_columnPass  	= 'password'; //������� � �������� �������������
	$db_tableOther 		= 'xf_user_authenticate'; //�������������� ������� ��� XenForo, �� ��������
	$db_columnSesId	 	= 'session'; //������� � �������� �������������, �� ��������
	$db_columnServer	= 'server'; //������� � ��������� �������������, �� �������e
	$db_columnSalt  	= 'members_pass_salt'; //������������� ��� IPB � vBulletin: , IPB - members_pass_salt, vBulletin - salt
    $db_columnIp  		= 'ip'; //������� � IP �������������
	
	$db_columnDatareg   = 'create_time'; // ������� ���� �����������
	$db_columnMail      = 'email'; // ������� mail

	$banlist            = 'banlist'; //������� ������� Ultrabans
	$noactive           = '1'; //����� ������ �� ��������������
	
	$useban             =  true; //�� �� �� ������� = ��� � ��������, Ultrabans ������
	$useactivate        =  false; //��������� �������� �� mail
	$useantibrut        =  true; //������ �� ������ �������� ������ (����� 1 ������ ��� ������������ ������)
	
	$masterversion  	= 'final_RC4'; //������-������ ��������
	$protectionKey		= '1234567890'; //���� ������ ������. ������ ��� �� ��������.

//========================= ��������� �� =======================//	

	$db_columnMoney		= 'realmoney'; //������� � ��������
	
	$db_tableMoneyKeys  = 'sashok724_launcher_keys'; //������� � �������
	$db_columnKey		= 'key'; 	//������� � �������
	$db_columnAmount	= 'amount'; //������� � ������ ������
	
	$uploaddirs = 'MinecraftSkins';  //����� ������
	$uploaddirp = 'MinecraftCloaks'; //����� ������
	
	$usePersonal 		=  true; //������������ ������ �������
	$canUploadSkin		=  true; //����� �� �������� �����
	$canUploadCloak		=  true; //����� �� �������� �����
	$canBuyVip			=  true; //����� �� �������� VIP
	$canBuyPremium		=  true; //����� �� �������� Premium
	$canBuyUnban		=  true; //����� �� �������� ������
	$canActivateVaucher =  true; //����� �� ������������ ������
	$canExchangeMoney   =  true; //����� �� ���������� Realmoney -> IConomy
	$canUseJobs			=  true; //����� �� ������������ ������
	$usecheck			=  false; //����� �� ������������ ����������� � ��������
	
	$cloakPrice			=  0;   //���� ����� (� ������)
	$vipPrice			=  100;  //���� ���� (� ���/���)
	$premiumPrice		=  250;  //���� �������� (� ���/���)
	$unbanPrice			=  150;  //���� ������� (� ������)
	
	$initialIconMoney	=  30;  //������� ����� ������ ��� ����������� � IConomy
	$exchangeRate		=  200; //���� ������ Realmoney -> IConomy
	
	//��� ��� ���� - �� �������!
	try {
		$db = new PDO("mysql:host=$db_host;port=$db_port;dbname=$db_database", $db_user, $db_pass);
		$db->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
		$db->exec("set names utf8");
	} catch(PDOException $e) {
		die("errorsql".$logger->WriteLine($log_date.$pe));  //вывод ошибок MySQL в m.log
	}
?>
