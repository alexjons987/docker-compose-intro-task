# Task: Microservice system with Docker Compose
Run:
* `mvn clean package -DskipTests`
* `docker compose up --build` / `docker compose up -d`

## Purpose
* Run multiple services simultaneously with **Docker Compose**
* Understand how services communicate via **Docker networks**
* Use **volumes** for persistent data
* Build and run a **Spring Boot service** using a Dockerfile
* Start the entire system with **one command**

## System description
The system should consist of:
* **A Spring Boot API service**
* **A database**
* **An internal Docker network**
* **A persistent Docker volume for the database**

Everything should be started via `docker-compose.yml`

## Requirements
### API service (Spring Boot)
Create a Spring Boot application that:
* Has at least one endpoint, e.g.:
  * `GET /status` → returns `"OK"`
* Performs a simple database operation:
  * e.g. `SELECT 1` or save/read a row
* Have a **Dockerfile**

## Dockerfile (API)
The API service should:
* Be built with a Dockerfile
* Run on port `8080`

## Docker Compose
Create a `docker-compose.yml` that:

### Should contain:
* At least two services
  * `api` (Spring Boot)
  * `db` (database)
* **A separate network**
* **A named volume** for the database

### Rules:
* The API must **not** use localhost to the database

* The API must connect via the service name, e.g.: `db:3306`

* The database **must not be exposed** with `ports`

## Volumes
* The database must use a **named volume**

Data must remain after:
* `docker compose down`
* `docker compose up`

## Start the system
The entire system must be able to be started with: `docker compose up --build`

## Verification
You must be able to show that:
* The API service starts correctly
* The API service can communicate with the database
* Data remains after reboot

## Testing Instructions
1. Start the system
2. Call the API via: browser, curl or Postman
3. Stop the containers
4. Restart
5. Verify that the data still exists

## Extras (optional)
* Add `healthcheck`
* Use `.env` for passwords
* Add another API service
* Add pgAdmin