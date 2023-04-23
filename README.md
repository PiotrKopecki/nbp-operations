# This app allows performing operations with NBP(Narodowy Bank Polski) api
# Demo: https://youtu.be/plUKTdk-HdI

## Tech stack:
### Angular: 15.2.8
### Spring Boot: 3.0.4

# Endpoints:

### 1
```localhost:8080/currency/{currencyCode}/average/exchange-rate```
* Date param in format RRRR-MM-DD required
* This endpoint returns average exchange rate for given currency in given day

### 2
```localhost:8080/currency/{currencyCode}/top/{size}/average/exchange-rate```
* This endpoint returns minimum&maximum average exchange rate for given currency from top {size} last average exchange rates

### 3
```localhost:8080/currency/{currencyCode}/top/{size}/exchange-rate```
* This endpoint returns major diffrence between buy/sell exchange rates for given currency from top {size} last exchange rates

### All currency codes are present in file: CurrencyUtils.java

# How to run this project?

## in directory with docker-compose file run this command:
```
docker-compose up
```

### frontend and backend has their readme files with examples on how to build&run docker images