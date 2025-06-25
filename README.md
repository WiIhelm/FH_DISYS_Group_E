# ⚡ Energy Community System

A modular system simulating a local energy-sharing community, where producers and users exchange energy. It uses RabbitMQ for message coordination, Spring Boot for API access, and JavaFX for monitoring energy flow and historical data.

---

## ▶️ How to Start the Project

### 1. ✅ Start RabbitMQ & PostgreSQL
Launch **RabbitMQ** and **PostgreSQL** using Docker.
* Ensure the following ports are free before starting:
  * `5672` → RabbitMQ
  * `5432` → PostgreSQL

### 2. 🗄️ Database Setup with Flyway
If this is the first time running the project:
* Flyway will **automatically apply the initial schema** and create the required tables on application startup.
* No manual DB setup required.

### 3. 🚀 Start the REST API Application
The **RestAPIApplication** handles:
* Real-time and historical dataset serving
* Filtered data queries
  
📁 (If necessary) Navigate to:
`disys_restapi/src/main/java/at/fhtechnikum/disys_restapi`
Run the `RestAPIApplication.java` file.

### 4. 🔁 Start RabbitMQ-Based Services
Start the following applications in **this order**:
1. `CPSApplication`
2. `UsageServiceApplication`
3. `CommunityEnergyProducerMain`
4. `CommunityEnergyUserMain`

These services rely on RabbitMQ to communicate and exchange data.

### 5. 🖥️ Start the JavaFX GUI Application

📁 Navigate to:
`disys_javafx_project/src/main/java/at/fhtechnikum/disys_javafx`
Run `GUIApplication.java`.

This launches the main interface for interacting with the system.

---

### 💡 Tips & Troubleshooting

* **IntelliJ Performance Tip:**
  If opening the entire repository causes performance issues or slow indexing, try opening each **subproject (folder)** as a **separate IntelliJ project**.

* **Flyway Errors?**
  Ensure the database is clean or has been properly reset before restarting services.

---

## 🗺️ Data Flow Summary

Producers and users send data → Usage Service updates DB → Percentage Service recalculates stats → REST API serves the data → GUI displays it.

---

## 🧩 Components

### 1. Community Energy Producer  
Generates energy data every few seconds and sends it to RabbitMQ. Output is based on weather data to simulate realistic solar generation.

### 2. Community Energy Consumer  
Simulates energy consumption by community users. Sends usage data periodically, increasing during peak hours (e.g., morning and evening).

### 3. Usage Service  
Processes incoming messages to update hourly energy usage statistics in the database. It determines whether community or grid energy was used.

### 4. Current Percentage Service  
Calculates the percentage of energy supplied by the community vs. the grid. Updates a table with the latest stats for the current hour.

### 5. Spring Boot REST API  
Provides read-only endpoints to fetch current and historical energy statistics. Supports two GET endpoints for real-time and filtered data.

### 6. JavaFX GUI  
Displays current and historical energy data using the REST API. Allows users to visually monitor the energy distribution over time.

---
