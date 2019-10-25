# climate-change-analysis
## Steps to run application
### 1) Make sure JAVA_HOME is correctly setup. We would need JAVA 8 for this project.
### 2) Make sure maven is setup on your system.
### 3) Open file "application.properties" and set the correct values of "username" and "password" tokens.
### 4) Try doing "mvn clean install", if the build is successful, then skip to step 4.
### 5) Download "ojdbc8.jar" from https://www.oracle.com/database/technologies/jdbc-ucp-122-downloads.html
### 6) Run "mvn install:install-file -Dfile=path/to/your/ojdbc8.jar -DgroupId=com.oracle -DartifactId=ojdbc8 -Dversion=19.3 -Dpackaging=jar"
### 7) Run the command "java -jar target\climate-change-0.1.0.jar"
### 8) Open "http://localhost:8085/about"