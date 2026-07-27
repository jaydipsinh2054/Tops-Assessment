# 🚲 Glide – Smart Bike Rental System

A modern **Full Stack Bike Rental Management System** built using **Spring Boot**, **React.js**, and **PostgreSQL**. The application provides a seamless bike rental experience for riders while allowing operators to efficiently manage stations, bikes, and system reports.

---

## 📖 Project Overview

Glide is a web-based bike rental platform developed to simplify urban bike sharing and rental operations. The system provides two separate user roles:

- **Rider** – Register, reserve bikes, start/end rides, manage wallet, and view ride history.
- **Operator** – Manage stations and bikes, monitor dashboards, and generate reports.

The project follows a **RESTful architecture** with a React frontend communicating securely with a Spring Boot backend using **JWT Authentication**.

---

## ✨ Key Features

### 👤 Rider Module

- User Registration
- Secure Login (JWT Authentication)
- Dashboard
- Wallet Management
- Add Money
- Wallet Transaction History
- View Bike Stations
- View Available Bikes
- Reserve Bike
- Start Ride
- End Ride
- Ride History

---

### 👨‍💼 Operator Module

- Secure Login
- Dashboard
- Station Management (CRUD)
- Bike Management (CRUD)
- Station-wise Reports
- Revenue Monitoring
- Completed Ride Statistics

---

## 💼 Business Rules

The application follows several business rules to simulate a real-world bike rental system:

- Riders must register before using the platform.
- Operators cannot register through the UI.
- JWT Authentication secures all protected APIs.
- A rider must maintain sufficient wallet balance before reserving a bike.
- ₹5 Unlock Fee is deducted when a ride starts.
- Ride fare is calculated at **₹2 per minute**.
- Bikes automatically change their status during reservation and rides.
- Completed rides are stored in ride history.
- Bikes are relocated to the selected destination station after ride completion.
- Reservation expiration is handled automatically.
- Reports and dashboards are generated dynamically from database records.

---

# 🛠 Technology Stack

## Frontend

- React.js
- Vite
- Bootstrap 5
- Axios
- React Router
- React Toastify

---

## Backend

- Java
- Spring Boot
- Spring Security
- JWT Authentication
- Spring Data JPA
- Hibernate
- Maven

---

## Database

- PostgreSQL

---

# 🏗 System Architecture

```
                React Frontend
                       │
                 REST API (JSON)
                       │
               Spring Boot Backend
                       │
      Controller → Service → Repository
                       │
                 PostgreSQL Database
```

---

# 📂 Project Structure

```
Glide-Smart-Bike-Rental-System
│
├── glide-backend
│   ├── src
│   ├── pom.xml
│   ├── mvnw
│   └── ...
│
├── glide-frontend
│   ├── src
│   ├── package.json
│   ├── vite.config.js
│   └── ...
│
├── images
│
└── README.md
```

---

# 🔐 Authentication & Security

- JWT Based Authentication
- Spring Security
- Password Encryption using BCrypt
- Role Based Authorization
- Protected APIs
- Secure Route Navigation

---

# 📦 Backend Modules

- Authentication
- User Management
- Wallet Management
- Wallet Transactions
- Station Management
- Bike Management
- Reservation Management
- Ride Management
- Reports
- Dashboard

---

# 🎨 Frontend Modules

## Rider

- Login
- Registration
- Dashboard
- Wallet
- Wallet Transactions
- Stations
- Available Bikes
- Reservations
- Current Ride
- Ride History

---

## Operator

- Dashboard
- Station Management
- Bike Management
- Reports

---

# 🗄 Database Tables

The project uses PostgreSQL with the following tables:

- Users
- Wallet
- Wallet Transactions
- Stations
- Bikes
- Reservations
- Rides

---


# 📡 REST APIs

## Authentication

- Register
- Login

---

## Rider APIs

- Dashboard
- Wallet
- Wallet Transactions
- Stations
- Available Bikes
- Reservations
- Start Ride
- End Ride
- Ride History

---

## Operator APIs

- Dashboard
- Station CRUD
- Bike CRUD
- Reports

---

# 📈 Future Enhancements

- QR Code Bike Unlocking
- Online Payment Gateway Integration
- GPS Tracking
- Push Notifications
- Mobile Application (Android / iOS)
- AI-based Bike Demand Prediction

---

# 👨‍💻 Developer

**Jaydipsinh Vaghela**

Information Technology Student

Java Full Stack Developer

---

# ⭐ Acknowledgement

This project was developed as a **Java Full Stack Major Project** to demonstrate practical implementation of:

- Spring Boot
- React.js
- JWT Authentication
- RESTful APIs
- PostgreSQL
- Full Stack Application Development

---

## 📄 License

This project is developed for educational purposes.
