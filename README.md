# 🎫 Qydyr Entity - Event Ticket Booking System

A comprehensive event ticket booking system built with Spring Boot, featuring user authentication, event management, and ticket purchasing capabilities.

## 🚀 Features

### Core Functionality
- **User Management**: Registration, authentication, and profile management
- **Event Management**: Create, read, update, and delete events (Afisha)
- **Ticket Purchasing**: Buy tickets with balance management
- **Admin Panel**: Complete administrative interface for managing events, places, and users
- **Favorites System**: Users can save favorite events and places
- **Order History**: Track purchase history and order details

### Technical Features
- **Spring Boot 2.7.5** with Java 17+
- **MySQL Database** with JPA/Hibernate
- **Thymeleaf Templates** for responsive web interface
- **Spring Security** with JWT and session-based authentication
- **REST API** endpoints for mobile/third-party integration
- **Image Upload** and management system
- **Email Notifications** for order confirmations
- **Responsive Design** with Bootstrap 5

## 🛠️ Technology Stack

- **Backend**: Spring Boot, Spring Security, Spring Data JPA
- **Database**: MySQL 8.0
- **Frontend**: Thymeleaf, Bootstrap 5, JavaScript
- **Authentication**: JWT + Session-based
- **Build Tool**: Maven
- **Java Version**: 17+

## 📋 Prerequisites

- Java 17 or higher
- MySQL 8.0 or higher
- Maven 3.6+
- Git

## 🚀 Quick Start

### 1. Clone the Repository
```bash
git clone https://github.com/5knew/qydyr-entity.git
cd qydyr-entity
```

### 2. Database Setup
```bash
# Create MySQL database
mysql -u root -p
CREATE DATABASE qydyr;
```

### 3. Configure Application
Update `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/qydyr
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### 4. Run the Application
```bash
# Using Maven wrapper
./mvnw spring-boot:run

# Or using Maven
mvn spring-boot:run
```

### 5. Access the Application
- **Main Application**: http://localhost:8080
- **Admin Panel**: http://localhost:8080/admin
- **API Documentation**: http://localhost:8080/swagger-ui.html

## 📊 Database Schema

The application includes the following main entities:

- **User**: User accounts with authentication
- **Afisha**: Events/concerts with details
- **Place**: Venues and locations
- **Order**: Ticket purchase records
- **Image**: Event and place images
- **Favorites**: User's favorite events and places

## 🔐 Default Admin Access

- **Email**: admin@qydyr.com
- **Password**: admin123

## 📱 API Endpoints

### Authentication
- `POST /api/v1/auth/register` - User registration
- `POST /api/v1/auth/login` - User login
- `POST /api/v1/auth/token` - Get JWT token

### Events (Afisha)
- `GET /api/v1/afisha` - Get all events
- `GET /api/v1/afisha/{id}` - Get event by ID
- `POST /api/v1/afisha` - Create event (Admin)
- `PUT /api/v1/afisha/{id}` - Update event (Admin)
- `DELETE /api/v1/afisha/{id}` - Delete event (Admin)

### Places
- `GET /api/v1/places` - Get all places
- `POST /api/v1/places` - Create place (Admin)
- `PUT /api/v1/places/{id}` - Update place (Admin)
- `DELETE /api/v1/places/{id}` - Delete place (Admin)

### Orders
- `POST /api/v1/orders` - Create order
- `GET /api/v1/orders` - Get user orders

## 🗂️ Project Structure

```
src/
├── main/
│   ├── java/com/example/qydyr/
│   │   ├── auth/           # Authentication services
│   │   ├── config/         # Configuration classes
│   │   ├── controller/     # REST and Web controllers
│   │   ├── dto/           # Data Transfer Objects
│   │   ├── exception/     # Exception handling
│   │   ├── model/         # Entity models
│   │   ├── repository/    # Data repositories
│   │   ├── service/       # Business logic
│   │   └── utils/         # Utility classes
│   └── resources/
│       ├── static/        # CSS, JS, images
│       ├── templates/     # Thymeleaf templates
│       └── application.properties
└── test/                  # Test files
```

## 🔧 Configuration

### Database Configuration
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/qydyr
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update
```

### Security Configuration
- CSRF protection disabled for API endpoints
- JWT authentication for API
- Session-based authentication for web interface
- Password encoding with BCrypt

## 📦 Database Backup

The project includes database backup files:
- `qydyr_database_dump.sql` - Complete database dump
- `qydyr_schema_only.sql` - Schema only
- `qydyr_data_only.sql` - Data only

To restore the database:
```bash
mysql -u root -p qydyr < qydyr_database_dump.sql
```

## 🧪 Testing

### Test Data
The application includes test data for development:
- Sample events and places
- Test user accounts
- Sample images

### Running Tests
```bash
./mvnw test
```

## 🚀 Deployment

### Docker Deployment
```bash
# Build Docker image
docker build -t qydyr-entity .

# Run with Docker Compose
docker-compose up -d
```

### Production Configuration
1. Update `application.properties` for production database
2. Configure email settings for notifications
3. Set up proper SSL certificates
4. Configure reverse proxy (Nginx/Apache)

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👥 Authors

- **Syngys Muratkhan** - *Initial work* - [5knew](https://github.com/5knew)

## 🙏 Acknowledgments

- Spring Boot community
- Bootstrap team for the UI framework
- MySQL team for the database
- All contributors and testers

## 📞 Support

If you have any questions or need help, please:
1. Check the [Issues](https://github.com/5knew/qydyr-entity/issues) page
2. Create a new issue with detailed description
3. Contact the maintainers

---

**Happy Event Booking! 🎉**