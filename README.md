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
11. Used dataProvider to create multiple user, post and comments, reading test data from excel sheet
12. Code tested on CI/CD Jenkins

## Pre-requisite
1. Java version above 1.8.
2. Maven version above 3.0.
3. TestNG 6.14.3
4. RestAssured 4.0.0 and above
5. google gson-simple 2.8.5
6. Jacoco 0.8.2
7. Jackson
8. APache POI

## How to install & Run using command prompt
1. Please extract the project at your desired path.
2. Go to `/src/main/resources/config/config.properties` file and update user name in configurations.
	for e.g.	if you want to run test cases for "Samantha", then just update name into username field
				like : username=Samantha
3. Go to `/src/main/resources/testdata/TestData.xlsx`	file and update user and post test data as much as you want to create new user and create new post		
4. Open the command prompt and go to the project path.
5. Run the command "mvn clean install"
6. All the automated test cases in the testNG.xml will be executed.

		
## To view Report 
1. Go to the root directory under `/test-output/emailable-report.html`
2. All code coverage reports are saved under `/target/jacoco-ut/` 


Note: Test cases and issues with these API are available in `Testcases.xlsx` for your reference.
