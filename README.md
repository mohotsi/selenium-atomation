sudo docker run --rm -ti --name zalenium -p 4444:4444 \
      -v /var/run/docker.sock:/var/run/docker.sock \
      -v /tmp/videos:/home/Thapelo/videos \
      --privileged dosel/zalenium startsudo docker run --rm -ti --name zalenium -p 4444:4444 \
      -v /var/run/docker.sock:/var/run/docker.sock \
      -v /tmp/videos:/home/Thapelo/videos \
      --privileged dosel/zalenium startsudo docker run --rm -ti --name zalenium -p 4444:4444 \
      -v /var/run/docker.sock:/var/run/docker.sock \
      -v /tmp/videos:/home/Thapelo/videos \
      --privileged dosel/zalenium start# selenium-automationsudo docker run --rm -ti --name zalenium -p 4444:4444 \
      -v /var/run/docker.sock:/var/run/docker.sock \
      -v /tmp/videos:/home/Thapelo/videos \
      --privileged dosel/zalenium start

Test Automation Selenium 

# Selenium with Springboot starter only

SOFTWARE DEVELOPMENT IN TEST

#The project was created uisng java 8 jdk

# maven project (page object model)

#To develop the project I used intelliJ IDE
Libraries added in pom.xml
cuccumber for Gerkins
Lambok to reduce code I need to write
Selenium for web automation
Selenium Manager so that I don't have to waste time getting driver for browsers
springboot to manage my java beans
TestNG for assertion and reporting

to run it directly on IDE
run testNG file
testng.xml
src/main/resources/testng.xml

run with firefox on terminal

mvn clean test -Dbrowser=firefox


to run remotely specify  with firefox

mvn clean test -Dbrowser=chome -Dspring.profiles.active=remote

thread count is configured in the pom.xml

	<configuration>
					<testFailureIgnore>true</testFailureIgnore>
					<parallel>both</parallel>
					<threadCount>3</threadCount>
				</configuration>

#Report setting on : target/output/HtmlReports.html, target/output/HtmlReports.json

the source root directory has selenium beans
the test directory has pages; features, definitions,cucumber config, cucumber runner









