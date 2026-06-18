# Portfolio Backend

A full-stack portfolio application built with **Spring Boot**, **MySQL**, and **React**, featuring secure JWT authentication, contact management, email notifications, and an AI-powered Recruiter Assistant using Google Gemini.

## 🚀 Features

### 👤 User Authentication

* JWT-based authentication
* Access token and refresh token support
* BCrypt password encryption
* Role-based authorization
* Protected API endpoints

### 📩 Contact Form

* Visitors can submit messages
* Messages are stored in MySQL
* Email notifications are sent automatically
* Contact management for authenticated users

### 🤖 AI Recruiter Assistant

* Powered by Google Gemini API
* Answers recruiter questions about the portfolio owner
* Provides information about:

  * Technical skills
  * Projects
  * Experience
  * Education
  * Achievements
  * Career interests
* Natural language responses
* Secure API key handling using environment variables

### 🔐 Security

* Spring Security integration
* JWT filter for request validation
* Stateless authentication
* Password hashing with BCrypt
* Environment variable-based secret management

### 🌐 CORS Support

* Configurable CORS settings
* Frontend and backend separation

---

# Tech Stack

## Backend

* Java 17+
* Spring Boot
* Spring Security
* Spring Data JPA
* Hibernate
* Maven

## Database

* MySQL

## Authentication

* JWT
* Refresh Tokens
* BCrypt Password Encoder

## AI Integration

* Google Gemini API

## Email Service

* Spring Mail

## Deployment

* Backend: Northflank
* Database: Railway MySQL
* Frontend: React + Vite

---

# Project Structure

```
src
├── config
│   ├── SecurityConfig
│   └── CorsConfiguration
├── controller
│   ├── AuthController
│   ├── ContactController
│   ├── RecruiterAssistantController
│   └── UserController
├── dto
├── entity
├── exception
├── repository
├── security
│   ├── JwtFilter
│   └── JwtUtil
├── service
│   ├── UserService
│   ├── RefreshTokenService
│   ├── EmailService
│   └── RecruiterAssistantService
└── ContactBackendApplication
```

---

# API Endpoints

## Authentication

### Login

```
POST /api/auth/login
```

### Refresh Token

```
POST /api/user/refresh
```

### Register

```
POST /api/user/register
```

---

## Contact

### Submit Contact Message

```
POST /api/contact/submit
```

Public endpoint.

---

## AI Recruiter Assistant

### Ask Questions

```
POST /api/recruiter-assistant
```

Example Request

```json
{
  "question": "Why should I hire Joel?"
}
```

Example Response

```json
{
  "answer": "Joel has experience in Java, Spring Boot, React, MySQL, JWT authentication, and cloud deployment. He has built projects involving AI integration and full-stack development."
}
```

---

# Security

Protected endpoints require:

```
Authorization: Bearer <access_token>
```

Passwords are encrypted using BCrypt.

Authentication is stateless using JWT.

---

# Environment Variables

### Database

```
DB_URL
DB_USERNAME
DB_PASSWORD
```

### JWT

```
JWT_SECRET
ACCESS_TOKEN_EXPIRATION
REFRESH_TOKEN_EXPIRATION
```

### Email

```
MAIL_USERNAME
MAIL_PASSWORD
```

### Google Gemini API

```
GEMINI_API_KEY
```

---

# Local Setup

### Clone Repository

```bash
git clone https://github.com/SJoelMartin/Portfolio-Backend.git
```

### Navigate to Project

```bash
cd Portfolio-Backend
```

### Configure Environment Variables

Create local variables or IDE environment variables:

```
DB_URL=
DB_USERNAME=
DB_PASSWORD=
JWT_SECRET=
MAIL_USERNAME=
MAIL_PASSWORD=
GEMINI_API_KEY=
```

### Build

```bash
mvn clean install
```

### Run

```bash
mvn spring-boot:run
```

Backend runs on:

```
http://localhost:8080
```

---

# Deployment

### Backend

Northflank

### Database

Railway MySQL

### Frontend

Vercel

### Environment Variables

All secrets are stored securely using platform environment variables and are never committed to GitHub.

---

# AI Recruiter Assistant Examples

### Questions Supported

* Tell me about Joel.
* What technologies does Joel know?
* Why should I hire Joel?
* Describe Joel's projects.
* What is Joel's experience with Spring Boot?
* What cloud technologies has Joel worked with?
* What are Joel's strengths?

---

# Future Enhancements

* Resume summarization
* Semantic search with embeddings
* Chat history persistence
* Conversation memory
* Voice-enabled assistant
* Admin dashboard analytics
* Vector database integration
* Multi-model AI support

---

# Author

Joel Martin

M.Tech Integrated Computer Science and Engineering

Java | Spring Boot | React | MySQL | JWT | REST APIs | AI Integration | Cloud Deployment
