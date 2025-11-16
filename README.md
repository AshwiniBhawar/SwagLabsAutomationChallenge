# E-Commerce SauceDemo Website

**This challenge was given by Naveen Khunteta and I'm happy to announce that I was selected as the winner :)**

**Link-** https://shiftsync.tricentis.com/missions-challenges-41/challenge-by-naveen-e-commerce-checkout-flow-with-dynamic-obstacles-2400

This is a test automation framework for website https://www.saucedemo.com This framework automates an E-Commerce web application using Selenium WebDriver, Java, TestNG, and Maven, following a hybrid framework approach

## Tools And Technologies Used To Automate Website

- **Java 17-** Programming language
- **Selenium WebDriver-** UI Test Automation
- **Maven-** Dependency management
- **TestNG-** Testing framework
- **Apache POI-** Excel data reading
- **Log4j-** Logging
- **Allure, ChainTest-** Reports
- **Listeners:** Failure handling/screenshots

## Scenarios Automated

a) Handle multiple user types (standard_user, problem_user) using an Excel file

b) Implement proper error handling for the locked_out_user

c) Add the two most expensive products to the cart and validate that the added product details match the cart product details

d) Verify that the cart badge count updates correctly after adding the products

e) Remove one item from the cart and verify that the cart badge count updates accordingly

f) Verify that the overview page displays the correct item total, tax, and final tota

g) Complete the order and validate the success message

h) Handle the problem_user scenario where images might be broken


## Screenshots/Videos:
1)Suite Execution Video- https://drive.google.com/file/d/13FChnoGRt6arL7ww9iothOlT8GBV2uei/view?usp=sharing

2)Allure Report Outcome Video- https://drive.google.com/file/d/1C-374CNQbOAe0rcPC27CmdFQFMn1anbK/view?usp=sharing 

## Reports

Reports: After execution, a detailed HTML report will be generated at 

1)./allure-results directory.
The report contains information on test cases executed, passed, failed, and skipped, along with screenshots for failed tests.

2)./target/chaintest directory

## Logs
Logs are created during the test execution and stored in the ./logs/ directory.

## Instructions to run a test suite

1)Clone the Repository- git clone <url>

2)Import into IDE-IntelliJ/Eclipse. Open IntelliJ/Eclipse > Import as a Maven project

3)Run Test Suite using testng.xml through IntelliJ/Eclipse or CMD using below command- mvn clean test

4)Generate Allure Report: Open CMD > Go to the project directory > run below command- allure serve allure-results

**Submitted By:**  Ashwini Bhawar **Date:** 08/10/2025                       
**EmailID:** bhawar.ashwini@gmail.com

## 🔗 Links
[![portfolio](https://img.shields.io/badge/my_portfolio-000?style=for-the-badge&logo=ko-fi&logoColor=white)](https://github.com/AshwiniBhawar)
[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/ashwini-bhawar-421020b6/)
