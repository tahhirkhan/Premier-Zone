# Premier Zone

A Spring Boot REST API for managing soccer player data with PostgreSQL database integration. Follows a three-layer architecture for better separation of concerns.

## Overview

This API provides comprehensive functionality to manage soccer player data, including:
- Retrieving player information with various filters
- Adding new players
- Updating existing player details
- Removing players from the database

The application follows a three-tier architecture pattern and implements standard REST conventions with proper HTTP status code handling.

## Tech Stack

- **Framework:** Spring Boot
- **Database:** PostgreSQL
- **Dependencies:**
  - Spring Data JPA
  - Spring Web
  - Lombok
  - PostgreSQL Driver

## Architecture

The application follows a three-layer architecture:

1. **Presentation Layer (Controllers)**
   - Handles HTTP requests
   - Manages API endpoints
   - Returns appropriate HTTP status codes

2. **Service Layer**
   - Implements business logic
   - Processes data between controllers and repositories
   - Handles data validation

3. **Persistence Layer (Repositories)**
   - Interacts with PostgreSQL database
   - Manages data persistence
   - Implements data access methods

## API Endpoints

### GET Endpoints

- `GET /api/players` - Retrieve all players
- `GET /api/players/team/{teamName}` - Retrieve players by team
- `GET /api/players/position/{position}` - Retrieve players by position
- `GET /api/players/nation/{nation}` - Retrieve players by nationality

### POST Endpoint

- `POST /api/players` - Add a new player
  - Requires player details in request body

### PUT Endpoint

- `PUT /api/players/{id}` - Update an existing player
  - Requires player ID and updated details

### DELETE Endpoint

- `DELETE /api/players/{id}` - Remove a player
  - Requires player ID

## Database Schema

The database currently contains records of approximately 700 soccer players with the following attributes:
- Player ID (Primary Key)
- Name
- Age
- Team
- Position
- Nationality
- Matches Played
- Goals
- Assists

## Features

- **Full CRUD Operations:** Support for Create, Read, Update, and Delete operations
- **Filtered Queries:** Ability to search players by various parameters
- **Proper Response Handling:** Appropriate HTTP status codes for all operations
- **Data Validation:** Input validation for all API operations
- **Clean Code:** Utilizes Lombok annotations for reduced boilerplate code

## Getting Started

### Prerequisites

- JDK 11 or later
- Maven
- PostgreSQL

### Configuration

1. Clone the repository:
```bash
git clone https://github.com/tahhirkhan/Premier-Zone
```

2. Configure database connection in `application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password
```

3. Run the application:
```bash
mvn spring-boot:run
```

## Error Handling

The API returns appropriate HTTP status codes:
- 200: Successful operation
- 201: Resource created successfully
- 400: Bad request
- 404: Resource not found
- 500: Internal server error

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## Author
@tahhirkhan
