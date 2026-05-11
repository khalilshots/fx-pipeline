# FX Deals Importer

A Spring Boot service for importing FX deal records. Exposes a single POST endpoint that validates, deduplicates by `dealUniqueId`, and persists valid deals to PostgreSQL.

## Tech Stack

- Java 17 + Spring Boot
- Spring Data JPA + PostgreSQL
- JUnit 5 + Mockito (unit tests)
- REST Assured (integration tests)
- JaCoCo (coverage enforcement)
- Docker + Docker Compose
- k6 (performance testing)

## Running Locally

Requires Java 17+, Maven, and PostgreSQL running on `localhost:5432`.

```bash
./mvnw spring-boot:run
```

## Running with Docker

```bash
make docker-up    # start API + PostgreSQL containers
make docker-down  # stop containers
```

## Import Endpoint

```
POST http://localhost:8080/deals/import
Content-Type: application/json
```

Example request body:

```json
[
  {
    "dealUniqueId": "123",
    "fromCurrency": "USD",
    "toCurrency": "EUR",
    "dealTimestamp": "2023-01-01T12:00:00",
    "amount": 500
  }
]
```

Each deal is validated on its own. Invalid deals and duplicates are reported back with an error message, and the valid ones are still saved.

## Testing

```bash
./mvnw test          # unit + integration tests
./mvnw verify        # includes JaCoCo coverage enforcement
```

Performance test (requires a running instance):

```bash
k6 run perf/k6DealsImport.js
```
