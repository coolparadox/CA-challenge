# contaazul-challenge

This is the answer to a challenge of building a REST interface using Java / Spring Boot.

To build and test:
./gradlew build

To run:
./gradlew bootRun

To run, alternatively:
java -jar build/libs/contaazul-mars-robot-0.1.0.jar

It runs a local http server listening on port 8080. Endpoint is /rest/mars/

For online help:
curl -s --request GET http://localhost:8080/rest/mars

For issuing commands:
curl -s --request POST http://localhost:8080/rest/mars/[COMMAND]

See online help for COMMAND description.

Regards,
Rafael Lorandi
http://github.com/coolparadox
