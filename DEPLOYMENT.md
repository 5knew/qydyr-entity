# Инструкции по развертыванию - Qydyr Entity

## 🚀 Локальное развертывание

### Предварительные требования
- Java 17 или выше
- MySQL 8.0 или выше
- Maven 3.6 или выше
- Git

### Пошаговая установка

#### 1. Клонирование репозитория
```bash
git clone <repository-url>
cd qydyr-entity
```

#### 2. Установка MySQL

**macOS (Homebrew):**
```bash
brew install mysql
brew services start mysql
```

**Ubuntu/Debian:**
```bash
sudo apt update
sudo apt install mysql-server
sudo systemctl start mysql
sudo systemctl enable mysql
```

**Windows:**
- Скачайте MySQL Installer с официального сайта
- Установите MySQL Server
- Запустите MySQL Workbench для настройки

#### 3. Настройка базы данных
```bash
# Подключение к MySQL
mysql -u root -p

# Создание базы данных
CREATE DATABASE qydyr CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

# Создание пользователя (опционально)
CREATE USER 'qydyr_user'@'localhost' IDENTIFIED BY 'qydyr_password';
GRANT ALL PRIVILEGES ON qydyr.* TO 'qydyr_user'@'localhost';
FLUSH PRIVILEGES;
EXIT;
```

#### 4. Настройка конфигурации
Отредактируйте `src/main/resources/application.properties`:

```properties
# База данных
spring.datasource.url=jdbc:mysql://localhost:3306/qydyr
spring.datasource.username=root
spring.datasource.password=your_mysql_password

# Email настройки (замените на свои)
spring.mail.username=your-email@gmail.com
spring.mail.password=your-app-password

# Google Maps API (замените на свой ключ)
gmaps.api.key=YOUR_GOOGLE_MAPS_API_KEY
```

#### 5. Сборка и запуск
```bash
# Установка прав на выполнение
chmod +x ./mvnw

# Сборка проекта
./mvnw clean package

# Запуск приложения
./mvnw spring-boot:run

# Или запуск JAR файла
java -jar target/qydyr-0.0.1-SNAPSHOT.jar
```

#### 6. Проверка работы
- Откройте браузер: http://localhost:8080
- API документация: http://localhost:8080/swagger-ui/index.html

---

## 🐳 Docker развертывание

### 1. Создание Dockerfile
```dockerfile
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/qydyr-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
```

### 2. Создание docker-compose.yml
```yaml
version: '3.8'

services:
  mysql:
    image: mysql:8.0
    container_name: qydyr-mysql
    environment:
      MYSQL_ROOT_PASSWORD: root
      MYSQL_DATABASE: qydyr
      MYSQL_USER: qydyr_user
      MYSQL_PASSWORD: qydyr_password
    ports:
      - "3306:3306"
    volumes:
      - mysql_data:/var/lib/mysql
      - ./init.sql:/docker-entrypoint-initdb.d/init.sql
    networks:
      - qydyr-network

  app:
    build: .
    container_name: qydyr-app
    ports:
      - "8080:8080"
    environment:
      - SPRING_DATASOURCE_URL=jdbc:mysql://mysql:3306/qydyr
      - SPRING_DATASOURCE_USERNAME=qydyr_user
      - SPRING_DATASOURCE_PASSWORD=qydyr_password
      - SPRING_MAIL_USERNAME=${EMAIL_USERNAME}
      - SPRING_MAIL_PASSWORD=${EMAIL_PASSWORD}
      - GMAPS_API_KEY=${GMAPS_API_KEY}
    depends_on:
      - mysql
    networks:
      - qydyr-network

volumes:
  mysql_data:

networks:
  qydyr-network:
    driver: bridge
```

### 3. Создание init.sql
```sql
-- Создание базы данных (если не создана автоматически)
CREATE DATABASE IF NOT EXISTS qydyr CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Использование базы данных
USE qydyr;

-- Создание пользователя
CREATE USER IF NOT EXISTS 'qydyr_user'@'%' IDENTIFIED BY 'qydyr_password';
GRANT ALL PRIVILEGES ON qydyr.* TO 'qydyr_user'@'%';
FLUSH PRIVILEGES;
```

### 4. Создание .env файла
```env
EMAIL_USERNAME=your-email@gmail.com
EMAIL_PASSWORD=your-app-password
GMAPS_API_KEY=your-google-maps-api-key
```

### 5. Запуск с Docker
```bash
# Сборка и запуск
docker-compose up --build

# Запуск в фоновом режиме
docker-compose up -d --build

# Просмотр логов
docker-compose logs -f app

# Остановка
docker-compose down
```

---

## ☁️ Облачное развертывание

### Heroku

#### 1. Подготовка для Heroku
Создайте `Procfile`:
```
web: java -jar target/qydyr-0.0.1-SNAPSHOT.jar
```

Добавьте в `pom.xml`:
```xml
<plugin>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-maven-plugin</artifactId>
    <configuration>
        <executable>true</executable>
    </configuration>
</plugin>
```

#### 2. Настройка переменных окружения
```bash
# Установка Heroku CLI
# https://devcenter.heroku.com/articles/heroku-cli

# Логин в Heroku
heroku login

# Создание приложения
heroku create qydyr-entity

# Добавление MySQL addon
heroku addons:create cleardb:ignite

# Настройка переменных окружения
heroku config:set SPRING_DATASOURCE_URL=$CLEARDB_DATABASE_URL
heroku config:set SPRING_MAIL_USERNAME=your-email@gmail.com
heroku config:set SPRING_MAIL_PASSWORD=your-app-password
heroku config:set GMAPS_API_KEY=your-google-maps-api-key
```

#### 3. Развертывание
```bash
# Добавление Heroku remote
git remote add heroku https://git.heroku.com/qydyr-entity.git

# Развертывание
git push heroku main

# Просмотр логов
heroku logs --tail
```

### AWS Elastic Beanstalk

#### 1. Подготовка приложения
Создайте `.ebextensions/01-environment.config`:
```yaml
option_settings:
  aws:elasticbeanstalk:application:environment:
    SPRING_PROFILES_ACTIVE: production
    SPRING_DATASOURCE_URL: jdbc:mysql://your-rds-endpoint:3306/qydyr
    SPRING_DATASOURCE_USERNAME: your-username
    SPRING_DATASOURCE_PASSWORD: your-password
```

#### 2. Развертывание
```bash
# Установка EB CLI
pip install awsebcli

# Инициализация
eb init

# Создание окружения
eb create production

# Развертывание
eb deploy
```

### Google Cloud Platform

#### 1. Подготовка
Создайте `app.yaml`:
```yaml
runtime: java17
env: standard

env_variables:
  SPRING_DATASOURCE_URL: jdbc:mysql://your-cloud-sql-ip:3306/qydyr
  SPRING_DATASOURCE_USERNAME: your-username
  SPRING_DATASOURCE_PASSWORD: your-password
  SPRING_MAIL_USERNAME: your-email@gmail.com
  SPRING_MAIL_PASSWORD: your-app-password
  GMAPS_API_KEY: your-google-maps-api-key
```

#### 2. Развертывание
```bash
# Установка Google Cloud SDK
# https://cloud.google.com/sdk/docs/install

# Аутентификация
gcloud auth login

# Установка проекта
gcloud config set project your-project-id

# Развертывание
gcloud app deploy
```

---

## 🔧 Production настройки

### 1. Безопасность

#### Настройка HTTPS
```properties
# application-prod.properties
server.port=8443
server.ssl.key-store=classpath:keystore.p12
server.ssl.key-store-password=your-password
server.ssl.key-store-type=PKCS12
server.ssl.key-alias=tomcat
```

#### Настройка CORS
```java
@Configuration
public class CorsConfig {
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
```

### 2. Мониторинг

#### Добавление Actuator endpoints
```properties
# application-prod.properties
management.endpoints.web.exposure.include=health,info,metrics,prometheus
management.endpoint.health.show-details=when-authorized
management.metrics.export.prometheus.enabled=true
```

#### Настройка логирования
```xml
<!-- logback-spring.xml -->
<configuration>
    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>
    
    <appender name="FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file>logs/application.log</file>
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <fileNamePattern>logs/application.%d{yyyy-MM-dd}.log</fileNamePattern>
            <maxHistory>30</maxHistory>
        </rollingPolicy>
        <encoder>
            <pattern>%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>
    
    <root level="INFO">
        <appender-ref ref="STDOUT" />
        <appender-ref ref="FILE" />
    </root>
</configuration>
```

### 3. Производительность

#### Настройка JVM
```bash
# Запуск с оптимизированными параметрами
java -Xms512m -Xmx2g -XX:+UseG1GC -XX:+UseStringDeduplication \
     -jar target/qydyr-0.0.1-SNAPSHOT.jar
```

#### Настройка базы данных
```properties
# Оптимизация HikariCP
spring.datasource.hikari.maximum-pool-size=20
spring.datasource.hikari.minimum-idle=5
spring.datasource.hikari.idle-timeout=300000
spring.datasource.hikari.max-lifetime=1200000
spring.datasource.hikari.connection-timeout=20000
```

---

## 🔍 Мониторинг и отладка

### 1. Health Checks
```bash
# Проверка статуса приложения
curl http://localhost:8080/actuator/health

# Детальная информация о здоровье
curl http://localhost:8080/actuator/health/readiness
curl http://localhost:8080/actuator/health/liveness
```

### 2. Метрики
```bash
# Просмотр метрик
curl http://localhost:8080/actuator/metrics

# Конкретная метрика
curl http://localhost:8080/actuator/metrics/jvm.memory.used
```

### 3. Логи
```bash
# Просмотр логов в реальном времени
tail -f logs/application.log

# Поиск ошибок
grep "ERROR" logs/application.log

# Анализ производительности
grep "slow query" logs/application.log
```

---

## 🚨 Устранение неполадок

### Частые проблемы

#### 1. Ошибка подключения к базе данных
```bash
# Проверка статуса MySQL
systemctl status mysql

# Проверка подключения
mysql -u root -p -e "SELECT 1"

# Проверка портов
netstat -tlnp | grep 3306
```

#### 2. Ошибка памяти
```bash
# Проверка использования памяти
free -h
ps aux --sort=-%mem | head

# Увеличение heap size
java -Xmx4g -jar target/qydyr-0.0.1-SNAPSHOT.jar
```

#### 3. Ошибка портов
```bash
# Проверка занятых портов
lsof -i :8080
lsof -i :3306

# Освобождение порта
kill -9 $(lsof -t -i:8080)
```

#### 4. Проблемы с SSL
```bash
# Проверка SSL сертификата
openssl s_client -connect your-domain.com:443

# Обновление сертификата
certbot renew
```

---

## 📋 Чек-лист развертывания

### Локальное развертывание
- [ ] Java 17+ установлена
- [ ] MySQL 8.0+ установлен и запущен
- [ ] Maven 3.6+ установлен
- [ ] База данных создана
- [ ] Конфигурация настроена
- [ ] Приложение запускается
- [ ] API доступен
- [ ] Swagger UI работает

### Docker развертывание
- [ ] Docker установлен
- [ ] Docker Compose установлен
- [ ] Dockerfile создан
- [ ] docker-compose.yml настроен
- [ ] .env файл создан
- [ ] Контейнеры запускаются
- [ ] Приложение доступно

### Production развертывание
- [ ] HTTPS настроен
- [ ] CORS настроен
- [ ] Мониторинг настроен
- [ ] Логирование настроено
- [ ] Backup базы данных настроен
- [ ] SSL сертификат установлен
- [ ] Firewall настроен
- [ ] Load balancer настроен (если нужно)

---

## 📞 Поддержка

При возникновении проблем:
1. Проверьте логи приложения
2. Проверьте статус базы данных
3. Проверьте сетевые подключения
4. Проверьте конфигурацию
5. Обратитесь к документации Spring Boot
6. Создайте issue в репозитории

---

**Удачного развертывания! 🚀**
