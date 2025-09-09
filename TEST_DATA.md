# 🧪 Тестовые данные - Qydyr Entity

## 📋 Обзор

Этот документ содержит полный набор тестовых данных для системы Qydyr Entity, включая пользователей, афиши, места проведения и заказы.

---

## 👤 Тестовые пользователи

### Администратор
```json
{
  "firstName": "Админ",
  "lastName": "Системы",
  "email": "admin@example.com",
  "password": "admin123",
  "role": "ADMIN",
  "cash": "100000"
}
```

### Менеджер
```json
{
  "firstName": "Менеджер",
  "lastName": "Событий",
  "email": "manager@example.com",
  "password": "manager123",
  "role": "MANAGER",
  "cash": "50000"
}
```

### Обычные пользователи
```json
[
  {
    "firstName": "Айдар",
    "lastName": "Куаныш",
    "email": "aidar@example.com",
    "password": "password123",
    "role": "USER",
    "cash": "25000"
  },
  {
    "firstName": "Айжан",
    "lastName": "Нурланова",
    "email": "aizhan@example.com",
    "password": "password123",
    "role": "USER",
    "cash": "30000"
  },
  {
    "firstName": "Данияр",
    "lastName": "Ахметов",
    "email": "daniyar@example.com",
    "password": "password123",
    "role": "USER",
    "cash": "20000"
  },
  {
    "firstName": "Жанар",
    "lastName": "Серик",
    "email": "zhanar@example.com",
    "password": "password123",
    "role": "USER",
    "cash": "35000"
  }
]
```

---

## 🎭 Тестовые афиши (события)

### Концерты
```json
[
  {
    "name": "Концерт группы U2",
    "addressName": "Алматы Арена",
    "addressLink": "https://maps.google.com/?q=Алматы+Арена",
    "eventDateTimeFrom": "2024-12-31T19:00:00",
    "eventDateTimeTo": "2024-12-31T22:00:00",
    "phone": "+7 777 123 4567",
    "description": "Легендарная ирландская рок-группа U2 в Алматы! Невероятное шоу с лучшими хитами всех времен.",
    "price": 15000,
    "status": "ACTIVE",
    "category": "CONCERT",
    "address": {
      "street": "Проспект Абая",
      "city": "Алматы",
      "country": "Казахстан"
    }
  },
  {
    "name": "Джаз-концерт 'Ночь в Париже'",
    "addressName": "Казахская государственная филармония",
    "addressLink": "https://maps.google.com/?q=Казахская+государственная+филармония",
    "eventDateTimeFrom": "2024-12-20T20:00:00",
    "eventDateTimeTo": "2024-12-20T23:00:00",
    "phone": "+7 727 123 4567",
    "description": "Романтический джаз-вечер с лучшими музыкантами города. Французские мелодии и атмосфера Парижа.",
    "price": 8000,
    "status": "ACTIVE",
    "category": "CONCERT",
    "address": {
      "street": "Улица Кабанбай батыра",
      "city": "Алматы",
      "country": "Казахстан"
    }
  },
  {
    "name": "Рок-фестиваль 'Звуки Алматы'",
    "addressName": "Парк имени 28 гвардейцев-панфиловцев",
    "addressLink": "https://maps.google.com/?q=Парк+28+гвардейцев+панфиловцев",
    "eventDateTimeFrom": "2024-12-15T16:00:00",
    "eventDateTimeTo": "2024-12-15T23:00:00",
    "phone": "+7 777 987 6543",
    "description": "Масштабный рок-фестиваль с участием лучших казахстанских и зарубежных групп. Еда, напитки, музыка!",
    "price": 5000,
    "status": "ACTIVE",
    "category": "CONCERT",
    "address": {
      "street": "Улица Гоголя",
      "city": "Алматы",
      "country": "Казахстан"
    }
  }
]
```

### Театральные постановки
```json
[
  {
    "name": "Спектакль 'Евгений Онегин'",
    "addressName": "Казахский театр оперы и балета",
    "addressLink": "https://maps.google.com/?q=Казахский+театр+оперы+и+балета",
    "eventDateTimeFrom": "2024-12-25T18:00:00",
    "eventDateTimeTo": "2024-12-25T21:00:00",
    "phone": "+7 727 123 4567",
    "description": "Классическая опера по роману А.С. Пушкина. Великолепные декорации, костюмы и музыка Чайковского.",
    "price": 12000,
    "status": "ACTIVE",
    "category": "THEATRE",
    "address": {
      "street": "Улица Кабанбай батыра",
      "city": "Алматы",
      "country": "Казахстан"
    }
  },
  {
    "name": "Драма 'Король Лир'",
    "addressName": "Русский театр драмы имени Лермонтова",
    "addressLink": "https://maps.google.com/?q=Русский+театр+драмы+Лермонтова",
    "eventDateTimeFrom": "2024-12-18T19:30:00",
    "eventDateTimeTo": "2024-12-18T22:30:00",
    "phone": "+7 727 234 5678",
    "description": "Трагедия Шекспира в современной интерпретации. Мощная игра актеров и глубокий смысл.",
    "price": 6000,
    "status": "ACTIVE",
    "category": "THEATRE",
    "address": {
      "street": "Проспект Абая",
      "city": "Алматы",
      "country": "Казахстан"
    }
  }
]
```

### Кино и развлечения
```json
[
  {
    "name": "Премьера фильма 'Астана'",
    "addressName": "Кинотеатр 'Арман'",
    "addressLink": "https://maps.google.com/?q=Кинотеатр+Арман",
    "eventDateTimeFrom": "2024-12-22T20:00:00",
    "eventDateTimeTo": "2024-12-22T22:30:00",
    "phone": "+7 727 345 6789",
    "description": "Премьерный показ нового казахстанского фильма с участием звезд кино. Встреча с режиссером после сеанса.",
    "price": 3000,
    "status": "ACTIVE",
    "category": "CINEMA",
    "address": {
      "street": "Улица Сатпаева",
      "city": "Алматы",
      "country": "Казахстан"
    }
  },
  {
    "name": "Марафон 'Алматы 2024'",
    "addressName": "Центральный парк культуры и отдыха",
    "addressLink": "https://maps.google.com/?q=Центральный+парк+Алматы",
    "eventDateTimeFrom": "2024-12-10T08:00:00",
    "eventDateTimeTo": "2024-12-10T12:00:00",
    "phone": "+7 777 456 7890",
    "description": "Ежегодный марафон по улицам Алматы. Дистанции: 5км, 10км, 21км, 42км. Регистрация обязательна.",
    "price": 2000,
    "status": "ACTIVE",
    "category": "MARATHON",
    "address": {
      "street": "Улица Гоголя",
      "city": "Алматы",
      "country": "Казахстан"
    }
  },
  {
    "name": "Семинар 'Цифровая трансформация'",
    "addressName": "Конференц-зал 'Астана'",
    "addressLink": "https://maps.google.com/?q=Конференц+зал+Астана",
    "eventDateTimeFrom": "2024-12-28T10:00:00",
    "eventDateTimeTo": "2024-12-28T17:00:00",
    "phone": "+7 727 567 8901",
    "description": "Однодневный семинар по цифровой трансформации бизнеса. Спикеры из IT-компаний Казахстана и зарубежья.",
    "price": 15000,
    "status": "ACTIVE",
    "category": "SEMINAR",
    "address": {
      "street": "Проспект Назарбаева",
      "city": "Алматы",
      "country": "Казахстан"
    }
  }
]
```

---

## 🏢 Тестовые места проведения

### Концертные залы
```json
[
  {
    "name": "Алматы Арена",
    "streetName": "Проспект Абая",
    "streetNumber": "44",
    "placeLink": "https://maps.google.com/?q=Алматы+Арена",
    "description": "Современный многофункциональный комплекс для проведения концертов и спортивных мероприятий. Вместимость до 15,000 человек.",
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
  },
  {
    "name": "Казахская государственная филармония",
    "streetName": "Улица Кабанбай батыра",
    "streetNumber": "110",
    "placeLink": "https://maps.google.com/?q=Казахская+государственная+филармония",
    "description": "Историческое здание с отличной акустикой. Идеально для классической музыки и камерных концертов.",
    "priceFrom": 2000,
    "priceTo": 15000,
    "socialNetwork": "@kazphilharmonic",
    "phone": "+7 727 234 5678",
    "street": "Улица Кабанбай батыра",
    "city": "Алматы",
    "country": "Казахстан",
    "status": "ACTIVE",
    "category": "CONCERT_HALL",
    "geoProcessed": false
  }
]
```

### Театры
```json
[
  {
    "name": "Казахский театр оперы и балета",
    "streetName": "Улица Кабанбай батыра",
    "streetNumber": "110",
    "placeLink": "https://maps.google.com/?q=Казахский+театр+оперы+и+балета",
    "description": "Главный театр оперы и балета Казахстана. Великолепная архитектура и профессиональная труппа.",
    "priceFrom": 3000,
    "priceTo": 20000,
    "socialNetwork": "@kazopera",
    "phone": "+7 727 345 6789",
    "street": "Улица Кабанбай батыра",
    "city": "Алматы",
    "country": "Казахстан",
    "status": "ACTIVE",
    "category": "THEATER_HALL",
    "geoProcessed": false
  },
  {
    "name": "Русский театр драмы имени Лермонтова",
    "streetName": "Проспект Абая",
    "streetNumber": "43",
    "placeLink": "https://maps.google.com/?q=Русский+театр+драмы+Лермонтова",
    "description": "Один из старейших театров Алматы. Классические и современные постановки.",
    "priceFrom": 1500,
    "priceTo": 8000,
    "socialNetwork": "@lermontov_theater",
    "phone": "+7 727 456 7890",
    "street": "Проспект Абая",
    "city": "Алматы",
    "country": "Казахстан",
    "status": "ACTIVE",
    "category": "THEATER_HALL",
    "geoProcessed": false
  }
]
```

### Кинотеатры и развлекательные центры
```json
[
  {
    "name": "Кинотеатр 'Арман'",
    "streetName": "Улица Сатпаева",
    "streetNumber": "90/22",
    "placeLink": "https://maps.google.com/?q=Кинотеатр+Арман",
    "description": "Современный кинотеатр с 8 залами, IMAX и 4DX технологиями. Попкорн и напитки включены.",
    "priceFrom": 1000,
    "priceTo": 5000,
    "socialNetwork": "@arman_cinema",
    "phone": "+7 727 567 8901",
    "street": "Улица Сатпаева",
    "city": "Алматы",
    "country": "Казахстан",
    "status": "ACTIVE",
    "category": "CINEMA",
    "geoProcessed": false
  },
  {
    "name": "Центральный парк культуры и отдыха",
    "streetName": "Улица Гоголя",
    "streetNumber": "1",
    "placeLink": "https://maps.google.com/?q=Центральный+парк+Алматы",
    "description": "Большой парк в центре города. Идеально для массовых мероприятий, фестивалей и спортивных событий.",
    "priceFrom": 500,
    "priceTo": 3000,
    "socialNetwork": "@central_park_almaty",
    "phone": "+7 727 678 9012",
    "street": "Улица Гоголя",
    "city": "Алматы",
    "country": "Казахстан",
    "status": "ACTIVE",
    "category": "PARK",
    "geoProcessed": false
  }
]
```

### Конференц-залы
```json
[
  {
    "name": "Конференц-зал 'Астана'",
    "streetName": "Проспект Назарбаева",
    "streetNumber": "56",
    "placeLink": "https://maps.google.com/?q=Конференц+зал+Астана",
    "description": "Современный конференц-зал с полным техническим оснащением. Подходит для семинаров, конференций и корпоративных мероприятий.",
    "priceFrom": 10000,
    "priceTo": 50000,
    "socialNetwork": "@astana_conference",
    "phone": "+7 727 789 0123",
    "street": "Проспект Назарбаева",
    "city": "Алматы",
    "country": "Казахстан",
    "status": "ACTIVE",
    "category": "CONFERENCE_HALL",
    "geoProcessed": false
  }
]
```

---

## 💳 Тестовые заказы

```json
[
  {
    "userId": 1,
    "afishaId": 1,
    "count": 2,
    "status": "PURCHASED"
  },
  {
    "userId": 2,
    "afishaId": 2,
    "count": 1,
    "status": "PURCHASED"
  },
  {
    "userId": 3,
    "afishaId": 3,
    "count": 4,
    "status": "PURCHASED"
  },
  {
    "userId": 1,
    "afishaId": 4,
    "count": 1,
    "status": "PURCHASED"
  },
  {
    "userId": 4,
    "afishaId": 5,
    "count": 2,
    "status": "PURCHASED"
  }
]
```

---

## 🖼️ Тестовые изображения

### Примеры изображений для афиш
```json
[
  {
    "name": "u2_concert_poster.jpg",
    "originalFileName": "U2_Concert_Poster.jpg",
    "contentType": "image/jpeg",
    "size": 2048000,
    "previewImage": true
  },
  {
    "name": "jazz_night_poster.jpg",
    "originalFileName": "Jazz_Night_Poster.jpg",
    "contentType": "image/jpeg",
    "size": 1536000,
    "previewImage": true
  },
  {
    "name": "onegin_play_poster.jpg",
    "originalFileName": "Onegin_Play_Poster.jpg",
    "contentType": "image/jpeg",
    "size": 1792000,
    "previewImage": true
  }
]
```

### Примеры изображений для мест
```json
[
  {
    "name": "almaty_arena_exterior.jpg",
    "originalFileName": "Almaty_Arena_Exterior.jpg",
    "contentType": "image/jpeg",
    "size": 2560000,
    "previewImage": true
  },
  {
    "name": "philharmonic_interior.jpg",
    "originalFileName": "Philharmonic_Interior.jpg",
    "contentType": "image/jpeg",
    "size": 1920000,
    "previewImage": true
  }
]
```

---

## 📧 Тестовые email сообщения

### Приветственные письма
```json
[
  {
    "to": "aidar@example.com",
    "subject": "Добро пожаловать в Qydyr!",
    "body": "Спасибо за регистрацию в нашей системе управления событиями! Теперь вы можете покупать билеты, добавлять события в избранное и многое другое."
  },
  {
    "to": "aizhan@example.com",
    "subject": "Новые события в Алматы",
    "body": "У нас появились новые интересные события! Концерт U2, джаз-вечер и многое другое. Посмотрите на нашем сайте."
  }
]
```

### Уведомления о покупке
```json
[
  {
    "to": "aidar@example.com",
    "subject": "Билеты успешно приобретены!",
    "body": "Ваши билеты на концерт U2 успешно приобретены. Дата: 31 декабря 2024, время: 19:00. Место: Алматы Арена."
  },
  {
    "to": "aizhan@example.com",
    "subject": "Подтверждение заказа",
    "body": "Ваш заказ на джаз-концерт 'Ночь в Париже' подтвержден. Ждем вас 20 декабря в 20:00 в Казахской государственной филармонии."
  }
]
```

---

## 🔧 SQL скрипты для заполнения БД

### Создание тестовых пользователей
```sql
-- Администратор
INSERT INTO user (first_name, last_name, username, email, password, role, cash) 
VALUES ('Админ', 'Системы', 'admin', 'admin@example.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDi', 'ADMIN', '100000');

-- Менеджер
INSERT INTO user (first_name, last_name, username, email, password, role, cash) 
VALUES ('Менеджер', 'Событий', 'manager', 'manager@example.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDi', 'MANAGER', '50000');

-- Обычные пользователи
INSERT INTO user (first_name, last_name, username, email, password, role, cash) 
VALUES 
('Айдар', 'Куаныш', 'aidar', 'aidar@example.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDi', 'USER', '25000'),
('Айжан', 'Нурланова', 'aizhan', 'aizhan@example.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDi', 'USER', '30000'),
('Данияр', 'Ахметов', 'daniyar', 'daniyar@example.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDi', 'USER', '20000'),
('Жанар', 'Серик', 'zhanar', 'zhanar@example.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDi', 'USER', '35000');
```

### Создание тестовых мест
```sql
INSERT INTO place (name, street_name, street_number, place_link, description, price_from, price_to, social_network, phone, street, city, country, status, category, geo_processed, deleted) 
VALUES 
('Алматы Арена', 'Проспект Абая', '44', 'https://maps.google.com/?q=Алматы+Арена', 'Современный многофункциональный комплекс для проведения концертов и спортивных мероприятий. Вместимость до 15,000 человек.', 5000, 50000, '@almaty_arena', '+7 727 123 4567', 'Проспект Абая', 'Алматы', 'Казахстан', 'ACTIVE', 'CONCERT_HALL', false, false),
('Казахская государственная филармония', 'Улица Кабанбай батыра', '110', 'https://maps.google.com/?q=Казахская+государственная+филармония', 'Историческое здание с отличной акустикой. Идеально для классической музыки и камерных концертов.', 2000, 15000, '@kazphilharmonic', '+7 727 234 5678', 'Улица Кабанбай батыра', 'Алматы', 'Казахстан', 'ACTIVE', 'CONCERT_HALL', false, false),
('Казахский театр оперы и балета', 'Улица Кабанбай батыра', '110', 'https://maps.google.com/?q=Казахский+театр+оперы+и+балета', 'Главный театр оперы и балета Казахстана. Великолепная архитектура и профессиональная труппа.', 3000, 20000, '@kazopera', '+7 727 345 6789', 'Улица Кабанбай батыра', 'Алматы', 'Казахстан', 'ACTIVE', 'THEATER_HALL', false, false);
```

### Создание тестовых афиш
```sql
INSERT INTO afisha (name, address_name, address_link, created_date_time, event_date_time_from, event_date_time_to, phone, description, price, status, category, street, city, country, geo_processed, deleted) 
VALUES 
('Концерт группы U2', 'Алматы Арена', 'https://maps.google.com/?q=Алматы+Арена', NOW(), '2024-12-31 19:00:00', '2024-12-31 22:00:00', '+7 777 123 4567', 'Легендарная ирландская рок-группа U2 в Алматы! Невероятное шоу с лучшими хитами всех времен.', 15000, 'ACTIVE', 'CONCERT', 'Проспект Абая', 'Алматы', 'Казахстан', false, false),
('Джаз-концерт ''Ночь в Париже''', 'Казахская государственная филармония', 'https://maps.google.com/?q=Казахская+государственная+филармония', NOW(), '2024-12-20 20:00:00', '2024-12-20 23:00:00', '+7 727 123 4567', 'Романтический джаз-вечер с лучшими музыкантами города. Французские мелодии и атмосфера Парижа.', 8000, 'ACTIVE', 'CONCERT', 'Улица Кабанбай батыра', 'Алматы', 'Казахстан', false, false),
('Спектакль ''Евгений Онегин''', 'Казахский театр оперы и балета', 'https://maps.google.com/?q=Казахский+театр+оперы+и+балета', NOW(), '2024-12-25 18:00:00', '2024-12-25 21:00:00', '+7 727 123 4567', 'Классическая опера по роману А.С. Пушкина. Великолепные декорации, костюмы и музыка Чайковского.', 12000, 'ACTIVE', 'THEATRE', 'Улица Кабанбай батыра', 'Алматы', 'Казахстан', false, false);
```

---

## 🚀 Быстрый старт с тестовыми данными

### 1. Создание администратора через API
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

### 2. Вход в админ-панель
1. Откройте http://localhost:8080/admin/login
2. Введите:
   - Email: `admin@example.com`
   - Пароль: `admin123`
3. Нажмите "Войти"

### 3. Создание тестовых данных через API
```bash
# Получите токен после входа
TOKEN="YOUR_JWT_TOKEN"

# Создайте несколько афиш
curl -X POST http://localhost:8080/api/afisha \
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
  }'
```

---

## 📊 Статистика тестовых данных

- **Пользователи**: 6 (1 админ, 1 менеджер, 4 пользователя)
- **Афиши**: 8 (концерты, театр, кино, марафон, семинар)
- **Места**: 6 (концертные залы, театры, кинотеатр, парк, конференц-зал)
- **Заказы**: 5 (примеры покупок билетов)
- **Изображения**: 5 (постеры и фото мест)

---

## 🔍 Полезные запросы для тестирования

### Получить всех пользователей
```bash
curl -X GET http://localhost:8080/api/users \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

### Получить все афиши
```bash
curl -X GET http://localhost:8080/api/afisha \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

### Получить все места
```bash
curl -X GET http://localhost:8080/api/places \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

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

---

**Примечание**: Все пароли в тестовых данных зашифрованы с помощью BCrypt. В SQL скриптах используется хеш для пароля "password123".

**Последнее обновление**: 2024-09-08
