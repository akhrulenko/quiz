Порт 8085

Добавьте значения переменных или опишите их вручную для ${DB_URL}, ${DB_USERNAME} и ${DB_PASSWORD} в application.yml <br>

API:
GET localhost:8085/quizzes	для получения списка всех опросов
Параметры в url: title, startDate, endDate, actiity опционально, orderBy обязателен, пагинация через pageSize и pageNum опционально<br>

POST localhost:8085/quizzes	для добавления опроса

PUT localhost:8085/quizzes	для обновления опроса

DELETE localhost:8085/quizzes/{id} для удаления опроса


Запуск в папке проекта: mvn spring-boot:run
