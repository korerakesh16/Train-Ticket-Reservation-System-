# Train-Ticket-Reservation-System-
Train Ticket Reservation System is a web-based project that allows users to search trains, check seat availability, view schedules, and book tickets online. Admins can manage train data. Built using Java, Servlets, JDBC, HTML, CSS, Bootstrap, and Oracle SQL.
\Train Ticket Reservation System
Project Overview
This project is a web-based Train Ticket Reservation System developed to help users easily book train tickets online. It allows passengers to check train schedules, search for trains between stations, view seat availability, and make secure reservations from the comfort of their homes. The system also includes an admin panel where administrators can manage train details and bookings.
The main aim of this project is to provide a user-friendly platform for train-related services, reduce manual work, and make the process faster, easier, and more secure.

 Website Features
User Side:
•	User Registration and Login – New users can sign up and securely log in.
•	View Train Schedules – Check available trains and their timing.
•	Search Trains – Search by source and destination.
•	Seat Availability – Know how many seats are left before booking.
•	Train Timings – View departure and arrival times.
•	Fare Enquiry – Check ticket prices for different routes and classes.
•	Trains Between Stations – See what trains are available between two specific stations.
•	Online Ticket Booking – Book tickets by entering passenger and journey details.
•	Booking History – View all past and upcoming ticket bookings.
•	Profile Management – Update profile details and change password.
•	Logout – Securely log out from the system.
 Admin Side:
•	Admin Login
•	Add New Trains – Add train names, numbers, routes, and timings.
•	Update Trains – Make changes to train schedules or seat details.
•	Remove or Cancel Trains – Option to cancel or delete train records.
•	View All Trains – Check the list of trains in the system.
•	Edit Profile
•	Logout

Modules in the System
1.	Login System – For both users and admin with password protection.
2.	Train Management – Add/update/delete train details (Admin only).
3.	Seat Reservation – Book tickets and assign seats.
4.	Fare Calculation – Calculate price based on distance and travel class.
5.	Payment Process – Simple payment simulation for booking.
6.	Booking Records – Maintain a history of bookings.
7.	User Profile – Manage personal information.

Technologies Used
Front-End:
•	HTML – For webpage structure
•	CSS – For styling the pages
•	Bootstrap – For responsive layout and design
Back-End:
•	Java  – Main logic for the application
•	Servlets – To handle user requests and backend processing
•	JDBC – For connecting Java code to the database
Database:
•	Oracle SQL – To store all the data like users, trains, bookings, etc.

 Requirements
•	Java (JDK)
•	Apache Tomcat Server
•	Oracle Database or SQL Developer
•	Eclipse IDE (or any Java IDE)
•	Web Browser (Chrome, Firefox)

How to Run the Project
1.	Open the project in Eclipse IDE.
2.	Set up the Oracle database using the provided SQL scripts.
3.	Update database connection details in the JDBC code.
4.	Run the project on Apache Tomcat server.
5.	Open your browser and visit:
http://localhost:8081/TrainTicketReservationSystem/

Sample Credentials
Admin
•	Username: admin
•	Password: admin123
User
•	You can create a new account using the register option.

Possible Future Improvements
•	Integration with live IRCTC APIs (for real-time train data)
•	OTP or email verification during registration
•	Downloadable e-ticket (PDF)
•	Multilingual support
•	Role-based access (admin, user, ticket checker, etc.)

