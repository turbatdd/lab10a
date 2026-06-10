# PatientsAppointmentsBookingApp

A Spring Boot Command-Line Application (PAMS — Patient Appointments Management System) that manages patient records and outputs them as formatted JSON sorted by age.

---

## Prerequisites

| Requirement | Minimum | Recommended |
|---|---|---|
| Java JDK | 17 | 25+ |
| Maven | 3.6+ | 3.9+ |
| IDE | Any | IntelliJ IDEA / VS Code |

---

## Project Structure

```
src/main/java/com/example/pams/
├── PAMSApp.java                  # Entry point (CommandLineRunner)
├── model/
│   └── Patient.java              # Patient data model
├── service/
│   └── PatientService.java       # Business logic & JSON serialization
└── repository/
    └── PatientRepository.java    # In-memory patient store

src/main/resources/
├── application.properties                # Default (activates development profile)
├── application-development.properties   # Dev profile config
└── application-production.properties    # Prod profile config
```

---

## Patient Model Fields

| Field | Type | Notes |
|---|---|---|
| `patientId` | int | Unique identifier |
| `firstName` | String | |
| `lastName` | String | |
| `contactPhoneNumber` | String | Optional |
| `email` | String | Optional |
| `mailingAddress` | String | Optional |
| `dateOfBirth` | LocalDate | Format: `yyyy-MM-dd` |
| `age` | int | Computed from DOB — included in JSON output |

---

## Running the App

### Development profile (default)
```bash
mvn spring-boot:run
```

### Production profile
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=production
```

### Build and run as JAR
```bash
mvn clean package
java -jar target/PatientsAppointmentsBookingApp-1.0.0.jar
```

### Run as JAR with production profile
```bash
java -jar target/PatientsAppointmentsBookingApp-1.0.0.jar --spring.profiles.active=production
```

---

## Spring Profiles

| Profile | Log Level | ANSI Colours | App Name |
|---|---|---|---|
| `development` | DEBUG | Enabled | `PatientsAppointmentsBookingApp [DEV]` |
| `production` | WARN | Disabled | `PatientsAppointmentsBookingApp [PROD]` |

---

## Sample Output

Patients are printed to the console as a JSON array, sorted by age descending (oldest first):

```json
[ {
  "patientId" : 5,
  "firstName" : "Mary",
  "lastName" : "Washington",
  "contactPhoneNumber" : null,
  "email" : null,
  "mailingAddress" : "30 W Burlington",
  "dateOfBirth" : "1932-05-31",
  "age" : 94
}, {
  "patientId" : 2,
  "firstName" : "Ana",
  "lastName" : "Smith",
  "contactPhoneNumber" : null,
  "email" : "asmith@te.edu",
  "mailingAddress" : null,
  "dateOfBirth" : "1948-12-05",
  "age" : 77
} ]
```

---

## Running Tests

```bash
mvn test
```
