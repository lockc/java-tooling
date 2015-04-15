# Spring Actuator Examples

This example demonstrates using the Spring Actuator module.  It also includes the Jolokia JMX to HTTP bridge 
coupled with hawt.io for a nice web based management console.

* http://www.jolokia.org/index.html
* http://hawt.io


## Instructions

* Download the hawtio app and run
> java -jar hawtio-app-1.4.45.jar --port 8090

* Start the web application
> mvn spring-boot:run

* Go to http://localhost:8090/hawtio/ and click **Connect**
* Enter the connection settings for th web application 
> Note the Jolokia endpoint will be *manage/jolokia*

Now you should see some cool management console.

