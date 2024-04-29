# BookManagementAPI 
The backend service for [BookManagement Android app](https://github.com/Chengming-Fan/BookManagement) based on Spring Boot and MySQL.

## Prerequisite
+ JDK 17+
+ MySQL

## APIs
+ retrieve all books 
+ retrieve book by id
+ update books
+ delete book by id

## Build & Run Locally
This project has been deployed on AWS ec2 instance, if you want to run it locally, please follow the following steps:

+ set environment variables: 
  + ```ENDPOINT=your database url```
  + ```DB_USERNAME=your database username```
  + ```DB_PASSWORD=your database password```
+ execute command ```./gradlew bootRun```