# 🧩 SauceDemo Automation Framework

## Overview
This project is a **Selenium WebDriver Test Automation Framework** developed using **Java**, **TestNG**, and **Maven**, following the **Page Object Model (POM)** design pattern.  
It automates key functional test cases for the **SauceDemo** e-commerce application — including login validation, product sorting, cart functionality, and checkout flow.

The goal of this project is to demonstrate best practices in **UI test automation**, **framework design**, and **reporting**.

---

## 🚀 Features

- 🧱 **Page Object Model (POM)** for modular and maintainable test code  
- 🧪 **TestNG** for test execution and reporting  
- 🧰 **Maven** for dependency and build management  
- 📊 **Extent Reports** for detailed HTML test reports with screenshots  
- ⚙️ **Data-driven testing** (via Excel or JSON)  
- 🌐 Cross-browser support (Chrome, Firefox, Edge)  
- 🔒 Validations for positive and negative login scenarios  
- 🛒 Automated tests for product sort, cart, and checkout

---

## 🧠 Tech Stack

| Tool / Library | Purpose |
|-----------------|----------|
| **Java** | Programming language |
| **Selenium WebDriver** | UI automation |
| **TestNG** | Test framework |
| **Maven** | Build and dependency management |
| **Extent Reports** | Reporting |
| **Apache POI / JSON** | Test data management |

---

## 🧪 Test Scenarios Covered

1. **Login Tests**
   - Valid and invalid login credentials  
   - Locked user and error message validation  

2. **Product Tests**
   - Verify product sorting (A–Z, Z–A, price low–high, high–low)  
   - Validate product details page  

3. **Cart Tests**
   - Add and remove items from cart  
   - Validate cart count and total  

4. **Checkout Tests**
   - Verify successful checkout process  
   - Validate order confirmation message  

📊 Test Reports

After execution, detailed HTML reports will be generated under:

/test-output/ExtentReports/


Each report includes:

Test execution summary

Pass/fail statistics

Screenshots for failed test cases

🧾 Example Test Flow

Launch browser and open https://www.saucedemo.com/

Login using test credentials

Sort products and add to cart

Proceed to checkout and complete order

Validate success message and close browser

📸 Sample Report Preview

(Add a screenshot of your Extent Report here, e.g. screenshots/report-example.png)

📚 Future Enhancements

Add retry mechanism for flaky tests

Integrate with CI tools like GitHub Actions

Include API and database validation layer

🏁 Conclusion

This project demonstrates how to build a structured, maintainable automation framework using Selenium, Java, and TestNG, focused on reliability, reusability, and detailed reporting.



Scanning
