/*launcher, сборка за 30.10.2012, индекс: 121 */

package net.launcher.run;

public class Settings
{
	/** Настройка заголовка лаунчера */
	public static final String 		title		 	 = "Launcher"; //Заголовок лаунчера
	public static final String 		titleInGame  	 = "Minecraft"; //Заголовок лаунчера после авторизации
	public static final String 		basedir			 = "AppData"; //Родительская папка для Minecraft (только для Windows) [ProgramFiles, AppData]
	public static final String 		baseconf		 = ".voxelaria"; //Папка с файлом конфигурации
	public static final String		pathconst		 = ".voxelaria/%SERVERNAME%"; //Конструктор пути к папке с MC
	public static final String      skins            = "MinecraftSkins/"; //Папка скинов
	public static final String      cloaks           = "MinecraftCloaks/"; //Папка плащей
	/** Параметры подключения */
	public static final String 	domain	 	 	 = "alexandrage.dyndns.org";//Домен сайта
	public static final String  siteDir		  	 = "site";//Папка с файлами лаунчера на сайте
	public static final String  updateFile		 = "http://alexandrage.dyndns.org/site/Launcher.jar";//Ссылка на файл обновления лаунчера
	public static final String 	buyVauncherLink  = "http://plati.ru/"; //Ссылка на страницу покупки ваучеров

	/** Для одиночной игры */
	public static final String  defaultUsername  = "player"; //Имя пользователя для одиночной игры
	public static final String  defaultSession   = "123456"; //Номер сессии для одиночной игры

	/** Настройка серверов */
	public static final String[] servers =
	{
		"voxelaria, localhost, 25565, net.minecraft.launchwrapper.Launch",
		"v, localhost, 25565, net.minecraft.client.Minecraft",
	};

	/** Настройка панели ссылок **/
	public static final String[] links = 
	{
		//Для отключения добавьте в адрес ссылки #
		" Регистрация ::http://",
	};

	/** Настройки структуры лаунчера */
	
	public static boolean useMulticlient		 =  true; //Использовать функцию "по клиенту на сервер"
	public static boolean useStandartWB			 =  true; //Использовать стандартный браузер для открытия ссылок
	public static boolean usePersonal		 	 =  true; //Использовать Личный кабинет
	public static boolean customframe 			 =  true; //Использовать кастомный фрейм
	public static boolean useOffline 			 =  true; //Использовать режим оффлайн
	public static boolean useConsoleHider		 =  true; //Использовать скрытие консоли клиента

	public static final String protectionKey			 = "tH@nKy0u.d_@rT"; //Ключ защиты сессии. Никому его не говорите.

	public static final boolean debug		 	 =  true;  //Отображать все действия лаунчера (отладка)(true/false)
	public static final boolean drawTracers		 =  false; //Отрисовывать границы элементов лаунчера
	public static final String masterVersion  	 = "final_RC4"; //Версия лаунчера
	
	public static void onStart() { /*  */ }
	public static void onStartMinecraft() {}
}