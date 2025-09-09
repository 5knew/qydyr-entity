# 🚀 Быстрый старт - Qydyr Entity

## ✅ Статус системы
- **Сервер**: ✅ Запущен на http://localhost:8080
- **База данных**: ✅ MySQL подключена (root/root)
- **API документация**: ✅ Доступна на http://localhost:8080/swagger-ui/index.html

---

## 🎯 Первые шаги

### 1. Откройте API документацию
Перейдите в браузере по адресу:
```
http://localhost:8080/swagger-ui/index.html
```

### 2. Зарегистрируйте пользователя
В Swagger UI найдите раздел **Authentication** и выполните:

**POST /api/auth/register**
```json
{
  "firstName": "Ваше имя",
  "lastName": "Ваша фамилия",
  "email": "your-email@example.com",
  "password": "password123",
  "role": "USER"
}
```

### 3. Войдите в систему
**POST /api/auth/login**
```json
{
  "email": "your-email@example.com",
  "password": "password123"
}
```

**Скопируйте токен из ответа!**

### 4. Настройте авторизацию
В Swagger UI:
1. Нажмите кнопку **"Authorize"** (🔒)
2. Введите: `Bearer YOUR_JWT_TOKEN`
3. Нажмите **"Authorize"**

---

## 🎭 Создание первой афиши

**POST /api/afisha**
```json
{
  "name": "Мой первый концерт",
  "addressName": "Концертный зал",
  "addressLink": "https://maps.google.com/...",
  "eventDateTimeFrom": "2024-12-31T19:00:00",
  "eventDateTimeTo": "2024-12-31T22:00:00",
  "phone": "+7 777 777 7777",
  "description": "Описание моего концерта",
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

---

## 🏢 Создание места проведения

**POST /api/places**
```json
{
  "name": "Мой концертный зал",
  "streetName": "Проспект Абая",
  "streetNumber": "44",
  "placeLink": "https://maps.google.com/...",
  "description": "Описание моего зала",
  "priceFrom": 1000,
  "priceTo": 10000,
  "socialNetwork": "@my_venue",
  "phone": "+7 777 123 4567",
  "street": "Проспект Абая",
  "city": "Алматы",
  "country": "Казахстан",
  "status": "ACTIVE",
  "category": "CONCERT_HALL",
  "geoProcessed": false
}
```

---

## 💳 Покупка билета

**POST /api/payment/buy-afisha**
```json
{
  "userId": 1,
  "afishaId": 1
}
```

---

## ❤️ Добавление в избранное

### Афиша
**POST /api/favourites/afisha**
```json
{
  "afishaId": 1
}
```

### Место
**POST /api/favourites/places**
```json
{
  "placeId": 1
}
```

---

## 🖼️ Загрузка изображения

**POST /api/images/upload**
- Выберите файл изображения
- Нажмите "Execute"

---

## 📧 Отправка email

**POST /api/email/send**
```json
{
  "to": "recipient@example.com",
  "subject": "Привет из Qydyr!",
  "body": "Это тестовое сообщение из системы Qydyr Entity."
}
```

---

## 🔍 Просмотр данных

### Все афиши
**GET /api/afisha**

### Все места
**GET /api/places**

### Все пользователи
**GET /api/users**

### Избранные афиши
**GET /api/favourites/afisha**

### Избранные места
**GET /api/favourites/places**

---

## 🛠️ Полезные команды

### Проверка статуса сервера
```bash
curl http://localhost:8080/actuator/health
```

### Проверка портов
```bash
lsof -i :8080  # Приложение
lsof -i :3306  # MySQL
```

### Просмотр логов
```bash
# В терминале где запущено приложение
# Логи отображаются автоматически
```

### Остановка сервера
```bash
# Найти процесс
ps aux | grep java

# Остановить (замените PID)
kill PID_NUMBER
```

### Перезапуск сервера
```bash
# Остановить
pkill -f "spring-boot:run"

# Запустить
./mvnw spring-boot:run
```

---

## 📚 Дополнительная документация

- **Полная документация**: `README.md`
- **API документация**: `API_DOCUMENTATION.md`
- **Примеры тестирования**: `TEST_EXAMPLES.md`
- **Инструкции по развертыванию**: `DEPLOYMENT.md`

---

## 🆘 Если что-то не работает

### Сервер не запускается
1. Проверьте, что MySQL запущен: `brew services list | grep mysql`
2. Проверьте, что порт 8080 свободен: `lsof -i :8080`
3. Проверьте логи в терминале

### Ошибка базы данных
1. Проверьте подключение: `mysql -u root -proot -e "SELECT 1"`
2. Проверьте, что база `qydyr` существует
3. Перезапустите MySQL: `brew services restart mysql`

### Ошибка авторизации
1. Убедитесь, что используете правильный токен
2. Проверьте формат: `Bearer YOUR_JWT_TOKEN`
3. Попробуйте заново войти в систему

### Swagger UI не открывается
1. Проверьте, что сервер запущен
2. Попробуйте: http://localhost:8080/swagger-ui/
3. Очистите кэш браузера

---

## 🎉 Готово!

Теперь вы можете:
- ✅ Регистрировать пользователей
- ✅ Создавать афиши и места
- ✅ Покупать билеты
- ✅ Управлять избранным
- ✅ Загружать изображения
- ✅ Отправлять email

**Удачного использования Qydyr Entity! 🚀**

---

**Нужна помощь?** Обратитесь к полной документации в файлах README.md и API_DOCUMENTATION.md
