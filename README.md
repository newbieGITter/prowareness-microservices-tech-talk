# prowareness-microservices-tech-talk
This repository contains demo shown for Prowareness tech talk. It includes code of a monolithic application being refactored into small, 
autonomous microservices which interact with each other using RestTemplate; service registry implementation(Netflix Eureka)

1. customer-order-product-monolith: This module contains code of a monolithic application where different functional components are built
in same module using same technology & deployed on same server instance. So a change in 1 component means that the whole application has
to be deployed again leading to high downtime of application.

To run this application using Spring Boot, you can use following command:-
spring-boot:run -Dserver.port=8080 
-- here 8080 is the port number of server instance on which you want to deploy your application

2. The above monolithic application is then split into 3 microservices:-
customer-microservice
product-microservice
order-microservice

To run this application individually(on different server instance), you can use below command for each module respectively:-
spring-boot:run -Dserver.port=8081 
spring-boot:run -Dserver.port=8082 
spring-boot:run -Dserver.port=8083 

3. microservices-demo-master
This module contains code which uses Netflix service registry API, Eureka. It follows client discovery mechanism to identify different 
services running in your application dynamically.
