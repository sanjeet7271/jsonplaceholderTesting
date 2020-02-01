# FREENOW Backend Test Challenges 

## Features supported
1. Dependency management and project management by using MAVEN 
2. API Testing using Rest Assured Library
3. TestNG for testing workflow
4. TestNG reports
5. Code coverage using jococo
6. GIT as distributed version-control system
7. Logging via Log4j
8. Property Reader to read Test data from properties files.
9. Custom assertion to print custom message when assertion fails.
10. Maven is configured in such a way that will run different testNG.xml provided at run time.

## Pre-requisite
1. Java above 1.8.
2. Maven version above 3.0.
3. TestNG 6.14.3
4. RestAssured 4.0.0 and above
5. google gson-simple 2.8.5
6. Jacoco 0.8.2

## How to install & Run using command prompt
1. Please extract the project at your desired path.
2. Go to `src/test/resources/config/config.properties` file and update configurations. 
3. Open the command prompt and go to the project path.
4. Run the command "mvn clean install -DsuiteXmlFile=testng.xml"
5. All the automated test cases in the testNG.xml will be executed.