# MDM Mock Service

This is a mock Master Data Management (MDM) service built with Java and Spring Boot. It provides two main REST endpoints to add and search for recipients using a configurable 7-match criteria. The application uses an in-memory H2 database and comes preloaded with sample data for easy testing.

## Features
- **Add Recipient**: Add a new recipient to the database.
- **Search Recipient**: Search for recipients using a 7-field match criteria with configurable weights.
- **In-memory H2 Database**: No setup required; data is preloaded for testing.
- **Configurable Match Weights**: Adjust match logic via `application.properties`.

## Endpoints

### 1. Add Recipient
- **URL**: `POST /api/recipients/add`
- **Body**: JSON object with recipient fields (firstName, lastName, email, phone, address, city, state, zip)
- **Response**: The saved recipient object

### 2. Search Recipient
- **URL**: `POST /api/recipients/search`
- **Body**: JSON object with any combination of recipient fields to search by
- **Response**: List of matching recipients, sorted by match score (highest first)

## 7-Match Criteria Logic
The search endpoint uses a 7-field match logic. Each field (firstName, lastName, email, phone, address, city, zip) is compared between the search criteria and each recipient in the database. If a field matches exactly, its configured weight is added to the recipient's match score. Recipients with a score greater than 0 are returned, sorted by score descending.

**Example Weights (from `application.properties`):**
```
match.weights.firstName=10
match.weights.lastName=10
match.weights.email=20
match.weights.phone=15
match.weights.address=5
match.weights.city=5
match.weights.zip=5
```

## Configuration
- **Database**: Uses H2 in-memory DB. Access the H2 console at `/h2-console` (JDBC URL: `jdbc:h2:mem:testdb`).
- **Match Weights**: Change weights in `src/main/resources/application.properties` under the `match.weights.*` keys.

## Sample Data
The application preloads several recipients (see `src/main/resources/data.sql`).

## Running the Application
1. Build and run the Spring Boot application (e.g., via your IDE or `mvn spring-boot:run`).
2. Use a tool like Postman or curl to test the endpoints.
3. Access the H2 console at [http://localhost:8080/h2-console](http://localhost:8080/h2-console) (JDBC URL: `jdbc:h2:mem:testdb`).

## Example Requests

### Add Recipient
```
POST /api/recipients/add
Content-Type: application/json
{
  "firstName": "Sam",
  "lastName": "Wilson",
  "email": "sam.wilson@example.com",
  "phone": "5678901234",
  "address": "555 Elm St",
  "city": "Aurora",
  "state": "IL",
  "zip": "60505"
}
```

### Search Recipient
```
POST /api/recipients/search
Content-Type: application/json
{
  "firstName": "John",
  "city": "Springfield"
}
```

## Project Structure
- `model/Recipient.java`: Entity definition
- `repository/RecipientRepository.java`: JPA repository
- `service/RecipientService.java`: Business logic (7-match criteria)
- `controller/RecipientController.java`: REST endpoints
- `config/MatchCriteriaConfig.java`: Loads match weights from config
- `resources/data.sql`: Preloaded sample data
- `resources/application.properties`: Configuration

## License
This project is for demonstration and testing purposes only.