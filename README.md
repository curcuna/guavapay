# Guavapay-Parcel Management


This project, based on microservice oriented architecture, conceptually operatares as a parcel delivery application.

Instructions to try it out
1)	Download the project.
2)	Download Docker Desktop
3)	At the level of where compose.yml file, type “docker compose up” in terminal or command window.
4)	Open the page <b><i>localhost:4200</i></b> in browser.

Architecture
The project consists of 4 microservice established on SpringBoot framework and 1 message broker ,namely Rabbitmq. Also the angular client app is added for trying server part.

Component Uml Diagram
Client app can only communicate with auth, order and cargo services via gateway service. Due to the advantage of role based access checking and token validation could be done at gateway with the help of auth service , this architecture is chosen. After ensuring authorized and authenticated request has come, the gateway transfer the request to related service.
Because of microservice nature, message broker is used for manipulating related data in other services. If an update comes for Cargo service, it pushes an update event to order service over Rabbitmq message brokers.

<img width="454" alt="image" src="https://user-images.githubusercontent.com/37071612/190000615-68c2f8ed-142f-4df4-99a3-2d0834acc242.png">

Documentation
Swagger is used for rest service documentation.

Techonologies used:
Java 18, Angular 14 , Gradle, Spring boot, Spring Cloud Gateway, Rabbitmq, Swagger, Docker

