# План 5

### 1.Преобразовать проект в веб приложение (20 мин)

### 2.Добавить зависимости в pom.xml (20 мин)

### 3.Настроить сервер jetty (40 мин)

### 4.Создать файл index.html (3 мин)

- 4.1 создать форму для ввода данных (1 мин)

- 4.2 добавить кнопку для отправки данных (1 мин)

- 4.3 cделать ссылку на GET-сервлет с параметром. (1 мин)

### 5.Создать сервлет echoServlet и переопределить в нем методы doPost, doGet  (30 мин)

- 5.1 Добавить сервлеты в web.xml (10 мин)

- 5.2 в методе doGet принимать GET запрос вида ?id=X и выводить в ответ значение X (10 мин)

- 5.3 в методе doPost получать данные из формы и редиректить на  (/echo/get?id= параметру из формы) (10 мин)

### 6.Сделать иерархический pom-проект (50 мин)

- 6.1 В родительском Pom обьявить два дочерних модуля (25 мин)

- 6.2 В каждом дочернем модуле добавить ссылку на родителя (25 мин)

### 7.Задеплоить war проект на heroku (40 мин)

### 8.Настроить автоматический деплой war проекта на heroku (10 мин)

### 9.Генерировать результат get страницы (10 мин)

- 9.1 Создать jsp странницу 

- 9.2 Выводить на ней полученый id

Пункт плана|Оценка времени  |Фактическое время|
-----------| ---------------| ----------------|
1	         |     20 мин     | 22 мин          |
2          |     20 мин     | 20 мин          |
3          |     40 мин     | 50 мин          |
4          |     3 мин      | 10 мин          |
5	         |     30 мин     | 25 мин          |
6	         |     50 мин     | 60 мин          |
7          |     40 мин     | 50 мин          |
8          |     10 мин     | 10 мин          |
9          |     10 мин     | 10 мин          |
Итого:     |263 мин(~4 часа)| 257 мин(~4 часа)|
