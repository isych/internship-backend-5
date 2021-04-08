# Java Internship. Backend Team-5

Приложение настроено для запуска через Docker. Если docker не использутеся, то нужно изменить под себя параметры доступа к БД в файле application.properties.

Последовательность запуска:
1. В консоли ввыполнить команду: docker-compose up

Ссылка для доступа к swagger: http://localhost:8081/swagger-ui/

Для запуска pgAdmin4 требуется:
1. в консоли выполнить команду docker ps и получить идентификатор контейнера с именем postgres:12.6-alpine
2. в консоли выполнить команду docker inspect ID , где ID - идентификатор из п.1
3. найти поле IPAddress и запомнить указанный IP-адресс
4. в окне браузера перейти по адресу http://localhost:5454/
5. email/password:  admin@admin.com / pass
6. Вставьте IP-адрес в pgAdmin(при создании нового сервера) и учетные данные базы данных(Maintenance database: app_db   Username: app_user   Password: password)

Spring Security подключена, но использовать JWT не обязательно. Все пользователи имеют полный доступ.

В базе у всех пользователей пароль "1".
