# API Документация - Qydyr Entity

## 🔗 Базовый URL
```
http://localhost:8080
```

## 🔐 Аутентификация

Все API endpoints (кроме регистрации и входа) требуют JWT токен в заголовке:
```
Authorization: Bearer YOUR_JWT_TOKEN
```

---

## 👤 Пользователи

### Регистрация
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

### Вход
```http
POST /api/auth/login
Content-Type: application/json

{
  "email": "user@example.com",
  "password": "password123"
}
```

**Ответ:** (аналогично регистрации)

### Получить всех пользователей
```http
GET /api/users
Authorization: Bearer YOUR_JWT_TOKEN
```

### Получить пользователя по ID
```http
GET /api/users/{id}
Authorization: Bearer YOUR_JWT_TOKEN
```

### Обновить пользователя
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

---

## 🎭 Афиши (События)

### Получить все афиши
```http
GET /api/afisha
Authorization: Bearer YOUR_JWT_TOKEN
```

**Параметры запроса:**
- `page` - номер страницы (по умолчанию 0)
- `size` - размер страницы (по умолчанию 4)
- `sort` - сортировка (например: `name,asc`)

### Получить афишу по ID
```http
GET /api/afisha/{id}
Authorization: Bearer YOUR_JWT_TOKEN
```

### Создать афишу
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
  "category": "CONCERT",
  "address": {
    "street": "Улица Пушкина",
    "city": "Алматы",
    "country": "Казахстан"
  }
}
```

### Обновить афишу
```http
PUT /api/afisha/{id}
Authorization: Bearer YOUR_JWT_TOKEN
Content-Type: application/json

{
  "name": "Обновленное название",
  "price": 6000,
  "description": "Новое описание"
}
```

### Удалить афишу
```http
DELETE /api/afisha/{id}
Authorization: Bearer YOUR_JWT_TOKEN
```

---

## 🏢 Места проведения

### Получить все места
```http
GET /api/places
Authorization: Bearer YOUR_JWT_TOKEN
```

### Получить место по ID
```http
GET /api/places/{id}
Authorization: Bearer YOUR_JWT_TOKEN
```

### Создать место
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

### Обновить место
```http
PUT /api/places/{id}
Authorization: Bearer YOUR_JWT_TOKEN
Content-Type: application/json

{
  "name": "Новое название места",
  "priceFrom": 1500,
  "priceTo": 6000
}
```

### Удалить место
```http
DELETE /api/places/{id}
Authorization: Bearer YOUR_JWT_TOKEN
```

---

## ❤️ Избранное

### Получить избранные места
```http
GET /api/favourites/places
Authorization: Bearer YOUR_JWT_TOKEN
```

### Добавить место в избранное
```http
POST /api/favourites/places
Authorization: Bearer YOUR_JWT_TOKEN
Content-Type: application/json

{
  "placeId": 1
}
```

### Удалить место из избранного
```http
DELETE /api/favourites/places/{placeId}
Authorization: Bearer YOUR_JWT_TOKEN
```

### Получить избранные афиши
```http
GET /api/favourites/afisha
Authorization: Bearer YOUR_JWT_TOKEN
```

### Добавить афишу в избранное
```http
POST /api/favourites/afisha
Authorization: Bearer YOUR_JWT_TOKEN
Content-Type: application/json

{
  "afishaId": 1
}
```

### Удалить афишу из избранного
```http
DELETE /api/favourites/afisha/{afishaId}
Authorization: Bearer YOUR_JWT_TOKEN
```

---

## 💳 Платежи

### Покупка билета
```http
POST /api/payment/buy-afisha
Authorization: Bearer YOUR_JWT_TOKEN
Content-Type: application/json

{
  "userId": 1,
  "afishaId": 1
}
```

**Ответ:**
```json
{
  "message": "Билет успешно приобретен",
  "orderId": 1,
  "remainingCash": 45000
}
```

---

## 📧 Email

### Отправить email
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

---

## 🖼️ Изображения

### Загрузить изображение
```http
POST /api/images/upload
Authorization: Bearer YOUR_JWT_TOKEN
Content-Type: multipart/form-data

file: [выбранный файл]
```

**Ответ:**
```json
{
  "id": 1,
  "name": "image.jpg",
  "originalFileName": "photo.jpg",
  "contentType": "image/jpeg",
  "size": 1024000,
  "previewImage": true
}
```

### Получить изображение
```http
GET /api/images/{id}
Authorization: Bearer YOUR_JWT_TOKEN
```

---

## 📊 Перечисления (Enums)

### Статусы
- `ACTIVE` - Активный
- `INACTIVE` - Неактивный
- `CANCELLED` - Отменен
- `PURCHASED` - Куплен

### Категории событий
- `CONCERT` - Концерт
- `THEATER` - Театр
- `SPORTS` - Спорт
- `EXHIBITION` - Выставка
- `CONFERENCE` - Конференция
- `FESTIVAL` - Фестиваль

### Категории мест
- `CONCERT_HALL` - Концертный зал
- `THEATER_HALL` - Театральный зал
- `STADIUM` - Стадион
- `EXHIBITION_CENTER` - Выставочный центр
- `CONFERENCE_HALL` - Конференц-зал
- `PARK` - Парк

### Роли пользователей
- `USER` - Пользователь
- `ADMIN` - Администратор

---

## 🚨 Коды ошибок

### HTTP статус коды
- `200` - OK
- `201` - Created
- `400` - Bad Request
- `401` - Unauthorized
- `403` - Forbidden
- `404` - Not Found
- `500` - Internal Server Error

### Примеры ошибок

**Ошибка аутентификации:**
```json
{
  "timestamp": "2024-09-08T13:46:32.442+00:00",
  "status": 401,
  "error": "Unauthorized",
  "message": "JWT token is missing or invalid",
  "path": "/api/afisha"
}
```

**Пользователь не найден:**
```json
{
  "timestamp": "2024-09-08T13:46:32.442+00:00",
  "status": 404,
  "error": "Not Found",
  "message": "User not found with ID: 999",
  "path": "/api/users/999"
}
```

**Недостаточно средств:**
```json
{
  "timestamp": "2024-09-08T13:46:32.442+00:00",
  "status": 400,
  "error": "Bad Request",
  "message": "User does not have enough cash to buy the Afisha",
  "path": "/api/payment/buy-afisha"
}
```

---

## 🔧 Примеры использования

### Полный цикл работы с афишей

1. **Регистрация пользователя**
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "Иван",
    "lastName": "Петров",
    "email": "ivan@example.com",
    "password": "password123",
    "role": "USER"
  }'
```

2. **Вход в систему**
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "ivan@example.com",
    "password": "password123"
  }'
```

3. **Создание афиши**
```bash
curl -X POST http://localhost:8080/api/afisha \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "name": "Концерт группы",
    "addressName": "Концертный зал",
    "eventDateTimeFrom": "2024-12-31T19:00:00",
    "eventDateTimeTo": "2024-12-31T22:00:00",
    "phone": "+7 777 777 7777",
    "description": "Описание концерта",
    "price": 5000,
    "status": "ACTIVE",
    "category": "CONCERT"
  }'
```

4. **Покупка билета**
```bash
curl -X POST http://localhost:8080/api/payment/buy-afisha \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "userId": 1,
    "afishaId": 1
  }'
```

### Работа с избранным

1. **Добавить афишу в избранное**
```bash
curl -X POST http://localhost:8080/api/favourites/afisha \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "afishaId": 1
  }'
```

2. **Получить избранные афиши**
```bash
curl -X GET http://localhost:8080/api/favourites/afisha \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

---

## 📱 Интеграция с фронтендом

### JavaScript/TypeScript пример

```javascript
// Конфигурация API клиента
const API_BASE_URL = 'http://localhost:8080';
let authToken = null;

// Функция для установки токена
function setAuthToken(token) {
  authToken = token;
}

// Универсальная функция для API запросов
async function apiRequest(endpoint, options = {}) {
  const url = `${API_BASE_URL}${endpoint}`;
  const config = {
    headers: {
      'Content-Type': 'application/json',
      ...(authToken && { 'Authorization': `Bearer ${authToken}` })
    },
    ...options
  };

  const response = await fetch(url, config);
  
  if (!response.ok) {
    throw new Error(`HTTP error! status: ${response.status}`);
  }
  
  return response.json();
}

// Примеры использования
async function login(email, password) {
  const response = await apiRequest('/api/auth/login', {
    method: 'POST',
    body: JSON.stringify({ email, password })
  });
  
  setAuthToken(response.token);
  return response;
}

async function getAfisha() {
  return await apiRequest('/api/afisha');
}

async function createAfisha(afishaData) {
  return await apiRequest('/api/afisha', {
    method: 'POST',
    body: JSON.stringify(afishaData)
  });
}
```

### React пример

```jsx
import React, { useState, useEffect } from 'react';

function AfishaList() {
  const [afisha, setAfisha] = useState([]);
  const [loading, setLoading] = useState(true);
  const [token, setToken] = useState(localStorage.getItem('token'));

  useEffect(() => {
    fetchAfisha();
  }, []);

  const fetchAfisha = async () => {
    try {
      const response = await fetch('http://localhost:8080/api/afisha', {
        headers: {
          'Authorization': `Bearer ${token}`
        }
      });
      const data = await response.json();
      setAfisha(data);
    } catch (error) {
      console.error('Error fetching afisha:', error);
    } finally {
      setLoading(false);
    }
  };

  if (loading) return <div>Loading...</div>;

  return (
    <div>
      <h1>Афиши</h1>
      {afisha.map(item => (
        <div key={item.id}>
          <h3>{item.name}</h3>
          <p>{item.description}</p>
          <p>Цена: {item.price} тенге</p>
        </div>
      ))}
    </div>
  );
}

export default AfishaList;
```

---

## 🔍 Отладка и мониторинг

### Проверка статуса приложения
```bash
curl http://localhost:8080/actuator/health
```

### Просмотр логов
```bash
# В терминале где запущено приложение
tail -f logs/application.log

# Или через Maven
./mvnw spring-boot:run | tee app.log
```

### Мониторинг базы данных
```bash
# Подключение к MySQL
mysql -u root -proot -D qydyr

# Просмотр таблиц
SHOW TABLES;

# Просмотр данных
SELECT * FROM user;
SELECT * FROM afisha;
SELECT * FROM place;
```

---

**Последнее обновление:** 2024-09-08  
**Версия API:** 1.0.0
