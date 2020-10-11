# Choreography Pattern  [![Build Status](https://travis-ci.com/fpineda3105/java-samples.svg?branch=main)](https://travis-ci.com/fpineda3105/java-samples) [![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=fpineda3105_java-samples&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=fpineda3105_java-samples) [![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=fpineda3105_java-samples&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=fpineda3105_java-samples) [![Bugs](https://sonarcloud.io/api/project_badges/measure?project=fpineda3105_java-samples&metric=bugs)](https://sonarcloud.io/dashboard?id=fpineda3105_java-samples) [![Coverage](https://sonarcloud.io/api/project_badges/measure?project=fpineda3105_java-samples&metric=coverage)](https://sonarcloud.io/dashboard?id=fpineda3105_java-samples) [![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=fpineda3105_java-samples&metric=duplicated_lines_density)](https://sonarcloud.io/dashboard?id=fpineda3105_java-samples) [![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=fpineda3105_java-samples&metric=code_smells)](https://sonarcloud.io/dashboard?id=fpineda3105_java-samples)

## What it is?
It's a backend order system using the Choreography Pattern

## Technologies used?
- Java 11
- Spring Boot 2.3
- Google Guava Event Bus
- Database in memory H2

## Patterns
- Event-driven messaging
- Choreography
- Hexagonal Architecture
- Command

## Features
- Place an Order
- Commit an Order
- Fetch an Order by id

## How to set up?
```
mvn install 
```

### All code is tested using an 80% code coverage Rule
```
mvn verify
```

### Endpoints
**Post** https://localhost:8080/orders

**Request** 
``` 
   {
    "productId" : 123,
    "customerId": 34234,
    "quantity": 2
   }
   ```

**Response** 
``` 
   {
    "id": 1,
    "quantity": 2,
    "productId": 123,
    "customerId": 34234,
    "status": "PLACED"
    }
   ```

**Get** https://localhost:8080/orders/{id}

**Response** 
```{
    "id": 1,
    "quantity": 2,
    "productId": 1,
    "customerId": 34234,
    "status": "COMMITTED"
   }
   ```
