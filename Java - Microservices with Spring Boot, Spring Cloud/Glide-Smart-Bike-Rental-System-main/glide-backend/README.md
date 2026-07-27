# 🚀 Glide Backend

Spring Boot REST API for the Glide Smart Bike Rental System.

---

## Technologies

- Java 21
- Spring Boot
- Spring Security
- JWT
- Spring Data JPA
- Hibernate
- PostgreSQL
- Maven
- Lombok

---

## Features

- JWT Authentication
- Rider Registration
- Operator Login
- Station CRUD
- Bike CRUD
- Reservation Management
- Ride Management
- Wallet Management
- Wallet Transactions
- Reports
- Dashboard APIs

---

## Project Structure

```
src
├── config
├── controller
├── dto
├── entity
├── enums
├── exception
├── repository
├── service
└── util
```

---

## REST APIs

### Authentication

- POST /api/auth/register
- POST /api/auth/login

### Rider

- Wallet APIs
- Reservation APIs
- Ride APIs
- Dashboard API

### Operator

- Station APIs
- Bike APIs
- Report APIs
- Dashboard API

---

## Database

- PostgreSQL

Tables:

- users
- wallet
- wallet_transaction
- station
- bike
- reservation
- ride

---

## Security

- Spring Security
- JWT Authentication
- Role Based Access Control

---

## Build

```bash
mvn clean install
```

Run

```bash
mvn spring-boot:run
```
