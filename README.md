# Sports Infrastructure Module

## Demo Video:
[![YouTube](http://i.ytimg.com/vi/5aARlBm5Iwo/hqdefault.jpg)](https://www.youtube.com/watch?v=5aARlBm5Iwo)
#### [IRIS Web Rec25 Demo Video 241DS003](https://www.youtube.com/watch?v=5aARlBm5Iwo)

## MVC Architecture:
In this project the files and folders have been arranged according to the conventions of the framework.
Where domain (dto's and entities), mappers, services (business logic), repositories are considered part of the Model. 

## Installation instructions:

### Prerequisites

Ensure you have the following installed on your system:  

- **Java 21+** (for Spring Boot Backend)  
- **Docker** (for database container)
- **Maven** (For running Spring Boot App)
- **VS Code** (For running Frontend)

Clone the repository in the system.

## Steps to run the project:

- Navigate to the Backend/sports directory in the project.
- Docker App should be running in the background.
- Open terminal & type **docker-compose up** to get the database running.
- Now type **./mvnw clean package** then **./mvnw spring-boot:run** in the terminal to get the Backend running.
- Otherwise, the app can be run from an IDE (such as IntelliJ etc.) by running the main method of **SportsApplication.java**.
- Now, open the Frontend folder in VS Code.
- Run any of the .html files in the folder with **Live Server** to get the login page.
- Login for Default Admin using the Credentials: **(Username: admin123@gmail.com & Password: Admin123)**
- Students can be created by Signing Up.

## List of Implemented Features:

#### 1. User Authentication & Security  
- JWT-based authentication & authorization.  
- Role-based access control (Admin & Student).  
- Secure login, signup, and logout functionality.  

#### 2. Equipment & Infrastructure Management  
- CRUD operations for **equipment** & **infrastructure**.  
- Different dashboard views for **Admin** & **Student**.   

#### 3. Request Management  
- Students can **request equipment** and **book facilities**.  
- Admin can **approve/reject requests** (first come, first serve).  
- Students can **cancel approved facility bookings**.  
- Requests sorted differently for Admin (oldest first) & Student (newest first).  

#### 4. Real-Time Updates & Notifications  
- Equipment quantity updates automatically when requests are approved.
- When quantity reaches 0, it is marked as Unavailable without the need of Admin.
- Email notifications using **Java Mailer**.
- Live notifications using Websockets over **STOMP protocol**.
- Frontend notifications for successfull creations, updations, deletions.  

#### 5. UI & Frontend Functionality  
- Responsive dashboard for Admin & Student.  
- User role verification to restrict page access.  
- Proper error handling & form validations.  

#### 6. Backend & Database Integration  
- Spring Boot backend with REST APIs.  
- PostgreSQL database (via Docker container).  
- MapStruct for DTO mappings.

## List of Planned Features:

- Real Time Availability Status using **Server-Sent Events**
- Waitlist System
- Penalty System
- Analytics Dashboard



