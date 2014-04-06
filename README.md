Fix-Sashok  
==========
Модифицированная и улучшенная версия лаунчера для игры Minecraft от Sashok724  
### Основные отличия 
- Поддержка Minecraft старых версий (до 1.5.2) и новых (от 1.6)
- Наличие функции регистрации в лаунчере (только для сайтов с шифрованием md5 и CMS DLE)
- Наличие связанной с плагином UltraBans функции бана. 
   
### Содержимое WEB-части
* libraries.jar -> Библиотеки клиента.
* Forge.jar -> Minecraft Forge.
* extra.jar -> Запасной джарник для OptiFine, PlayerApi, GuiApi и т.д.
* client.zip -> Содержит bin/natives для lwjgl, config для модов.  
Добавлен assets.zip Звуки для клиентов 1.6.+, должен быть одинаковым размером во всех клиентах на сайте версии 1.6.+)
* Список серверов теперь редактируется на сайте servers.php.
* Дописаны скрипты для авторизации 1.7.2-1.7.4.
Ссылки на новые скрипты указываем в классе YggdrasilMinecraftSessionService.class
"http://minecraft/site/"
"http://minecraft/site/j.php"
"http://minecraft/site/h.php"


Данный мини-хак позволит вам откатится с нового алгоритма генерации хэша пароля (Core12) 
на старый, использовавшийся на версиях XenForo младше 1.2

1. Открываем php скрипт /library/XenForo/Authentication/Abstract.php
2. Ищем функцию createDefault, примерно на 134 строке, в конце скрипта. 
3. В теле функции заменяем XenForo_Authentication_Core12 на XenForo_Authentication_Core
4. Для тех кто уже зарегистрирован достаточно поменять пароль.
