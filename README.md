# ⚡ Energy Community System

A modular system simulating a local energy-sharing community, where producers and users exchange energy. It uses RabbitMQ for message coordination, Spring Boot for API access, and JavaFX for monitoring energy flow and historical data.

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

## 🗺️ Data Flow Summary

Producers and users send data → Usage Service updates DB → Percentage Service recalculates stats → REST API serves the data → GUI displays it.
