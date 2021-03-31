# Java Internship. Backend Team-5

Ссылка для доступа к swagger локально: http://localhost:8081/swagger-ui/

Приложение настроено для запуска через Docker. Если docker не использутеся, то нужно изменить под себя параметры доступа к БД в файле application.properties.

Последовательность запуска:
1. maven clean
2. maven package
3. docker image build -t rest-server .
4. docker-compose up

Spring Security подключена, но использовать JWT не обязательно. Все пользователи имеют полный доступ.

В базе пользователей присутствуют три записи. Пользователь с правами администратора: login: test-admin  password: 1. Обычный пользователь (тех.спец): login: test-user  password: 1. Полььзователь с правами супер-администратора: login: test-superadmin   password: 1.