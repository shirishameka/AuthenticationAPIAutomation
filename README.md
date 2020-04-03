# Authentication-API-Automation


This project has automated tests for User Sign-Up/Registration API and User Login API.

### Modules

* User Sign-Up/Registration API
* User Login API

### Tech

This project uses a number of open source projects to work properly:

* Java
* TestNG
* Slf4j
* Lombok

### How to run this project

Checkout the project from gitlab using following command
```sh
$ git clone https://github.com/shirishameka/AuthenticationAPIAutomation.git
```

Get in-to the project folder

```sh
$ cd AuthenticationAPIAutomation
```

To build the project and run the tests
```sh
$ mvn clean install
```

To only run the tests
```sh
$ mvn clean test
```

### Reading Logs

Tests write logs while they are running which provide further information about what happened if case of failure.
These files reside in ./logs directory and can be viewed locally in your browser by navigating to that folder.


