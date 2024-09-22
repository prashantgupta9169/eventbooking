# eventbooking
Event Booking


# Spring Boot Application with MySQL

This project is a Gradle-based Java Spring Boot application that uses MySQL as its database. Below are the instructions to configure the MySQL connection, including changing the username, password, and database name, and then running the application.

## Prerequisites

Before you begin, ensure you have the following installed:
- JDK 1.8 or later
- Gradle 4+ (if you're not using the Gradle Wrapper)
- MySQL Server

## Configuring MySQL

1. **Create a MySQL Database**: Log in to your MySQL server and create a new database for the application.

    ```sql
    CREATE DATABASE your_database_name;
    ```

2. **Create a MySQL User (Optional)**: You can use an existing user or create a new user for the application.

    ```sql
    CREATE USER 'your_username'@'localhost' IDENTIFIED BY 'your_password';
    GRANT ALL PRIVILEGES ON your_database_name.* TO 'your_username'@'localhost';
    FLUSH PRIVILEGES;
    ```

Replace `your_database_name`, `your_username`, and `your_password` with your desired database name, MySQL username, and password.

## Configuring the Application

1. **Edit Application Properties**: Navigate to `src/main/resources` and open the `application.properties` file.

2. **Set MySQL Credentials**: Update the MySQL connection properties with your database name, username, and password.

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
    spring.datasource.username=your_username
    spring.datasource.password=your_password
    ```

Replace `your_database_name`, `your_username`, and `your_password` with the values you used in the MySQL configuration step.

## Building the Application

Before running the application, you need to build it using Gradle. This process compiles your application and packages it, making it ready to run. Use the Gradle Wrapper included in the project for consistency across different environments.

Open a terminal or command prompt in the project directory and execute the following command:

```bash
./gradlew build

## Running the Application

You can run the application using the Gradle Wrapper included in the project. Open a terminal or command prompt in the project directory and execute the following command:

```bash
./gradlew bootRun


Accessing the Application
Once the application is running, you can access it by navigating to http://localhost:8080 in your web browser (or the appropriate port if you've configured it differently).

Conclusion
You've now configured and started your Spring Boot application with a MySQL database. For further customization and development, refer to the Spring Boot and MySQL documentation.
