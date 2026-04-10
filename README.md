# 💰 Smart Expense Tracker

![Java](https://img.shields.io/badge/Java-17-orange)
![Servlets](https://img.shields.io/badge/Jakarta%20Servlets-5.0-blue)
![JSP](https://img.shields.io/badge/JSP-View-green)
![MySQL](https://img.shields.io/badge/MySQL-Database-blue)

---

## 📌 Table of Contents

* [Description](#description)
* [Features](#features)
* [Tech Stack](#tech-stack)
* [Project Structure](#project-structure)
* [Installation](#installation)
* [Usage](#usage)
* [Deployment](#deployment)
* [Environment Variables](#environment-variables)
* [API Endpoints](#api-endpoints)
* [Demo](#demo)
* [Contribution](#contribution)
* [Troubleshooting](#troubleshooting)
* [Author](#author)

---

## 📖 Description

**Smart Expense Tracker** is a full-stack Java web application that allows users to track, manage, and analyze their daily expenses.

It follows **MVC architecture** using Servlets, JSP, JDBC, and MySQL, and provides data visualization using Chart.js along with export functionality.

---

## 🚀 Features

* 🔐 User Authentication (Signup, Login, Logout)
* 💸 Expense Management (Add, Edit, Delete)
* 🏷 Categories: Food, Travel, Bills, Shopping, Others
* 📊 Dashboard:

  * Pie Chart (Category-wise)
  * Line Chart (Monthly trends)
* 📁 Export:

  * CSV download
  * PDF report
* 🛡 Session-based authentication
* 📱 Responsive UI (Bootstrap)
* ⚠ Validation & Error Handling

---

## 🛠 Tech Stack

| Layer        | Technology                |
| ------------ | ------------------------- |
| Backend      | Java Servlets             |
| Frontend     | JSP, HTML, CSS, Bootstrap |
| Database     | MySQL                     |
| Connectivity | JDBC                      |
| Charts       | Chart.js                  |
| PDF Export   | iTextPDF                  |
| Server       | Apache Tomcat             |
| Deployment   | Docker + Render           |

---

## 📂 Project Structure

```
smart-expense-tracker/
│
├── src/
│   ├── controller/
│   ├── dao/
│   ├── model/
│   └── util/
│
├── WebContent/
│   ├── views/
│   ├── css/
│   ├── js/
│   └── WEB-INF/lib/
│
├── sql/
│   └── schema.sql
│
├── Dockerfile
├── README.md
└── smart-expense-tracker.war
```

---

## ⚙️ Installation

### 🔧 Prerequisites

* Java 17+
* Apache Tomcat 10+
* MySQL

---

### 📥 Clone Repository

```
git clone https://github.com/your-username/smart-expense-tracker.git
cd smart-expense-tracker
```

---

### 🗄 Setup Database

```
CREATE DATABASE expense_tracker;

USE expense_tracker;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100),
    password VARCHAR(100)
);

CREATE TABLE expenses (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    amount DOUBLE,
    category VARCHAR(50),
    description VARCHAR(255),
    date DATE
);
```

---

### ▶️ Run on Tomcat

1. Import project into Eclipse
2. Configure Apache Tomcat
3. Run on server
4. Open:

```
http://localhost:8080/smart-expense-tracker/
```

---

## ▶️ Usage

* Signup/Login
* Add expenses
* Edit/Delete expenses
* View dashboard charts
* Download CSV/PDF reports

---

## 🌍 Deployment

### 🚀 Using Docker

```
FROM tomcat:10.1-jdk17

RUN rm -rf /usr/local/tomcat/webapps/*

COPY smart-expense-tracker.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080
```

---

### ☁️ Deploy on Render

1. Push project to GitHub
2. Go to Render
3. Create Web Service
4. Select Docker
5. Add environment variables
6. Deploy

---

## 🔐 Environment Variables

```
DB_URL=jdbc:mysql://your-host:3306/expense_tracker
DB_USER=your_username
DB_PASS=your_password
```

---

## 📡 API Endpoints

| Endpoint       | Method | Description  |
| -------------- | ------ | ------------ |
| /login         | POST   | Login        |
| /signup        | POST   | Register     |
| /logout        | GET    | Logout       |
| /expense       | POST   | Add/Update   |
| /dashboard     | GET    | Dashboard    |
| /editExpense   | GET    | Edit page    |
| /deleteExpense | GET    | Delete       |
| /exportCSV     | GET    | Download CSV |
| /exportPDF     | GET    | Download PDF |

---

## Demo

Live Demo:

```
https://your-app.onrender.com
```

---

## 🤝 Contribution

1. Fork repo
2. Create branch
3. Commit changes
4. Push
5. Open PR

---

## 🛠 Troubleshooting

| Issue           | Solution                  |
| --------------- | ------------------------- |
| 404             | Check servlet annotations |
| DB error        | Verify DB credentials     |
| PDF not working | Add itextpdf.jar          |
| CSV not working | Check servlet mapping     |

---

## 👨‍💻 Author

**Aabid**
GitHub: https://github.com/Aabid01

---

⭐ If you like this project, give it a star!
