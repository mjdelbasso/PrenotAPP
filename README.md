PrenotAPP: Simple REST API for Booking Appointments
Project Description:
PrenotAPP is a straightforward REST API developed for practice purposes, simulating an ecosystem of shops and clients. Each shop offers various services categorized by type, and clients can easily schedule appointments with these shops. It's designed to facilitate booking appointments at barber shops, tattoo studios, beauty salons, and similar establishments.

Key Features:

Shop Management: Shops can register and manage their profiles, including details such as business hours, available services, and appointment availability.

Category Management: Shops can categorize their services into different categories to streamline navigation and organization.

Client Registration: Clients can create accounts and manage their profiles, including personal information and appointment preferences.

Appointment Scheduling: Clients can browse available appointment slots at different shops and book appointments according to their convenience.

Yet to implement: 

** Notification System: Clients receive notifications regarding appointment confirmations, reminders, and cancellations to keep them informed and updated.

** Admin Dashboard: Administrators have access to a dashboard for managing shops, categories, clients, and appointments, ensuring smooth operation of the platform.

Technologies Used:
Backend: Java 21, Spring Boot
Database: PostgreSQL
Authentication: JWT (JSON Web Tokens) // <--- Not implemented yet.
Data Validation: Spring Data Validation
Installation and Setup:
To set up PrenotAPP on your local environment, follow these steps:

Clone this repository to your local machine:

bash
Copy code
git clone <repository_url>
Navigate to the project directory:

bash
Copy code
cd PrenotAPP
Configure the PostgreSQL database:

Set up a PostgreSQL database on your local machine or a remote server.
Update the database configuration in the application.properties file with your database credentials and connection details.
Start the Spring Boot application:

Run the application using your preferred IDE or with the following command:
arduino
Copy code
./mvnw spring-boot:run
The API will be accessible at http://localhost:8080.

Usage:
Refer to the API documentation or Postman collection provided in the docs directory for information on available endpoints and request formats.
Contributing:
Contributions to PrenotAPP are welcome! If you have suggestions for improvements or would like to report a bug, please open an issue or submit a pull request on GitHub.

License:
This project is licensed under the MIT License.

Contact:
For any inquiries or feedback, feel free to contact the project maintainer at mjdelbasso.it@gmail.com.

Enjoy using PrenotAPP for managing appointments effortlessly!
