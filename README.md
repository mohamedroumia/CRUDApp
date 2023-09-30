
# CRUD Application Documentation

## Project Overview

CRUD application is designed to create, read, update and delete information of user.

## Technologies Used

- **Spring Boot:** Version 2.7.1
- **Java:** Version 8
- **Maven:** Version 2.22.2
- **Database:** Docker image mysql

## Prerequisites
- Docker installed on your machine ([Docker Installation Guide](https://docs.docker.com/get-docker/))
- Postman installed on your machine([Download Postman](https://www.postman.com/downloads/)) 
- CRUD application source code

## Follow These Steps To Run CRUD Application On Your Machine:

 1. ## Build And Install CRUD Application

	Open a terminal, navigate to CRUD project root directory and run the following command to build and install CRUD Application.
	```
	mvn clean install
	```

 2. ## Build Docker Image

	Open a terminal, navigate to CRUD project root directory ( where the Dockerfile is located), and run the following command to build the Docker image:
	```
	docker build -t crud-image .
	```

 3. ## Run and Create a MySQL Docker Container: 
	Before running CRUD application in Docker, we need to create a MySQL container. Run the
    following command to create mysql container, database "crud_user" and docker image mysql:
     ``` docker run --name mysql-container -e
    MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=crud_user -p 3306:3306 -d
    mysql:latest 
    ```

 5. ## Create and Link CRUD Application to the MySQL Container:
    
    Create and link crud-app-container to enable communication between CRUD application and the MySQL container following this command: 
    ```
    docker run --name crud-app-container --link mysql-container -p
    8080:8080 crud-image 
    ```

 6. ##  Call CRUD User APIS
    
    To call CRUD User API you have to Download file "crudApp.postman_collection.json" from resources root directory, then export in your postman application.
