Java-Cucumber
======
Built using Java, Selenium, Gradle, Cucumber.

Web Page Object based test automation skeleton with parallel option


Setup
---

## `Download Java`
[Download and get Java installed from here](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
(you can use other jdk's. This is to just get you started)

## `Mac OSX`:
**1.** Open a terminal and proceed with the following:
`$ open ~/.bash_profile
`\
**2.** Set environment variables
```
export JAVA_HOME=$(/usr/libexec/java_home)
export PATH=${PATH}:$ANDROID_HOME/emulator:$ANDROID_HOME/tools:$ANDROID_HOME/platform-tools:$JAVA_HOME/bin
```
\
**3.** Save changes, reopen terminal and enter the following.
- **Homebrew**: 
`$ ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"`
    - After installation: `$ brew doctor` should state `Your system is ready to brew`
- **Git**:`$ brew install git`
    
## `Windows OS`:
**Set windows variables:**
1. open _powershell as admin_ > enter `rundll32 sysdm.cpl,EditEnvironmentVariables` to open windows variables
2. Create and set following for `SYSTEM VARIABLES`. You will click on the `NEW` button to create a variable.
```
Variable name -> JAVA_HOME
Variable path -> path\to\javaSDK (put path to your actual sdk which is usually in your ProgramFiles folder)
```
3. Select `Path` in `SYSTEM Variables` and click `Edit` then click `New` and enter the following for the variables created:
 ```
%JAVA_HOME%\bin
```
3. Close the admin powershell instance

## **Install** [IntelliJ](https://www.jetbrains.com/idea/download):       

When intellij is open do the following 
`Import Project` > `find where you git cloned the project to and select it` > `Gradle` > continue through the steps to import project. 

Install cucumber plugins
- `File` > `Preferences/Settings`_ > _`Plugins`_ > _`Marketplace`_:
- _`Cucumber for Java`_
- _`google-java-format`_
    - **How to use**: Enable the plugin in `Other Settings` 
        - `Ctrl + Alt + L` to format or `right click on file` > `Reformat Code`
    
Usage:
---

#### **Remote**
- Add capabitlies to caps.json under src/test/resources/jsonData/caps.data


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


