# Omnify_Assesment
✅ Selenium Test Automation Framework (TestNG + Java + Maven)

This project is a robust Selenium-based test automation framework built using **Java**, **TestNG**, and **Maven**. It supports scalable web UI testing, handles test configuration through property files, and includes features like test report generation and automated copying of reports to the user's Downloads folder.

## 📌 Table of Contents

*[📦 Project Structure](#-project-structure)

*[🧰 Technologies Used](#-technologies-used)

*[⚙️ Setup & Installation](#️-setup--installation)

*[📊 Test Reports](#-test-reports)

*[🛠️ Customization](#️-customization)

*[🧪  How to Run the Framework](#-how-to-run-the-framework)

*[🧪 Test Report/Output Screenshot][#-Report/Output-Screenshot]

*[📌 Features / Best Practices Followed](#-best-practices-followed)

*[👤 Author](#-author)


## 📦 Project Structure

```
📦 QA
├── 📂 src
│   ├── 📂 main
│   │   └── 📂 java
│   │       ├── 📂 base                            # Base test setup
│   │       │   └── BaseTest.java
│   │       ├── 📂 pages                           # Page Object Model classes
│   │       │   ├── DashboardPage.java
│   │       │   ├── EmployeeAdd.java
│   │       │   ├── LoginPage.java
│   │       │   └── VerifyEmployee.java
│   │       └── 📂 utils                           # Utility classes
│   │           ├── ConfigReader.java
│   │           └── copyReportToDownloads.java
│   ├── 📂 test
│   │   ├── 📂 java
│   │   │   └── 📂 tests                           # Test classes for functionality
│   │   │       ├── DashboardTest.java
│   │   │       ├── EmployeeAddTest.java
│   │   │       ├── LoginTest.java
│   │   │       └── VerifyEmployeeTest.java
│   │   └── 📂 resources
│   │       └── config.properties                   # Application config
├── 📂 test-output                                  # TestNG default reports
│   └── emailable-report.html                       # Generated test report
├── 📄 pom.xml                                      # Maven build descriptor



```

## 🧰 Technologies Used

| Technology           | Version                                                                        | Purpose                                    |
| -------------------- | ------------------------------------------------------------------------------ | ------------------------------------------ |
| **Java**             | 21.0.7                                                                         | Programming language                       |
| **Selenium**         | 4.32.0                                                                         | Web automation framework                   |
| **TestNG**           | 7.11.0                                                                         | Testing framework                          |
| **WebDriverManager** | 5.8.0                                                                          | Manages browser drivers automatically      |
| **Eclipse IDE**      | 2025-06 M2 (4.36.0)                                                            | Development environment                    |
| **Google Chrome**    | 136.0.7103.114                                                                 | Browser for test execution                 |
| **Maven**            | 3.3.9.900                                                                      | Build and dependency management            |


## ⚙️  Setup & Installation

## Prerequisites

Ensure the following tools and environments are installed on your system:

Eclipse IDE for Enterprise Java and Web Developers – Version 2025-06 M2 (4.36.0)

Google Chrome Browser – Version 136.0.7103.114

Stable Internet Connection –
Required for:
        TestNG installation

        WebDriverManager (auto-downloads browser drivers)


## 🛠️  Customization

* To change BaseUrl / Username and Password/ Names added , Update/modify config.properties file.


| Property     | Description                          |
|--------------|--------------------------------------|
| `baseUrl`    | URL of the application under test     |
| `username`   | Login username                        |
| `password`   | Login password                        |
| `employee1`  | Employee name in `First,Middle,Last` format |
| `employee2`  | Additional employee entry             |
| `employee3`  | Additional employee entry             |
| `employee4`  | Additional employee entry             |
```
Example:
```properties
baseUrl=https://opensource-demo.orangehrmlive.com/web/index.php/auth/login  
username=Admin  
password=admin123  
employee1=John,F.,Doe  
employee2=Alice,B.,Smith  
employee3=Michael,B.,Brown  
employee4=Sara,M.,Connor  
```

##🧪  How to Run the Framework

##🔧  Step-by-Step Setup

Install Eclipse IDE

Download and install Eclipse IDE for Java Developers:
👉 https://www.eclipse.org/downloads

download the Github repository ZIP and extract it locally.

Import the Project into Eclipse

        Open Eclipse
        
        Go to File → Import → Maven → Existing Maven Projects
        
        Select the extracted project folder
        
        Click Finish to import

Install TestNG Plugin (if not already installed)
        
        Go to Eclipse -> Help → Eclipse Marketplace
        
        Search for TestNG for Eclipse
        
        Click Install and follow the installation steps
        
        Restart Eclipse when prompted
        Need visual guidance? You can follow this YouTube tutorial or any other general youtube videos:
        [https://www.youtube.com/watch?v=J9VM_JyiJFo]

Add maven to your Build Path
	
	Right click on Project name in Eclipse IDE file explorer

	Select Build Path -> Configure Build Path
	
	Click on java Build Path -> Libraries -> Class Path -> Add libraries
	
	Click on TestNG -> Next -> Finish        

Run the Tests

        Open any test class from the tests package
        
        Right-click inside the class
        
        Select Run As → TestNG Test
        
        ✅ Tests are detected using TestNG annotations (e.g., @Test) — no XML suite or Main class file is needed.

> **Note:** WebDriverManager automatically downloads and manages the required browser drivers.


## 📊  Test Reports

After test execution, reports are generated and managed as follows:

- **Default Location:**  
  TestNG automatically generates the test report (`emailable-report.html`) inside the `test-output/` directory of the project.

- **Custom Feature:**  
  After each test run, the report is also **automatically copied** to the user's **Downloads** folder with a unique timestamp, for example:  
  `emailable-report_20250522_153045.html`

This allows for easier access and archiving of past reports outside the project structure.

## Report/Output Screenshot
![Screenshot 2025-05-28 121035](https://github.com/user-attachments/assets/ac86bade-eb98-4373-a278-8e179f9d2fec)



## 📌 Features / Best Practices Followed

- 🚀 One-click test execution via TestNG
- 🧱 Modular Page Object Model (POM) design
- 🛡️ Robust error handling and logging
- 📁 Configurable via external `config.properties`
- 📊 Test reports auto-generated and copied to Downloads
- 🔧 Easily scalable for additional tests and pages

## 👤 Author

**Nagaraju K**  
📧 Email: [nraju316@gmail.com]  
📞 Phone: +91-6363909369  
💼 LinkedIn: [ https://www.linkedin.com/in/nagaraju-k-50888325a ]
