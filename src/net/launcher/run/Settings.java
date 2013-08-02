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
	public static final String        skins            = "MinecraftSkins/"; //Папка скинов
	public static final String        cloaks           = "MinecraftCloaks/"; //Папка плащей
	/** Параметры подключения */
	public static final String 	domain	 	 	 = "alexandrage.dyndns.org";//Домен сайта
	public static final String  siteDir		  	 = "site";//Папка с файлами лаунчера на сайте
	public static final String  updateFile		 = "http://alexandrage.dyndns.org/site/Launcher.jar";//Ссылка на файл обновления лаунчера
	public static final String 	buyVauncherLink  = "http://plati.ru/"; //Ссылка на страницу покупки ваучеров

	/** Для одиночной игры */
	public static final String  defaultUsername  = "player"; //Имя пользователя для одиночной игры
	public static final String  defaultSession   = "123456"; //Номер сессии для одиночной игры

	/** Настройка серверов */
	// 1-> Имя папки клиента 2-> ip 3-> port 
	// 4-> Версия клиента для автопатча директории (старые версии до 1.5.2)
	// 5-> Тип запуска клиента 1 для старых версий 2 для новых
	// 6-> Главный класс для запуска (новые версии) 
	// net.minecraft.client.main.Main запуск чистого клиента
	// net.minecraft.launchwrapper.Launch запуск клиента с Forge и Liteloader
	// 7-> 1 для запуска клиента с Liteloader и Liteloader+forge
	//     2 для запуска клиента с forge без Liteloader
	public static final String[] servers =
	{
		"v, localhost, 25565, 1.5.x, 1, none",
		"vanila, localhost, 25565, none, 2, net.minecraft.client.main.Mainю, 1",
		"voxelaria, localhost, 25565, none, 2, net.minecraft.launchwrapper.Launch, 1",
	};

	/** Настройка панели ссылок **/
	public static final String[] links = 
	{
		//Для отключения добавьте в адрес ссылки #
		" Регистрация ::http://",
	};

	/** Настройки структуры лаунчера */
	public static boolean useAutoenter			 =  false; //Использовать функцию автозахода на выбранный сервер
	
	
	public static boolean useMulticlient		 =  true; //Использовать функцию "по клиенту на сервер"
	public static boolean useStandartWB			 =  true; //Использовать стандартный браузер для открытия ссылок
	public static boolean usePersonal		 	 =  true; //Использовать Личный кабинет
	public static boolean customframe 			 =  true; //Использовать кастомный фрейм
	public static boolean useOffline 			 =  true; //Использовать режим оффлайн
	public static boolean useConsoleHider		 =  true; //Использовать скрытие консоли клиента
	public static boolean useModCheckerTimer	 =  true; //Каждые 30 секунд моды будут перепроверяться

	public static final String protectionKey	 = "1234567890"; //Ключ защиты сессии. Никому его не говорите.

	public static final boolean debug		 	 =  false;  //Отображать все действия лаунчера (отладка)(true/false)
	public static final boolean drawTracers		 =  false; //Отрисовывать границы элементов лаунчера
	public static final String masterVersion  	 = "final_RC4"; //Версия лаунчера

	public static final boolean patchDir 		 =  true; //Использовать автоматическую замену директории игры (true/false)
	public static final String mcclass			 = "net.minecraft.client.Minecraft";
	public static final String[] mcversions		 =
	{
		"1.7.3::af", "1.8.1::ag", "1.2.5::aj", "1.3.x::am", "1.4.x::an", "1.5.x::an"
	};
	
	public static void onStart() { /*  */ }
	public static void onStartMinecraft() {}
}