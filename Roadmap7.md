# ПЛАН 7

### 1.Добавить зависимости в pom.xml (20 мин)

### 2.Создать класс SerializeProvider (30 мин)

-2.1 implements provider (10 мин)

-2.2 Возвращать GsonBuilder() для использования Gson (10 мин)

-2.3 В GuiseServletConfig привязать SerializeProvider к поставщику Gson (10 мин)

### 3.В UserServlet инжектить провайдер GSON (5 мин)

### 4.В AuthorityServlet инжектить провайдер GSON (5 мин) 

### 5.В ActivityServlet инжектить провайдер GSON (5 мин)

### 6.В AuthenticationDao добавить запросы (60 мин)

-6.1 Запрос который возвращает список всех пользователей (30 мин)

-6.2 Запрос который возвращает пользователя с указанным идентификатором (30 мин)

### 7.В UserServlet написать проверку (60 мин)

-7.1 Если req.getParameter("id") != null выводить в json формате пользователя с указанным идентификатором
else выводить всех пользователей (60 мин)

### 8.В AuthorizationDao добавить запросы (90 мин)

-8.1 Запрос который возвращает список прав доступа (30 мин)

-8.2 Запрос который возвращает право пользователя с указанным идентификатором (30 мин)

-8.3 Запрос который возвращает права указанного пользователя (30 мин)

### 9.В AuthrityServlet написать проверку (60 мин)

-9.1 Если req.getParameter("id") != null выводить в json формате право с указанным идентификатором
else if req.getParameter("userId") != null выводить в json формате права указанного пользователя
else выводить все права (60 мин)

### 10.В AccountingDao добавить запросы (120 мин)

-10.1 Запрос который возвращает список действий (30 мин)

-10.2 Запрос который возвращает действие с указанным идентификатором (30 мин)

-10.3 Запрос который возвращает роль указанного пользователя с идентификатором authoriryId (30 мин)

-10.4 Запрос который возвращает список действий с указанной ролью (30 мин)

### 11.В ActivityServlet написать проверку (60 мин)

-11.1 Если req.getParameter("id") != null выводить в json формате действие с указанным идентификатором
else if req.getParameter("auyhorityId") != null выводить в json формате действия с указанными правами доступа
else выводить все действия (60 мин)

### 12. Пометить поля в domain пакете @Expose (20 мин)

-12.1 В объекте User не поменать password и hashPassword

-12.2 В объекте Authorization не помечать User

-12.3 В объекте Accounting не помечать Authorization

### 13. Создать класс ConnectionProvider (120 мин)

-13.1 implements provider (10 мин)

-13.2 Возвращать коннекшен (110 мин)


Пункт плана|Оценка времени  |Фактическое время   |
-----------| ---------------| ----------------   |
1	         |     20 мин     |  15 мин            |
2          |     30 мин     |  20 мин            |
3          |     5 мин      |  5 мин             |
4          |     5 мин      |  5 мин             |
5	         |     5 мин      |  5 мин             |
6	         |     60 мин     |  30 мин            |
7	         |     60 мин     |  30 мин            |
8          |     90 мин     |  80 мин            |
9          |     60 мин     |  60 мин            |
10         |     120 мин    |  60 мин            |
11         |     60 мин     |  30 мин            |
12         |     20 мин     |  20 мин            |
13         |     120 мин    |  150 мин           |
Итого      |655мин(11 часов)|  510 мин(8,5 часов)|
