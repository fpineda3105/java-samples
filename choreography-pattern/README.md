# Choreography Pattern  [![Build Status](https://travis-ci.com/fpineda3105/java-samples.svg?branch=main)](https://travis-ci.com/fpineda3105/java-samples)

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
