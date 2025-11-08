# 🚌 Bus Reservation System

A simple Java (Maven-based) project for managing bus reservations.
This project helps users to book, cancel, and view bus tickets, while the admin can manage bus details and passenger information.

---

## 📌 Features

* 🔑 **User Features:**

  * Register / Login
  * Search buses by route
  * Book tickets
  * Cancel tickets
  * View booking history

* 🛠 **Admin Features:**

  * Add new buses
  * Update or remove buses
  * View all bookings
  * Manage passenger records

---

## ⚙️ Tech Stack

* **Language:** Java
* **Framework:** Spring Boot
* **Build Tool:** Maven
* **Database:** MySQL
* **Frontend:** Thymeleaf Templates (HTML + CSS)
* **IDE Support:** IntelliJ IDEA / Eclipse / VS Code

---

## 🚀 Installation & Setup

Clone this repository and navigate into the project directory:

```bash
git clone https://github.com/shreyr1/bus-reservation-system
cd bus-reservation-system
```

---

## 🧩 Database Setup

⚠️ Note: The project uses a hardcoded MySQL user (`root` / `root`) by default.
Make sure your local MySQL server is running and allows root login.
The app will automatically create the required tables at startup.

Expected tables for `bus_db`:

```
+------------------+
| Tables_in_bus_db |
+------------------+
| bookings         |
| buses            |
| schedules        |
| users            |
+------------------+
```

---

## 🧰 Build & Run

### ▶️ Using Maven

```bash
mvn clean install
mvn spring-boot:run
```

### ▶️ OR Run the JAR directly

After building, run:

```bash
java -jar target/bus-reservation-system-0.0.1-SNAPSHOT.jar
```

Then open your browser at 👉 [http://localhost:8080](http://localhost:8080)

---

## 🧑‍💻 Default Login (Demo Seed)

When the app starts for the first time (and the `users` table is empty),
the `DataLoader.java` class automatically creates demo users and sample data.

### 👥 Default Accounts

**Admin**

* Email: `admin@test.com`
* Password: `password`
* Role: `ROLE_ADMIN`

**User**

* Email: `user@test.com`
* Password: `password`
* Role: `ROLE_USER`

### 🚌 Demo Data

The following sample records are created:

**Buses**

| Name             | Number      | Seats |
| ---------------- | ----------- | ----- |
| Volvo AC Sleeper | UP85-AB1234 | 30    |
| Express Non-AC   | DL01-CD5678 | 45    |

**Schedule**

| Source  | Destination | Departure (approx) | Arrival (approx) | Price |
| ------- | ----------- | ------------------ | ---------------- | ----- |
| Mathura | Delhi       | +2 hours           | +5 hours         | ₹550  |

---

## 🧱 Project Structure

```
bus-reservation-system/
├── pom.xml                               # Maven configuration file
├── README.md                             # Project documentation
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── busreservation
    │   │           ├── BusReservationSystemApplication.java     # Main Spring Boot entry point
    │   │           ├── config                                   # Configuration classes
    │   │           │   ├── DataLoader.java                      # Seeds demo users, buses, and schedules
    │   │           │   └── SecurityConfig.java                  # Spring Security setup
    │   │           ├── controller                               # Handles HTTP routes and logic
    │   │           │   ├── AdminController.java                 # Admin dashboard web controller
    │   │           │   ├── AdminRestController.java             # REST endpoints for admin
    │   │           │   ├── BookingController.java               # User booking actions
    │   │           │   ├── PublicController.java                # Public pages like search/home
    │   │           │   └── UserController.java                  # User profile and account management
    │   │           ├── dto                                      # Data transfer objects
    │   │           │   ├── PasswordChangeDto.java               # Password update data
    │   │           │   └── UserRegistrationDto.java             # Registration form data
    │   │           ├── exception                                # Custom exceptions and handlers
    │   │           │   ├── GlobalExceptionHandler.java          # Handles all exceptions globally
    │   │           │   └── ResourceNotFoundException.java       # Thrown when resource is missing
    │   │           ├── model                                    # Database entity models
    │   │           │   ├── Booking.java                         # Booking entity
    │   │           │   ├── Bus.java                             # Bus entity
    │   │           │   ├── Schedule.java                        # Schedule entity
    │   │           │   └── User.java                            # User entity
    │   │           ├── repository                               # JPA repositories
    │   │           │   ├── BookingRepository.java               # Handles Booking data
    │   │           │   ├── BusRepository.java                   # Handles Bus data
    │   │           │   ├── ScheduleRepository.java              # Handles Schedule data
    │   │           │   └── UserRepository.java                  # Handles User data
    │   │           └── service                                  # Business logic layer
    │   │               ├── BookingServiceImpl.java              # Implements booking logic
    │   │               ├── BookingService.java                  # Booking service interface
    │   │               ├── BusServiceImpl.java                  # Implements bus management logic
    │   │               ├── BusService.java                      # Bus service interface
    │   │               ├── CustomUserDetailsService.java        # Loads users for authentication
    │   │               ├── FileStorageService.java              # Handles file uploads
    │   │               ├── ScheduleServiceImpl.java             # Implements schedule logic
    │   │               ├── ScheduleService.java                 # Schedule service interface
    │   │               ├── UserServiceImpl.java                 # Implements user logic
    │   │               └── UserService.java                     # User service interface
    │   └── resources
    │       ├── application.properties                           # App and DB configuration
    │       ├── static                                           # Static frontend assets
    │       │   ├── css/
    │       │   │   └── style.css                                # Stylesheet
    │       │   └── images/
    │       │       └── bus.png                                  # Bus image asset
    │       └── templates                                        # Thymeleaf HTML templates
    │           ├── admin                                        # Admin panel pages
    │           │   ├── admin-dashboard.html                     # Admin home
    │           │   ├── edit-bus.html                            # Edit bus details
    │           │   ├── edit-schedule.html                       # Edit schedule details
    │           │   ├── manage-buses.html                        # Manage bus list
    │           │   ├── manage-schedules.html                    # Manage schedules
    │           │   ├── view-bookings.html                       # View all bookings
    │           │   ├── view-schedule-bookings.html              # View bookings by schedule
    │           │   └── view-users.html                          # View user list
    │           ├── booking-page.html                            # Booking form
    │           ├── error-page.html                              # Error display
    │           ├── fragments                                    # Common HTML fragments
    │           │   ├── footer.html                              # Page footer
    │           │   └── header.html                              # Page header
    │           ├── index.html                                   # Home page
    │           ├── login.html                                   # Login page
    │           ├── my-bookings.html                             # User booking history
    │           ├── payment-simulation.html                      # Payment simulation
    │           ├── profile.html                                 # User profile
    │           ├── register.html                                # Registration page
    │           ├── search-results.html                          # Bus search results
    │           └── ticket.html                                  # Ticket details
    └── test
        └── java
            └── com
                └── busreservation
                    └── service
                        └── BusServiceImplTest.java              # Unit test for Bus service
```
