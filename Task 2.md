# Task: Docker Compose – env (required) & secrets (optional)
Run:
* `mvn clean package -DskipTests`
* `docker compose up --build` / `docker compose up -d`

## Purpose
This task trains you to:
* use **.env files**
* understand **why environment variables are needed**
* (optional) improve security with **Docker Secrets**

The task reflects a **real-world workflow** in teams and CI/CD.

## Scenario
You are going to run a Spring** Boot application** that uses a **MySQL database** via Docker Compose.

The following rules apply:
* No hardcoded values ​​in `docker-compose.yml`
* The application should **not restart** if `.env` is missing
* The database configuration should be shared between services

## Expected structure
```
project/
├── docker-compose.yml
├── .env # REQUIRED (should not be checked in)
├── .env.example # Should be checked in
├── secrets/ # (OPTIONAL)
│   └── mysql_password.txt
├── backend/
└── Dockerfile`
```

## Part 1 – Required .env (REQUIRED)
### 1. Create `.env.example`
The file should contain:
```env
MYSQL_DATABASE=
MYSQL_USER=
MYSQL_PASSWORD=
MYSQL_ROOT_PASSWORD=
MYSQL_PORT=3306
```
This file should be usable as a template by another developer.
### 2. Create `.env`
Fill in the correct values:
```env
MYSQL_DATABASE=db_name
MYSQL_USER=user_name
MYSQL_PASSWORD=user_pass
MYSQL_ROOT_PASSWORD=root_password
MYSQL_PORT=3306
```
`.env` should be in `.gitignore`.
### 3. docker-compose.yml (mandatory env usage)
Requirements:
* All sensitive and configurable values ​​should come from `.env`
* No strings like `school`, `root`, `3306` should be hardcoded

Example (incomplete):
```
services:
  mysql:
    image: mysql:8.0
  environment:
    MYSQL_DATABASE: ${MYSQL_DATABASE}
    MYSQL_USER: ${MYSQL_USER}
    MYSQL_PASSWORD: ${MYSQL_PASSWORD}
    MYSQL_ROOT_PASSWORD: ${MYSQL_ROOT_PASSWORD}
```
If `.env` is missing, `docker compose up` will fail.
### 4. Spring Boot service
Requirements:
* Should use the **same variables** from `.env`
* Should connect to the database via the service name `mysql`
```
SPRING_DATASOURCE_URL: jdbc:mysql://mysql:${MYSQL_PORT}/${MYSQL_DATABASE}
SPRING_DATASOURCE_USERNAME: ${MYSQL_USER}
SPRING_DATASOURCE_PASSWORD: ${MYSQL_PASSWORD}
```
### 5. Verification (must be visible)
Run:
`docker compose config`

All variables should be expanded ✔ No empty values
## Part 2 – Docker Secrets (OPTIONAL)
### Goal
Replace passwords in `.env` with **Docker Secrets**.
### 6. Create a secret file
`secrets/mysql_password.txt`
Contents:
super_secret_password
### 7. Define secret in compose
```
secrets:
  mysql_password:
    file: ./secrets/mysql_password.txt
```
### 8. Use secret in MySQL
```
services:
  mysql:
    secrets:
      - mysql_password
    environment:
      MYSQL_PASSWORD_FILE: /run/secrets/mysql_password
      MYSQL_ROOT_PASSWORD_FILE: /run/secrets/mysql_password
```
`MYSQL_PASSWORD` should no longer be in `.env`.
### 9. Use secret in Spring Boot
```
services:
  backend:
    secrets:
      - mysql_password
    environment:
      SPRING_DATASOURCE_PASSWORD_FILE: /run/secrets/mysql_password
```