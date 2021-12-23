Java-Cucumber
======
Built using Java, Selenium, Gradle, Cucumber.

Web Page Object based test automation on Browserstack with parallel option

    
Usage:
---

#### **Remote**
- Add capabitlies to caps.json under src/test/resources/jsonData/caps.json

**Running tests**
----  
**Gradle Wrapper Command Line Test Runs** 
- Open your `Terminal/Powershell`(if you open the terminal from intelliJ you don't need to cd) and `cd`(_**change directory**_) to `project path` on your system
- Example: `C:\Users\yourUserHere\git-projects\projectName`
    - now that we are in the project directory we can use `gradlew tasks` to get more info about the project and how to run tests with it. Scroll up and locate the cucumber groups for project info!  
        - **NOTE:** use `.\gradlew` with powershell !!!
        
- Reports and screenshots are located here for local viewing!!! ```C:\Users\yourUserHere\git-projects\projectName\TestResults```
            
- Run the project with this command

    ```
    gradlew clean build cucumber
    ```   
 


