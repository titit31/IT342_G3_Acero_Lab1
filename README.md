# User Registration and Authentication System

## Overview
This is a Lab 1 project for IT342. It implements a User Registration and Authentication system using:
- **Backend**: Spring Boot 3 + MySQL + Spring Security (BCrypt, JWT)
- **Frontend**: React (Vite) + Axios + React Router

## Features
- User Registration
- User Login (JWT specific)
- Protected Dashboard Endpoint (`/api/user/me`)
- Profile Page

## Prerequisites
- Java 17+
- Node.js & npm
- MySQL Server (Database: `auth_db`, User: `root`, Pass: `root`)

## Setup & Run

### Database
1. Create a MySQL database named `auth_db`.
2. Update `backend/src/main/resources/application.properties` if your credentials differ.

### Backend
```bash
cd backend
mvn spring-boot:run
```
Server runs on `http://localhost:8080`.

### Frontend
```bash
cd web
npm install
npm run dev
```
Client runs on `http://localhost:5173`.

## API Endpoints
- `POST /api/auth/register` - Register a new user
- `POST /api/auth/login` - Authenticate user
- `GET /api/user/me` - Get current user details (Secured)
