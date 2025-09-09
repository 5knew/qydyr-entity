# 🔧 Исправление ошибки входа в админ-панель

## ❌ Проблема
```
{
  "path": "/admin/login",
  "error": "Internal Server Error",
  "message": "Request method 'POST' not supported",
  "timestamp": "2025-09-08T17:31:36.692070Z",
  "status": 500
}
```

## 🔍 Причина
Spring Security не был настроен для обработки веб-форм аутентификации. Контроллер имел только GET метод для отображения формы, но не POST для обработки данных.

## ✅ Решение

### 1. Обновлена конфигурация безопасности
В `SecurityConfiguration.java` добавлена поддержка веб-форм:

```java
.formLogin()
.loginPage("/admin/login")
.defaultSuccessUrl("/admin", true)
.failureUrl("/admin/login?error=true")
.permitAll()
.and()
.logout()
.logoutUrl("/admin/logout")
.logoutSuccessUrl("/admin/login?logout=true")
.permitAll()
```

### 2. Удален лишний POST метод из контроллера
Spring Security теперь сам обрабатывает аутентификацию.

## 🚀 Как использовать

### 1. Перезапустите приложение
```bash
./mvnw spring-boot:run
```

### 2. Создайте администратора (если еще не создан)
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

### 3. Войдите в админ-панель
1. Откройте http://localhost:8080/admin/login
2. Введите:
   - **Email**: `admin@example.com`
   - **Пароль**: `admin123`
3. Нажмите "Войти"

### 4. Вы будете перенаправлены на дашборд
- URL: http://localhost:8080/admin
- Доступны все функции админ-панели

## 🔧 Дополнительные настройки

### Обработка ошибок
- При неверном пароле: `/admin/login?error=true`
- После выхода: `/admin/login?logout=true`

### Безопасность
- Только пользователи с ролью ADMIN могут получить доступ
- Сессии создаются при необходимости
- Автоматический выход при неактивности

## 🧪 Тестирование

### Проверка входа
1. Откройте http://localhost:8080/admin/login
2. Введите неверные данные → должна появиться ошибка
3. Введите правильные данные → перенаправление на дашборд

### Проверка доступа
1. Попробуйте открыть http://localhost:8080/admin без входа
2. Должно произойти перенаправление на страницу входа

## 📚 Связанные файлы

- `SecurityConfiguration.java` - конфигурация безопасности
- `AdminController.java` - контроллер админ-панели
- `admin/login.html` - форма входа
- `admin/dashboard.html` - дашборд администратора

---

**Готово!** Теперь вход в админ-панель должен работать корректно! 🎉
