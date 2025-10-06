# 🌱 HydroGrow Server – Java Backend for IoT Plant Water Level Monitoring

[![Backend](https://img.shields.io/badge/backend-Java-orange)]()
[![Framework](https://img.shields.io/badge/framework-Servlets%20%26%20Hibernate-blue)]()
[![Database](https://img.shields.io/badge/database-MySQL-lightgrey)]()
[![IoT](https://img.shields.io/badge/device-ESP32-green)]()
[![Mobile](https://img.shields.io/badge/app-React%20Native-lightblue)]()

---

## 🧩 Overview

**HydroGrow Server** is the central backend for the **HydroGrow IoT plant monitoring system**.  
It receives soil moisture data from **ESP32 + soil moisture sensors**, stores it in a **MySQL database**, and provides APIs for the **React Native app** to display and analyze plant water levels.

---

## ⚙️ Features

- 🌡️ **Real-time soil moisture data**
- 📤 **Receive sensor readings from ESP32**
- 💾 **Store readings in MySQL via Hibernate**
- 📱 **Provide REST APIs to React Native app**
- 📊 **Retrieve latest or historical sensor data**
- 🧠 **Threshold alerts for low moisture levels**
- 🔁 **Scalable REST architecture**

---

## 🧱 Tech Stack

| Layer | Technology |
|--------|-------------|
| **Language** | Java |
| **Framework** | Servlets + Hibernate |
| **Database** | MySQL |
| **IoT Device** | ESP32 with soil moisture sensor |
| **Communication** | REST API (JSON) |
| **Server** | GlassFish / Tomcat |
| **Frontend** | React Native mobile app |

---

## 🔗 Related Repositories

 - ESP32 + Arduino sensor controller: https://github.com/savindi2003/hydrogrow-arduino.git
 - Frontend (React Native App) : https://github.com/savindi2003/hydrogrow-app.git

---

## 👩‍💻 Author
**Savindi Duleesha**  
📧 savindiduleesha@gmail.com  
🌐 [Portfolio](https://savindi2003.github.io/my-portfolio/)

---

## 📜 License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
