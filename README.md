# Saucelabs coding challenge - An Application for monitoring specific server's health status

## How to run this application

Make sure you have maven and JDK version >= 8 properly installed, then you can either run this application by following command from the current project diretory.

```
mvn spring-boot:run
```

Or you can build this project into a jar file

```
mvn clean package
```

then run this jar file by following command.

```
java -jar target/monitor-1.0.0-SNAPSHOT.jar
```


After started this application, you can check the output from both terminal window or file `./monitor.log` (**which needed to be created before running this application**)
Basically there are three types of output indicating different status of server API.

- Server is healthy (it returns 200): `INFO Server is working.`
- Server returns wrong status code: `ERROR Server returned status code 500`
- Cannot connect to server: `ERROR Failed to connect to server.`

Also, you can configure following properties from `application.properties` file.

```
server.url = YOUR_SERVER_ADDRESS_WITH_PORT
schedule.interval = MONITOR_CHECK_INTERVAL
logfile.path = LOGFILE_PATH
```

## TODO

- need to add tests if I have enough time :(
