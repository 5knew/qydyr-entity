# 📋 Объяснение проекта Qydyr Entity

## 🎯 Что это такое?

**Qydyr Entity** - это система управления событиями и местами проведения мероприятий, построенная на Spring Boot. Система позволяет пользователям просматривать события, покупать билеты, добавлять в избранное и управлять своим профилем.

## 🏗️ Архитектура системы

### Backend (Java Spring Boot)
- **Spring Boot 2.7.5** - основной фреймворк
- **Spring Security** - аутентификация и авторизация
- **JWT** - токены для API
- **Spring Data JPA** - работа с базой данных
- **MySQL 8.0** - база данных
- **Maven** - управление зависимостями

### Frontend (Thymeleaf)
- **Thymeleaf** - серверный шаблонизатор
- **Bootstrap 5** - CSS фреймворк
- **Font Awesome** - иконки
- **JavaScript** - интерактивность

### Документация
- **Swagger UI** - интерактивная API документация
- **Markdown** - документация проекта

## 🎭 Основные сущности

### 1. Пользователи (User)
```java
- id: Long
- firstName: String
- lastName: String
- email: String
- password: String (зашифрован)
- role: Role (USER, ADMIN, MANAGER)
- cash: String (баланс для покупок)
```

**Роли:**
- **USER** - обычный пользователь
- **ADMIN** - полный доступ к системе
- **MANAGER** - управление контентом

### 2. Афиши (Afisha) - События
```java
- id: Long
- name: String (название события)
- addressName: String (название места)
- addressLink: String (ссылка на карты)
- eventDateTimeFrom: LocalDateTime (начало)
- eventDateTimeTo: LocalDateTime (окончание)
- phone: String (контактный телефон)
- description: String (описание)
- price: Integer (цена билета)
- status: Status (ACTIVE, INACTIVE, CANCELLED, PURCHASED)
- category: EventCategory (CONCERT, THEATRE, CINEMA, MARATHON, SEMINAR)
- address: Address (адрес)
- geoLocation: GeoLocation (координаты)
```

### 3. Места проведения (Place)
```java
- id: Long
- name: String (название места)
- streetName: String (улица)
- streetNumber: String (номер дома)
- placeLink: String (ссылка на карты)
- description: String (описание)
- priceFrom: Integer (минимальная цена)
- priceTo: Integer (максимальная цена)
- socialNetwork: String (социальные сети)
- phone: String (телефон)
- category: Category (CONCERT_HALL, THEATER_HALL, STADIUM, etc.)
- status: Status (ACTIVE, INACTIVE)
- address: Address (адрес)
- geoLocation: GeoLocation (координаты)
- images: List<Image> (фотографии)
```

### 4. Заказы (Order)
```java
- id: Long
- count: Integer (количество билетов)
- status: Status (PURCHASED, PAID, REFUND)
- user: User (покупатель)
- afisha: Afisha (событие)
```

### 5. Избранное
- **FavoriteAfishes** - избранные афиши
- **FavoritePlaces** - избранные места

## 🔐 Система безопасности

### Аутентификация
- **JWT токены** для API
- **Session-based** для веб-интерфейса
- **BCrypt** для шифрования паролей

### Авторизация
- **Роли пользователей** (USER, ADMIN, MANAGER)
- **Разрешения** (permissions)
- **Защищенные маршруты**

### Защищенные эндпоинты
- `/admin/**` - только для ADMIN
- `/api/**` - требуют JWT токен
- `/api/v1/auth/**` - открытые для регистрации/входа

## 🚀 Основные функции

### Для пользователей
1. **Регистрация и вход** в систему
2. **Просмотр событий** с фильтрацией и поиском
3. **Просмотр мест** проведения
4. **Покупка билетов** на события
5. **Добавление в избранное** событий и мест
6. **Управление профилем**
7. **Просмотр истории заказов**

### Для администраторов
1. **Полный доступ** ко всем функциям
2. **Управление пользователями**
3. **Создание и редактирование** событий
4. **Создание и редактирование** мест
5. **Просмотр статистики** системы
6. **Управление заказами**

### Для менеджеров
1. **Управление контентом** (события, места)
2. **Просмотр пользователей**
3. **Ограниченный доступ** к админ-функциям

## 📊 API Endpoints

### Аутентификация
- `POST /api/auth/register` - регистрация
- `POST /api/auth/login` - вход

### Пользователи
- `GET /api/users` - все пользователи
- `GET /api/users/{id}` - пользователь по ID
- `PUT /api/users/{id}` - обновление пользователя

### Афиши
- `GET /api/afisha` - все афиши (с пагинацией)
- `GET /api/afisha/{id}` - афиша по ID
- `POST /api/afisha` - создание афиши
- `PUT /api/afisha/{id}` - обновление афиши
- `DELETE /api/afisha/{id}` - удаление афиши

### Места
- `GET /api/places` - все места
- `GET /api/places/{id}` - место по ID
- `POST /api/places` - создание места
- `PUT /api/places/{id}` - обновление места
- `DELETE /api/places/{id}` - удаление места

### Избранное
- `GET /api/favourites/afisha` - избранные афиши
- `POST /api/favourites/afisha` - добавить афишу в избранное
- `DELETE /api/favourites/afisha/{id}` - удалить из избранного
- `GET /api/favourites/places` - избранные места
- `POST /api/favourites/places` - добавить место в избранное
- `DELETE /api/favourites/places/{id}` - удалить из избранного

### Платежи
- `POST /api/payment/buy-afisha` - покупка билета

### Email
- `POST /api/email/send` - отправка email

### Изображения
- `POST /api/images/upload` - загрузка изображения
- `GET /api/images/{id}` - получение изображения

## 🌐 Веб-интерфейс

### Публичные страницы
- `/` - главная страница
- `/login` - вход в систему
- `/register` - регистрация
- `/events` - список событий
- `/venues` - список мест
- `/favourites` - избранное
- `/profile` - профиль пользователя
- `/orders` - история заказов

### Админ-панель
- `/admin` - дашборд администратора
- `/admin/login` - вход в админ-панель
- `/admin/afisha` - управление афишами
- `/admin/places` - управление местами
- `/admin/users` - управление пользователями

## 🗄️ База данных

### Основные таблицы
- `user` - пользователи
- `afisha` - афиши/события
- `place` - места проведения
- `orders` - заказы
- `favorite_afishes` - избранные афиши
- `favorite_places` - избранные места
- `image` - изображения

### Связи
- User → Orders (один ко многим)
- Afisha → Orders (один ко многим)
- User → FavoriteAfishes (один ко многим)
- User → FavoritePlaces (один ко многим)
- Place → Images (один ко многим)

## 🧪 Тестирование

### Готовые тестовые данные
- **8 пользователей** (1 админ, 1 менеджер, 6 пользователей)
- **10 афиш** (концерты, театр, кино, марафон, семинар)
- **9 мест** (концертные залы, театры, кинотеатр, парк)
- **10 заказов** (примеры покупок)
- **20 записей избранного**

### Файлы для тестирования
- `TEST_DATA.md` - описание тестовых данных
- `SQL_TEST_DATA.sql` - SQL скрипты
- `API_JSON_EXAMPLES.json` - JSON примеры
- `TEST_EXAMPLES.md` - примеры тестирования
- `ADMIN_ACCESS_GUIDE.md` - доступ к админ-панели

## 🚀 Развертывание

### Локальная разработка
1. Установите Java 17+, MySQL 8.0+, Maven 3.6+
2. Создайте базу данных `qydyr`
3. Настройте `application.properties`
4. Запустите `./mvnw spring-boot:run`

### Production
1. Настройте SSL сертификаты
2. Измените пароли базы данных
3. Настройте CORS для фронтенда
4. Настройте логирование и мониторинг

## 📚 Документация

### Основные файлы
- `README.md` - основная документация
- `API_DOCUMENTATION.md` - API документация
- `QUICK_START.md` - быстрый старт
- `DEPLOYMENT.md` - развертывание
- `FRONTEND_GUIDE.md` - руководство по фронтенду
- `ADMIN_GUIDE.md` - руководство администратора

### Тестовые файлы
- `TEST_DATA.md` - тестовые данные
- `TEST_EXAMPLES.md` - примеры тестирования
- `SQL_TEST_DATA.sql` - SQL скрипты
- `API_JSON_EXAMPLES.json` - JSON примеры
- `ADMIN_ACCESS_GUIDE.md` - доступ к админ-панели

## 🔧 Технические особенности

### Безопасность
- JWT токены с настраиваемым сроком действия
- BCrypt для шифрования паролей
- Защита от CSRF атак
- Валидация входных данных

### Производительность
- Пагинация для больших списков
- Ленивая загрузка связанных объектов
- Кэширование статических ресурсов
- Оптимизированные SQL запросы

### Масштабируемость
- Модульная архитектура
- Разделение на слои (Controller, Service, Repository)
- Использование DTO для передачи данных
- Готовность к микросервисной архитектуре

## 🎯 Возможности расширения

### Планируемые функции
- Система уведомлений
- Интеграция с платежными системами
- Мобильное приложение
- API для внешних сервисов
- Система отзывов и рейтингов
- Аналитика и отчеты

### Технические улучшения
- Redis для кэширования
- Elasticsearch для поиска
- Docker контейнеризация
- CI/CD пайплайн
- Мониторинг и логирование

---

**Qydyr Entity** - это полнофункциональная система управления событиями, готовая к использованию в production среде! 🚀
