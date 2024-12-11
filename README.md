# Real-Time Ticketing System

This is a real-time ticketing system that supports concurrent ticket releases by vendors and purchases by customers using multi-threading and synchronization. The system is implemented with a Spring Boot backend and a React.js frontend.

## Features

### Backend
- **Concurrency Handling**: Uses multi-threading for concurrent ticket releases and purchases.
- **Synchronization**: Ensures data integrity in a multi-threaded environment.
- **API Endpoints**: Provides endpoints for configuring, starting, stopping, and monitoring the system.
- **Configuration Management**: Allows saving and loading configuration settings in JSON format.

### Frontend
- **Configuration Interface**: Provides a form to input and validate configuration parameters.
- **Dashboard**: Displays real-time ticket availability and allows control of the system.
- **Interactive UI**: Built with React.js, ensuring a seamless user experience.

---

## Technology Stack

### Backend
- Java
- Spring Boot
- Lombok
- Jackson (for JSON processing)

### Frontend
- React.js
- Axios (for API communication)
- CSS (for styling)

---

## Installation

### Prerequisites
- Java 17 or higher
- Node.js 16 or higher
- Maven
- A modern web browser

### Backend Setup
1. Clone the repository.
2. Navigate to the backend directory.
3. Run `mvn clean install` to build the project.
4. Start the server with `mvn spring-boot:run`.
5. The backend will run on `http://localhost:8080`.

### Frontend Setup
1. Navigate to the frontend directory.
2. Run `npm install` to install dependencies.
3. Start the React development server with `npm start`.
4. The frontend will run on `http://localhost:3000`.

---

## API Endpoints

### Configuration
- **POST** `/api/configure`: Configure the system.
- **POST** `/api/config/save`: Save configuration to a file.
- **GET** `/api/config/load`: Load configuration from a file.

### System Control
- **POST** `/api/start`: Start the system.
- **POST** `/api/stop`: Stop the system.

### Monitoring
- **GET** `/api/ticket-count`: Retrieve the current ticket count.

---

## Frontend Components

### ConfigurationForm
- A form to input system parameters (e.g., total tickets, release rate, etc.).
- Validates input and sends the configuration to the backend.

### TicketDashboard
- Displays the current ticket count.
- Provides buttons to start, stop, and refresh the system.

### App
- Manages the flow between configuration and dashboard views.
- Includes a header and footer for consistent layout.

---

## Usage

1. Start both the backend and frontend servers.
2. Open the frontend in your browser (`http://localhost:3000`).
3. Configure the system parameters using the form.
4. Start the system to begin ticket operations.
5. Monitor ticket availability and manage the system from the dashboard.

---

## File Structure

### Backend
```
src/
├── main/java/com/example/demo/
│   ├── DemoApplication.java
│   ├── Configuration.java
│   ├── TicketPool.java
│   ├── Vendor.java
│   ├── Customer.java
│   ├── TicketController.java
│   └── ConfigController.java
```

### Frontend
```
src/
├── components/
│   ├── ConfigurationForm.js
│   ├── TicketDashboard.js
│   ├── Header.js
│   └── Footer.js
├── App.js
├── index.js
├── App.css
└── index.css
```

---

## Future Enhancements
- Add authentication for vendors and customers.
- Provide more advanced reporting features.
- Improve error handling and logging.

---

## License
This project is licensed under the MIT License.

