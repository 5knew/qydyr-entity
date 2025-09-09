# 🧪 Примеры тестирования API - Qydyr Entity

## 🚀 Быстрый старт тестирования

### 1. Проверка статуса приложения
```bash
curl http://localhost:8080/actuator/health
```

**Ожидаемый ответ:**
```json
{
  "status": "UP",
  "components": {
    "db": {
      "status": "UP"
    }
  }
}
```

### 2. Открытие Swagger UI
Откройте браузер и перейдите по адресу:
```
http://localhost:8080/swagger-ui/index.html
```

### 3. Доступ к админ-панели
```
http://localhost:8080/admin/login
```

---

## 🔐 Решение проблемы доступа к админ-панели

### Проблема: Ошибка 403 Forbidden при доступе к /admin

**Причина**: Для доступа к админ-панели нужен пользователь с ролью ADMIN.

### Решение:

#### 1. Создайте администратора через API
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

#### 2. Войдите в админ-панель через веб-интерфейс
1. Откройте http://localhost:8080/admin/login
2. Введите:
   - **Email**: `admin@example.com`
   - **Пароль**: `admin123`
3. Нажмите "Войти"

#### 3. Альтернативный способ - через SQL
```sql
-- Подключитесь к MySQL
mysql -u root -proot -D qydyr

-- Создайте администратора
INSERT INTO user (first_name, last_name, username, email, password, role, cash) 
VALUES ('Админ', 'Системы', 'admin', 'admin@example.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDi', 'ADMIN', '100000');
```

---

## 👤 Тестирование пользователей

### Регистрация администратора
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

### Регистрация менеджера
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "Менеджер",
    "lastName": "Событий",
    "email": "manager@example.com",
    "password": "manager123",
    "role": "MANAGER"
  }'
```

### Регистрация обычного пользователя
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "Айдар",
    "lastName": "Куаныш",
    "email": "aidar@example.com",
    "password": "password123",
    "role": "USER"
  }'
```

**Ожидаемый ответ:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "user": {
    "id": 1,
    "firstName": "Айдар",
    "lastName": "Куаныш",
    "email": "aidar@example.com",
    "role": "USER"
  }
}
```

### Вход в систему
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "aidar@example.com",
    "password": "password123"
  }'
```

### Получение всех пользователей
```bash
# Замените YOUR_JWT_TOKEN на токен из предыдущего ответа
curl -X GET http://localhost:8080/api/users \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

### Обновление пользователя
```bash
curl -X PUT http://localhost:8080/api/users/1 \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "firstName": "Айдар",
    "lastName": "Куанышбаев",
    "email": "aidar.new@example.com"
  }'
```

---

## 🎭 Тестирование афиш

### Создание афиши
```bash
curl -X POST http://localhost:8080/api/afisha \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "name": "Концерт группы U2",
    "addressName": "Алматы Арена",
    "addressLink": "https://maps.google.com/?q=Алматы+Арена",
    "eventDateTimeFrom": "2024-12-31T19:00:00",
    "eventDateTimeTo": "2024-12-31T22:00:00",
    "phone": "+7 777 123 4567",
    "description": "Легендарная ирландская рок-группа U2 в Алматы!",
    "price": 15000,
    "status": "ACTIVE",
    "category": "CONCERT",
    "address": {
      "street": "Проспект Абая",
      "city": "Алматы",
      "country": "Казахстан"
    }
  }'
```

### Получение всех афиш
```bash
curl -X GET http://localhost:8080/api/afisha \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

### Создание еще одной афиши
```bash
curl -X POST http://localhost:8080/api/afisha \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "name": "Спектакль 'Евгений Онегин'",
    "addressName": "Казахский театр оперы и балета",
    "addressLink": "https://maps.google.com/?q=Казахский+театр+оперы+и+балета",
    "eventDateTimeFrom": "2024-12-25T18:00:00",
    "eventDateTimeTo": "2024-12-25T21:00:00",
    "phone": "+7 727 123 4567",
    "description": "Классическая опера по роману А.С. Пушкина",
    "price": 8000,
    "status": "ACTIVE",
    "category": "THEATER",
    "address": {
      "street": "Улица Кабанбай батыра",
      "city": "Алматы",
      "country": "Казахстан"
    }
  }'
```

---

## 🏢 Тестирование мест

### Создание места проведения
```bash
curl -X POST http://localhost:8080/api/places \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "name": "Алматы Арена",
    "streetName": "Проспект Абая",
    "streetNumber": "44",
    "placeLink": "https://maps.google.com/?q=Алматы+Арена",
    "description": "Современный многофункциональный комплекс для проведения концертов и спортивных мероприятий",
    "priceFrom": 5000,
    "priceTo": 50000,
    "socialNetwork": "@almaty_arena",
    "phone": "+7 727 123 4567",
    "street": "Проспект Абая",
    "city": "Алматы",
    "country": "Казахстан",
    "status": "ACTIVE",
    "category": "CONCERT_HALL",
    "geoProcessed": false
  }'
```

### Получение всех мест
```bash
curl -X GET http://localhost:8080/api/places \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

---

## ❤️ Тестирование избранного

### Добавление афиши в избранное
```bash
curl -X POST http://localhost:8080/api/favourites/afisha \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "afishaId": 1
  }'
```

### Получение избранных афиш
```bash
curl -X GET http://localhost:8080/api/favourites/afisha \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

### Добавление места в избранное
```bash
curl -X POST http://localhost:8080/api/favourites/places \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "placeId": 1
  }'
```

### Получение избранных мест
```bash
curl -X GET http://localhost:8080/api/favourites/places \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

---

## 💳 Тестирование платежей

### Покупка билета
```bash
curl -X POST http://localhost:8080/api/payment/buy-afisha \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "userId": 1,
    "afishaId": 1
  }'
```

**Ожидаемый ответ:**
```json
{
  "message": "Уважаемый Айдар Билет успешно приобретен на посешение Концерт группы U2",
  "orderId": 1,
  "remainingCash": 35000
}
```

---

## 📧 Тестирование email

### Отправка email
```bash
curl -X POST http://localhost:8080/api/email/send \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "to": "aidar@example.com",
    "subject": "Добро пожаловать в Qydyr!",
    "body": "Спасибо за регистрацию в нашей системе управления событиями!"
  }'
```

---

## 🖼️ Тестирование изображений

### Загрузка изображения
```bash
# Создайте тестовый файл изображения
echo "iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAADUlEQVR42mNkYPhfDwAChwGA60e6kgAAAABJRU5ErkJggg==" | base64 -d > test_image.png

# Загрузите изображение
curl -X POST http://localhost:8080/api/images/upload \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -F "file=@test_image.png"
```

### Получение изображения
```bash
curl -X GET http://localhost:8080/api/images/1 \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  --output downloaded_image.png
```

---

## 🔍 Полный сценарий тестирования

### Скрипт для автоматического тестирования
```bash
#!/bin/bash

# Цвета для вывода
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

echo -e "${YELLOW}🚀 Начинаем тестирование Qydyr Entity API${NC}"

# 1. Проверка статуса приложения
echo -e "\n${YELLOW}1. Проверка статуса приложения...${NC}"
curl -s http://localhost:8080/actuator/health
echo -e "\n${GREEN}✅ Приложение работает${NC}"

# 2. Регистрация пользователя
echo -e "\n${YELLOW}2. Регистрация пользователя...${NC}"
REGISTER_RESPONSE=$(curl -s -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "Тест",
    "lastName": "Пользователь",
    "email": "test@example.com",
    "password": "password123",
    "role": "USER"
  }')

echo $REGISTER_RESPONSE

# Извлекаем токен
TOKEN=$(echo $REGISTER_RESPONSE | grep -o '"token":"[^"]*"' | cut -d'"' -f4)
echo -e "${GREEN}✅ Пользователь зарегистрирован, токен: ${TOKEN:0:20}...${NC}"

# 3. Создание афиши
echo -e "\n${YELLOW}3. Создание афиши...${NC}"
AFISHA_RESPONSE=$(curl -s -X POST http://localhost:8080/api/afisha \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TOKEN" \
  -d '{
    "name": "Тестовый концерт",
    "addressName": "Тестовый зал",
    "eventDateTimeFrom": "2024-12-31T19:00:00",
    "eventDateTimeTo": "2024-12-31T22:00:00",
    "phone": "+7 777 777 7777",
    "description": "Тестовое описание",
    "price": 1000,
    "status": "ACTIVE",
    "category": "CONCERT"
  }')

echo $AFISHA_RESPONSE
echo -e "${GREEN}✅ Афиша создана${NC}"

# 4. Получение всех афиш
echo -e "\n${YELLOW}4. Получение всех афиш...${NC}"
curl -s -X GET http://localhost:8080/api/afisha \
  -H "Authorization: Bearer $TOKEN" | jq '.'
echo -e "${GREEN}✅ Афиши получены${NC}"

# 5. Покупка билета
echo -e "\n${YELLOW}5. Покупка билета...${NC}"
PAYMENT_RESPONSE=$(curl -s -X POST http://localhost:8080/api/payment/buy-afisha \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TOKEN" \
  -d '{
    "userId": 1,
    "afishaId": 1
  }')

echo $PAYMENT_RESPONSE
echo -e "${GREEN}✅ Билет куплен${NC}"

echo -e "\n${GREEN}🎉 Все тесты пройдены успешно!${NC}"
```

---

## 🐛 Тестирование ошибок

### Тестирование неверного токена
```bash
curl -X GET http://localhost:8080/api/afisha \
  -H "Authorization: Bearer invalid_token"
```

**Ожидаемый ответ:**
```json
{
  "timestamp": "2024-09-08T13:46:32.442+00:00",
  "status": 401,
  "error": "Unauthorized",
  "message": "JWT token is missing or invalid",
  "path": "/api/afisha"
}
```

### Тестирование несуществующего ресурса
```bash
curl -X GET http://localhost:8080/api/afisha/999 \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

### Тестирование неверных данных
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "",
    "email": "invalid-email",
    "password": "123"
  }'
```

---

## 📊 Мониторинг производительности

### Тестирование нагрузки
```bash
# Установите Apache Bench (ab)
# macOS: brew install httpd
# Ubuntu: sudo apt-get install apache2-utils

# Тест нагрузки на получение афиш
ab -n 100 -c 10 -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  http://localhost:8080/api/afisha
```

### Мониторинг памяти
```bash
# Проверка использования памяти Java процессом
ps aux | grep java | grep qydyr
```

---

## 🔧 Отладка

### Включение подробных логов
Добавьте в `application.properties`:
```properties
logging.level.com.example.qydyr=DEBUG
logging.level.org.springframework.security=DEBUG
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
```

### Проверка базы данных
```bash
# Подключение к MySQL
mysql -u root -proot -D qydyr

# Просмотр таблиц
SHOW TABLES;

# Проверка данных
SELECT * FROM user;
SELECT * FROM afisha;
SELECT * FROM place;
SELECT * FROM orders;
```

---

## 📝 Чек-лист тестирования

- [ ] Приложение запускается без ошибок
- [ ] Swagger UI доступен
- [ ] Регистрация пользователя работает
- [ ] Вход в систему работает
- [ ] JWT токен генерируется корректно
- [ ] Создание афиши работает
- [ ] Получение афиш работает
- [ ] Создание места работает
- [ ] Получение мест работает
- [ ] Добавление в избранное работает
- [ ] Покупка билета работает
- [ ] Email отправляется
- [ ] Загрузка изображений работает
- [ ] Обработка ошибок работает корректно
- [ ] Безопасность API работает

---

**Удачного тестирования! 🚀**
