# База данных Qydyr Entity - Дампы

## Описание файлов

### 1. qydyr_database_dump.sql (38 KB)
**Полный дамп базы данных** - содержит структуру таблиц и все данные
- Включает: CREATE TABLE, INSERT INTO, индексы, ограничения
- Используется для: полного восстановления базы данных

### 2. qydyr_schema_only.sql (8 KB)
**Только структура базы данных** - содержит только схему без данных
- Включает: CREATE TABLE, индексы, ограничения
- Используется для: создания пустой базы данных с правильной структурой

### 3. qydyr_data_only.sql (31 KB)
**Только данные** - содержит только INSERT INTO без структуры
- Включает: INSERT INTO для всех таблиц
- Используется для: восстановления данных в существующую базу

## Информация о базе данных

- **Название базы**: `qydyr`
- **Хост**: `localhost:3306`
- **Пользователь**: `root`
- **Пароль**: `root`
- **Версия MySQL**: 9.4.0

## Таблицы в базе данных

1. `afisha` - события/афиши
2. `favorite_afishes` - избранные афиши пользователей
3. `favorite_places` - избранные места пользователей
4. `hibernate_sequence` - последовательности Hibernate
5. `images` - изображения
6. `orders` - заказы билетов
7. `place` - места проведения мероприятий
8. `user` - пользователи

## Восстановление базы данных

### Вариант 1: Полное восстановление
```bash
mysql -u root -p -e "CREATE DATABASE qydyr;"
mysql -u root -p qydyr < qydyr_database_dump.sql
```

### Вариант 2: Восстановление структуры + данных
```bash
mysql -u root -p -e "CREATE DATABASE qydyr;"
mysql -u root -p qydyr < qydyr_schema_only.sql
mysql -u root -p qydyr < qydyr_data_only.sql
```

### Вариант 3: Только структура (пустая база)
```bash
mysql -u root -p -e "CREATE DATABASE qydyr;"
mysql -u root -p qydyr < qydyr_schema_only.sql
```

## Проверка восстановления

```bash
# Подключение к базе данных
mysql -u root -p qydyr

# Проверка таблиц
SHOW TABLES;

# Проверка количества записей
SELECT 
    'afisha' as table_name, COUNT(*) as records FROM afisha
UNION ALL
SELECT 'user', COUNT(*) FROM user
UNION ALL
SELECT 'place', COUNT(*) FROM place
UNION ALL
SELECT 'orders', COUNT(*) FROM orders;
```

## Дата создания дампов
**10 сентября 2025, 00:35**

## Примечания
- Все дампы созданы с кодировкой UTF-8
- Включены все ограничения внешних ключей
- Сохранены все индексы и последовательности
- Данные пользователей включают зашифрованные пароли
