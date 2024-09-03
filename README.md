# Java-Spring-Template-Example

## Requirement
- Java 17
- maven 3.9.0
- Postgresql ^15
- tomcat 9 (for production)

## Getting Start
- Build: mvn clean install
- running: mvn spring-boot:run

## Procject Structure
- Controller: setup Rest API Endpoint
- Model: Entity Mapping with database tables
- Repository: JPA Based for ORM
- Service: Service Interface for define function
- ServiceImpl: Implement function from Service Interface

## Application Setting
- you need to set up your configuration in application.properties file.

## Spesial Notes
- This project build with Springboot 3.1.0, all plugin needed will update later