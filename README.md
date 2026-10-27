# Campus Equipment Booking Service
Java 17 / Spring Boot REST API for a CI/CD lab assessment.

## Build and test
`mvn clean test`

## Run
`mvn spring-boot:run` — port 8080.

## Endpoints
- `GET /api/equipment`
- `GET /api/equipment/{id}`
- `POST /api/bookings`
- `GET /api/bookings/{id}`
- `POST /api/bookings/{id}/cancel`
- `GET /actuator/health`

## 8. Submission Evidence

The following commands provide evidence of the implementation and verification.

### Git History and Status

```bash
git log -5 --oneline
git status
```

Verify that the booking validation fix and pipeline/deployment configuration are present in the commit history.

### Maven Test Results

```bash
mvn clean test
```

Verify that all tests pass with zero failures and zero errors.

### Docker Verification

```bash
docker images campus-equipment-booking
docker ps -a --filter name=campus-equipment-booking
curl http://localhost:8081/actuator/health
```

Verify the Docker image, container status, and application health response.

### Kubernetes Verification

```bash
kubectl get deployments
kubectl get pods -l app=campus-booking
kubectl get services
kubectl get deployment campus-booking
curl http://$(minikube ip):30080/actuator/health
```

Verify the Deployment replica status, pod health, Service configuration, and application health response.

### Jenkins Verification

Verify that the Jenkins Pipeline completes successfully, with the test and packaging stages passing. The console output should show successful Maven tests and JAR packaging.


Inventory is in memory and resets on restart. No database is required.
