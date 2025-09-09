# Qydyr Entity - Система управления событиями и местами

## 📋 Описание проекта

Qydyr Entity - это Spring Boot приложение для управления событиями (афишами) и местами проведения мероприятий. Система включает в себя аутентификацию пользователей, систему платежей, управление избранным и email уведомления.

## 🚀 Быстрый старт

### Предварительные требования
- Java 17+
- MySQL 8.0+
- Maven 3.6+

### Установка и запуск

1. **Клонирование репозитория**
```bash
git clone <repository-url>
cd qydyr-entity
```

2. **Настройка базы данных**
```bash
# Установка MySQL через Homebrew (macOS)
brew install mysql
brew services start mysql

# Создание базы данных
mysql -u root -proot -e "CREATE DATABASE qydyr;"
```

3. **Запуск приложения**
```bash
# Установка прав на выполнение
chmod +x ./mvnw

# Запуск приложения
./mvnw spring-boot:run
```

4. **Проверка работы**
- Откройте браузер: http://localhost:8080
- API документация: http://localhost:8080/swagger-ui/index.html

## 🏗️ Архитектура проекта

### Backend
```
src/main/java/com/example/qydyr/
├── auth/                    # Аутентификация и авторизация
│   ├── AuthenticationController.java
│   ├── AuthenticationService.java
│   ├── AuthenticationRequest.java
│   └── RegisterRequest.java
├── config/                  # Конфигурация
│   ├── SecurityConfiguration.java
│   ├── JwtService.java
│   ├── JwtAuthenticationFilter.java
│   └── SwaggerConfig.java
├── controller/              # REST контроллеры
│   ├── AfishaController.java
│   ├── PlaceController.java
│   ├── UserController.java
│   ├── FavouriteController.java
│   ├── ImageController.java
│   └── EmailController.java
├── dto/                     # Data Transfer Objects
│   ├── CreateAfishaDto.java
│   ├── UpdateAfishaDto.java
│   ├── CreatePlaceDto.java
│   ├── UpdatePlaceDto.java
│   ├── UserDto.java
│   └── AdminUserDto.java
├── exception/               # Обработка исключений
│   ├── GlobalExceptionHandler.java
│   ├── UserNotFoundException.java
│   ├── AfishaNotFoundException.java
│   └── InsufficientFundsException.java
├── model/                   # Модели данных
│   ├── User.java
│   ├── Afisha.java
│   ├── Place.java
│   ├── Order.java
│   ├── Image.java
│   ├── Address.java
│   ├── GeoLocation.java
│   └── enums/              # Перечисления
├── repository/              # Репозитории JPA
│   ├── UserRepository.java
│   ├── AfishaRepository.java
│   ├── PlaceRepository.java
│   ├── OrderRepository.java
│   ├── ImageRepository.java
│   ├── FavouriteAfishesRepository.java
│   └── FavouritePlaceRepository.java
├── service/                 # Бизнес-логика
│   ├── UserService.java
│   ├── AfishaService.java
│   ├── PlaceService.java
│   ├── PaymentService.java
│   ├── EmailSender.java
│   ├── ImageService.java
│   ├── FavouriteService.java
│   ├── GeoLocationService.java
│   └── impl/               # Реализации сервисов
└── utils/                   # Утилиты
    └── ImageUtil.java
```

### Frontend
```
src/main/resources/
├── templates/               # Thymeleaf шаблоны
│   ├── layouts/
│   │   └── base.html       # Базовый шаблон
│   └── pages/              # Страницы приложения
│       ├── index.html      # Главная страница
│       ├── login.html      # Вход
│       ├── register.html   # Регистрация
│       ├── afisha.html     # События
│       ├── places.html     # Места
│       ├── favourites.html # Избранное
│       ├── profile.html    # Профиль
│       └── orders.html     # Заказы
├── static/                 # Статические ресурсы
│   ├── css/
│   │   └── style.css       # Пользовательские стили
│   └── js/
│       └── main.js         # JavaScript функции
└── application.properties  # Конфигурация
```

## 🛠️ Технологии

### Backend
- **Spring Boot** 2.7.5
- **Java** 17
- **MySQL** 8.0
- **Spring Security** + JWT
- **Maven** для сборки

### Frontend
- **Thymeleaf** - серверный шаблонизатор
- **Bootstrap 5** - CSS фреймворк
- **Font Awesome** - иконки
- **JavaScript** - интерактивность

### Документация
- **Swagger UI** - API документация

## 🔧 Конфигурация

### База данных (application.properties)
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/qydyr
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

### Email настройки
```properties
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your-email@gmail.com
spring.mail.password=your-app-password
```

### Google Maps API
```properties
gmaps.api.key=YOUR_GOOGLE_MAPS_API_KEY
```

## 📚 API Документация

### Веб-интерфейс

Приложение предоставляет полноценный веб-интерфейс, доступный по адресу `http://localhost:8080/`

#### Основные страницы
- **Главная** (`/`) - обзор возможностей и последние события
- **События** (`/events`) - просмотр и управление событиями
- **Места** (`/venues`) - просмотр и управление местами проведения
- **Избранное** (`/favourites`) - сохраненные события и места
- **Профиль** (`/profile`) - управление аккаунтом
- **Заказы** (`/orders`) - история покупок

#### Функции веб-интерфейса
- ✅ Адаптивный дизайн для всех устройств
- ✅ Интуитивная навигация
- ✅ Поиск и фильтрация
- ✅ Добавление в избранное
- ✅ Покупка билетов
- ✅ Управление профилем
- ✅ Уведомления о действиях

### API Документация

Полная документация API доступна в Swagger UI: `http://localhost:8080/swagger-ui/index.html`

### Аутентификация

#### Регистрация пользователя
```http
POST /api/auth/register
Content-Type: application/json

{
  "firstName": "Имя",
  "lastName": "Фамилия",
  "email": "user@example.com",
  "password": "password123",
  "role": "USER"
}
```

#### Вход в систему
```http
POST /api/auth/login
Content-Type: application/json

{
  "email": "user@example.com",
  "password": "password123"
}
```

**Ответ:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "user": {
    "id": 1,
    "firstName": "Имя",
    "lastName": "Фамилия",
    "email": "user@example.com",
    "role": "USER"
  }
}
```

### Управление пользователями

#### Получить всех пользователей
```http
GET /api/users
Authorization: Bearer YOUR_JWT_TOKEN
```

#### Получить пользователя по ID
```http
GET /api/users/{id}
Authorization: Bearer YOUR_JWT_TOKEN
```

#### Обновить пользователя
```http
PUT /api/users/{id}
Authorization: Bearer YOUR_JWT_TOKEN
Content-Type: application/json

{
  "firstName": "Новое имя",
  "lastName": "Новая фамилия",
  "email": "newemail@example.com"
}
```

### Управление афишами

#### Получить все афиши
```http
GET /api/afisha
Authorization: Bearer YOUR_JWT_TOKEN
```

#### Создать афишу
```http
POST /api/afisha
Authorization: Bearer YOUR_JWT_TOKEN
Content-Type: application/json

{
  "name": "Концерт группы",
  "addressName": "Концертный зал",
  "addressLink": "https://maps.google.com/...",
  "eventDateTimeFrom": "2024-12-31T19:00:00",
  "eventDateTimeTo": "2024-12-31T22:00:00",
  "phone": "+7 777 777 7777",
  "description": "Описание концерта",
  "price": 5000,
  "status": "ACTIVE",
  "category": "CONCERT"
}
```

#### Обновить афишу
```http
PUT /api/afisha/{id}
Authorization: Bearer YOUR_JWT_TOKEN
Content-Type: application/json

{
  "name": "Обновленное название",
  "price": 6000
}
```

#### Удалить афишу
```http
DELETE /api/afisha/{id}
Authorization: Bearer YOUR_JWT_TOKEN
```

### Управление местами

#### Получить все места
```http
GET /api/places
Authorization: Bearer YOUR_JWT_TOKEN
```

#### Создать место
```http
POST /api/places
Authorization: Bearer YOUR_JWT_TOKEN
Content-Type: application/json

{
  "name": "Концертный зал",
  "streetName": "Улица Пушкина",
  "streetNumber": "10",
  "placeLink": "https://maps.google.com/...",
  "description": "Описание места",
  "priceFrom": 1000,
  "priceTo": 5000,
  "socialNetwork": "@concert_hall",
  "phone": "+7 777 777 7777",
  "street": "Улица Пушкина",
  "city": "Алматы",
  "country": "Казахстан",
  "status": "ACTIVE",
  "category": "CONCERT_HALL",
  "geoProcessed": false
}
```

### Система избранного

#### Получить избранные места
```http
GET /api/favourites/places
Authorization: Bearer YOUR_JWT_TOKEN
```

#### Добавить место в избранное
```http
POST /api/favourites/places
Authorization: Bearer YOUR_JWT_TOKEN
Content-Type: application/json

{
  "placeId": 1
}
```

#### Получить избранные афиши
```http
GET /api/favourites/afisha
Authorization: Bearer YOUR_JWT_TOKEN
```

#### Добавить афишу в избранное
```http
POST /api/favourites/afisha
Authorization: Bearer YOUR_JWT_TOKEN
Content-Type: application/json

{
  "afishaId": 1
}
```

### Платежная система

#### Покупка билета
```http
POST /api/payment/buy-afisha
Authorization: Bearer YOUR_JWT_TOKEN
Content-Type: application/json

{
  "userId": 1,
  "afishaId": 1
}
```

### Email уведомления

#### Отправить email
```http
POST /api/email/send
Authorization: Bearer YOUR_JWT_TOKEN
Content-Type: application/json

{
  "to": "user@example.com",
  "subject": "Тема письма",
  "body": "Содержимое письма"
}
```

### Управление изображениями

#### Загрузить изображение
```http
POST /api/images/upload
Authorization: Bearer YOUR_JWT_TOKEN
Content-Type: multipart/form-data

file: [выбранный файл]
```

#### Получить изображение
```http
GET /api/images/{id}
Authorization: Bearer YOUR_JWT_TOKEN
```

## 🔐 Безопасность

### JWT Токены
- Все API endpoints (кроме регистрации и входа) требуют JWT токен
- Добавьте заголовок `Authorization: Bearer YOUR_JWT_TOKEN` к запросам
- Токены имеют срок действия (настраивается в конфигурации)

### Роли пользователей
- **USER** - обычный пользователь
- **ADMIN** - администратор системы

## 📊 Модели данных

### User (Пользователь)
```java
{
  "id": Long,
  "firstName": String,
  "lastName": String,
  "username": String,
  "email": String,
  "password": String,
  "cash": String,
  "role": Role
}
```

### Afisha (Афиша)
```java
{
  "id": Long,
  "name": String,
  "addressName": String,
  "addressLink": String,
  "createdDateTime": LocalDateTime,
  "eventDateTimeFrom": LocalDateTime,
  "eventDateTimeTo": LocalDateTime,
  "phone": String,
  "description": String,
  "price": Integer,
  "status": Status,
  "category": EventCategory,
  "geoLocation": GeoLocation,
  "address": Address,
  "image": Image
}
```

### Place (Место)
```java
{
  "id": Long,
  "name": String,
  "streetName": String,
  "streetNumber": String,
  "placeLink": String,
  "description": String,
  "priceFrom": Integer,
  "priceTo": Integer,
  "socialNetwork": String,
  "phone": String,
  "address": Address,
  "geoLocation": GeoLocation,
  "status": Status,
  "category": EventCategory,
  "geoProcessed": Boolean,
  "image": Image
}
```

## 🚀 Развертывание

### Docker (опционально)
```yaml
version: '3.8'
services:
  mysql:
    image: mysql:latest
    environment:
      MYSQL_ROOT_PASSWORD: root
      MYSQL_DATABASE: qydyr
    ports:
      - "3306:3306"
    volumes:
      - mysql_data:/var/lib/mysql

  app:
    build: .
    ports:
      - "8080:8080"
    depends_on:
      - mysql
    environment:
      - SPRING_DATASOURCE_URL=jdbc:mysql://mysql:3306/qydyr
      - SPRING_DATASOURCE_USERNAME=root
      - SPRING_DATASOURCE_PASSWORD=root

volumes:
  mysql_data:
```

### Production настройки
1. Измените пароли базы данных
2. Настройте SSL сертификаты
3. Настройте CORS для фронтенда
4. Настройте логирование
5. Настройте мониторинг

## 🧪 Тестирование

### Запуск тестов
```bash
./mvnw test
```

### Интеграционные тесты
```bash
./mvnw verify
```

### Тестовые данные

В проекте доступны готовые тестовые данные для быстрого старта:

#### 📁 Файлы с тестовыми данными
- **`TEST_DATA.md`** - Полное описание всех тестовых данных
- **`SQL_TEST_DATA.sql`** - SQL скрипты для заполнения БД
- **`API_JSON_EXAMPLES.json`** - JSON примеры для API тестирования
- **`TEST_EXAMPLES.md`** - Расширенные примеры тестирования

#### 🚀 Быстрый старт с тестовыми данными

1. **Заполните БД тестовыми данными:**
```bash
# Подключитесь к MySQL
mysql -u root -proot -D qydyr

# Выполните SQL скрипт
source SQL_TEST_DATA.sql
```

2. **Или создайте администратора через API:**
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "Админ",
    "lastName": "Системы",
    "email": "admin@example.com",
    "password": "admin123",
    "role": "ADMIN"
  }'
```

3. **Войдите в админ-панель:**
   - Откройте http://localhost:8080/admin/login
   - Email: `admin@example.com`
   - Пароль: `admin123`

#### 📊 Статистика тестовых данных
- **Пользователи**: 8 (1 админ, 1 менеджер, 6 пользователей)
- **Афиши**: 10 (концерты, театр, кино, марафон, семинар)
- **Места**: 9 (концертные залы, театры, кинотеатр, парк, конференц-зал)
- **Заказы**: 10 (примеры покупок билетов)
- **Избранное**: 20 записей (афиши и места)

#### 🔧 Тестовые аккаунты
| Роль | Email | Пароль | Описание |
|------|-------|--------|----------|
| ADMIN | admin@example.com | admin123 | Полный доступ к системе |
| MANAGER | manager@example.com | manager123 | Управление контентом |
| USER | aidar@example.com | password123 | Обычный пользователь |
| USER | aizhan@example.com | password123 | Обычный пользователь |

## 📝 Логирование

### Уровни логирования
- **ERROR** - критические ошибки
- **WARN** - предупреждения
- **INFO** - информационные сообщения
- **DEBUG** - отладочная информация

### Настройка логирования
```properties
logging.level.com.example.qydyr=DEBUG
logging.level.org.springframework.security=DEBUG
logging.level.org.hibernate.SQL=DEBUG
```

## 🔧 Устранение неполадок

### Частые проблемы

1. **Ошибка подключения к базе данных**
   - Проверьте, что MySQL запущен
   - Проверьте настройки в application.properties
   - Проверьте права доступа пользователя

2. **Ошибка компиляции Lombok**
   - Убедитесь, что в pom.xml настроен annotationProcessorPaths
   - Перезапустите IDE

3. **Ошибка JWT токена**
   - Проверьте, что токен не истек
   - Проверьте правильность заголовка Authorization

4. **Ошибка загрузки файлов**
   - Проверьте настройки multipart в application.properties
   - Проверьте права на запись в директорию

## 📞 Поддержка

Для получения поддержки:
1. Проверьте логи приложения
2. Изучите API документацию в Swagger UI
3. Создайте issue в репозитории

## 📄 Лицензия

Этот проект распространяется под лицензией MIT.

## 🤝 Вклад в проект

1. Форкните репозиторий
2. Создайте ветку для новой функции
3. Внесите изменения
4. Создайте Pull Request

---

**Версия:** 1.0.0  
**Последнее обновление:** 2024-09-08
