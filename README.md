# Bus Reservation System

A full-featured bus reservation system built with **Spring Boot**, **MySQL**, and **Thymeleaf**. Users can register, book tickets, and manage schedules, while admins can manage buses, schedules, and bookings.

---

## Features

- User registration and login
- Browse buses and schedules
- Book tickets and view booking history
- Admin panel to manage buses, schedules, and bookings
- File uploads for bus images and user profile photos
- Responsive UI with Thymeleaf templates

---

## Technology Stack

- **Backend:** Java, Spring Boot, Spring MVC, Spring Data JPA, Spring Security
- **Frontend:** Thymeleaf, HTML, CSS
- **Database:** MySQL (via Docker)
- **Build Tool:** Maven
- **Development Environment:** GitHub Codespaces or local IDE

---

## Getting Started

### Prerequisites

- Java JDK 17+
- Maven 3+
- MySQL or Docker
- Git

---

## Running Locally

1. **Clone the repository**

```bash
git clone https://github.com/<your-username>/bus-reservation-system.git
cd bus-reservation-system
````

2. **Start MySQL**

If MySQL is installed locally:

```bash
sudo service mysql start
```

Or use Docker:

```bash
docker run --name bus-mysql -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=busdb -p 3307:3306 -d mysql:8
```

3. **Configure Spring Boot**

Edit `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3307/busdb
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

4. **Build and Run**

```bash
mvn clean install
mvn spring-boot:run
```

5. **Open in browser**

```
http://localhost:8080/
```

---

## Running in GitHub Codespaces

1. **Create a Codespace** for this repository.

2. **Start MySQL in Docker inside Codespace**

```bash
docker run --name bus-mysql -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=busdb -p 3307:3306 -d mysql:8
```

3. **Update `application.properties`** (same as local setup) to point to port `3307`.

4. **Build and run Spring Boot**

```bash
mvn clean install
mvn spring-boot:run
```

5. **Forward port 8080** in Codespaces and open the URL:

```
https://<your-codespace-name>-8080.app.github.dev/
```

* Example register page:

```
https://<your-codespace-name>-8080.app.github.dev/register
```

---

## Notes

* Use `spring.jpa.hibernate.ddl-auto=update` to auto-create tables.
* Avoid committing `/target` and `.DS_Store` files. Include a `.gitignore`:

```
/target
.DS_Store
.vscode/
```

---

## Contact

For questions, issues, or contributions, please contact **py3030223@gmail.com** or open an issue in the repository.


