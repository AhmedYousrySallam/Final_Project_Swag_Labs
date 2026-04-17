# 🧪 Swag Labs – Manual & Automation Testing Project

<p align="center">
  <img src="https://i.ytimg.com/vi/hypO6hgSzP4/sddefault.jpg" width="450"/>
</p>

This project demonstrates both manual and automation testing for the Swag Labs web application.

## 🎯 Project Scope
The project includes:
- Manual test cases
- Test scenarios
- Expected vs Actual Results
- Functional testing
- UI validation
- Error message validation
- End-to-end workflow testing
- Automation testing using Selenium WebDriver, TestNG, Maven, and Page Object Model (POM)

## 📌 Covered Modules
- Login
- Products
- Add to Cart
- Sorting
- Checkout
- Complete Order Flow
- Logout

## 🛠️ Tools and Technologies
- Java
- Selenium WebDriver
- TestNG
- Maven
- Page Object Model (POM)
- Manual Testing

---

## 📁 Project Structure
```text
Final_Project_Swag_Labs/
│
├── Automation Testing/
│   ├── pom.xml
│   └── src/
│       ├── main/java/Swag/
│       │   ├── LoginData.java
│       │   ├── LoginPage.java
│       │   ├── ProductsPage.java
│       │   └── LogoutPage.java
│       └── test/java/Swag/
│           ├── BaseTest.java
│           ├── LoginPageTest.java
│           ├── ProductsPageTest.java
│           ├── CheckoutFlowTest.java
│           ├── MultiUserCheckoutTest.java
│           └── MultiUserLogoutTest.java
│
└── Manual Testing/
    ├── Login Test Cases
    ├── Products Page Test Cases
    ├── Checkout Test Cases
    ├── Test Scenarios
    └── Expected vs Actual Results
```

---

## 📝 Manual Testing Coverage
✔️ Functional Test Cases  
✔️ UI/UX Validation  
✔️ Error Message Verification  
✔️ Input Validation Scenarios  
✔️ Add-to-Cart + Sorting Test Scenarios  
✔️ End-to-End Flow Testing  

Manual testing files include:
- Login Test Cases  
- Products Page Test Cases
- Checkout Test Cases  
- Test Scenarios  
- Expected vs Actual Results

---

## 🤖 Automation Testing Coverage

### 🔐 Login Page
- Valid login
- Invalid login (wrong username/password)
- Locked user validation
- Multiple user login scenarios
- Assertion of displayed error messages

### 🛒 Products Page
- Validate product list
- Add to cart functionality
- Product name and price assertions
- Sorting tests (A → Z, Z → A, Low → High, High → Low)
- Cart badge and cart content validation
- Logout from products page

### 💳 Checkout
- Valid checkout
- Required fields validation
- Error messages
- Price and totals validation
- Verify items in overview
- Continue and cancel actions
- Complete order
- Confirm success page
- Multi-user checkout scenarios

---

## ▶️ How to Run the Automation Tests

### 1️⃣ Clone the project
```bash
git clone https://github.com/AhmedYousrySallam/Final_Project_Swag_Labs.git
```

### 2️⃣ Navigate to the automation project
```bash
cd Final_Project_Swag_Labs/Automation\ Testing
```

### 3️⃣ Install Maven dependencies
```bash
mvn clean install
```

### 4️⃣ Run tests with TestNG
```bash
mvn test
```

---

## 🧑‍🤝‍🧑 Team Members
| Name | Role |
|------|------|
| **Ahmed Abo Elsuad** | QA Engineer |
| **Ahmed Yousry** | QA Engineer |
| **Hager Hussien** | QA Engineer |

---

## ⭐ If you like this project
Please consider starring ⭐ the repository — it motivates us to contribute more!

---

## 📄 License
This project is for educational and training purposes.
