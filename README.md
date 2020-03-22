
Download a Framework
-----
https://github.com/tuanbuic/AnabisExercise.git

Tools
------
* Maven
* Cucumber-JVM
* JUnit
* Serenity
* Selenium

Prerequisite
--------------
In order to utilise this project you need to have the following installed locally:
* Install JDK 1.8
* Install Cucumber for Java plugin for your Idea
* Install maven
* Chrome and Chromedriver (UI tests use Chrome by default, can be changed in config)

Framework Architecture
--------------
	anibis_exercise
            |_config
                |_environment.properties
                |_serenity.properties
            |_data
                |data.properties
            |_driver
                |_chromedriver.exe
            |_features
                |_Home.feature
                |_Login.feature
                |_Search.feature
                |_End2End.feature
            |_object_repositories
                |_CommonPage.properties
                |_HomePage.properties
                |_LoginPage.properties
                |_SearchPage.properties
            |src/main
                |_main
                    |java/vn/anibis
                        |_core
                            |_config/
                            |_enums/
                            |_repository/
                            |_web/
                        |_test
                            |_pages/
                            |steps/
                        |_testrunner.hook
                            |_Hooks.java
                        |_util
                            |DateUtil.java
                            |FilesUtil.java
                            |PropetiesUtil.java
                |_test/java
                    |_RunnerTest.java
                    
## Usage
* Write you features and put in features folder                   
* Define Step in src/main/anibis/test/steps                    
* Defined Step definition in src/main/anibis/test/pages
* Put the Object locator in object_repositories
* Data for testing store in data/data.properties

Use object_repositories convention
 --------------------
- We support 4 types of Locator in this framework: ID, Name, Xpath, and CSS
- To define any Type of Locator. You can follow the instruct below
    * Your_Key_Properties_name = **{LocatoerType}**:::**{LocattorValue}**
    * Example: 
        - login.logOn.button = **id**:::**ctl00_phlContent_btnLogin**
        - search.priceRange.input= **//input[@type='number']**
        
Wait Page and Object          
--
Default wait for all object is 10. You can change it in environment.properties files

Setting Browser
-----------------
You can define to run with which browser from TestRunner: **config/environment.properties**  
Change the web.driver.path and web.browser.name to your browser you want. Default is Chrome

Running test
--------------
We supported Running with tag
By Maven: clean install "-Dcucumber.options=--tags @SearchFeature"
Run by Junit: Go to src/test/java/RunnerTest.java-- Change the tag properly and Run the Class

Test Report
--------------
Serenity test report will be auto-generated after running test with **Maven**: $project.dir/target/site/serenity/index.html
           
          