package com.challenge.api.service;

import com.challenge.api.model.CreateEmployeeRequest;
import com.challenge.api.model.Employee;
import com.challenge.api.model.EmployeeImpl;
import jakarta.annotation.PostConstruct;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EmployeeService {

    private final ConcurrentHashMap<UUID, Employee> store = new ConcurrentHashMap<>();

    // Mock seed data. Would normally come from a persistence layer.
    @PostConstruct
    void seed() {
        Instant now = Instant.now();
        save(new EmployeeImpl(
                UUID.randomUUID(),
                "Sarah",
                "Johnson",
                "Sarah Johnson",
                145_000,
                36,
                "Principal Engineer",
                "sarah.johnson@example.com",
                now.minus(1200, ChronoUnit.DAYS),
                null));
        save(new EmployeeImpl(
                UUID.randomUUID(),
                "Michael",
                "Brown",
                "Michael Brown",
                132_000,
                41,
                "Staff Engineer",
                "michael.brown@example.com",
                now.minus(900, ChronoUnit.DAYS),
                null));
        save(new EmployeeImpl(
                UUID.randomUUID(),
                "Jennifer",
                "Davis",
                "Jennifer Davis",
                118_000,
                52,
                "Engineering Manager",
                "jennifer.davis@example.com",
                now.minus(2400, ChronoUnit.DAYS),
                null));
        save(new EmployeeImpl(
                UUID.randomUUID(),
                "David",
                "Wilson",
                "David Wilson",
                98_000,
                29,
                "Senior Engineer",
                "david.wilson@example.com",
                now.minus(600, ChronoUnit.DAYS),
                null));
        save(new EmployeeImpl(
                UUID.randomUUID(),
                "Emily",
                "Martinez",
                "Emily Martinez",
                72_000,
                47,
                "Software Engineer",
                "emily.martinez@example.com",
                now.minus(1800, ChronoUnit.DAYS),
                now.minus(30, ChronoUnit.DAYS)));
    }

    public List<Employee> getAll() {
        return new ArrayList<>(store.values());
    }

    public Employee getByUuid(UUID uuid) {
        Employee employee = store.get(uuid);
        if (employee == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found: " + uuid);
        }
        return employee;
    }

    public Employee create(CreateEmployeeRequest request) {
        EmployeeImpl employee = new EmployeeImpl(
                UUID.randomUUID(),
                request.firstName(),
                request.lastName(),
                request.firstName() + " " + request.lastName(),
                request.salary(),
                request.age(),
                request.jobTitle(),
                request.email(),
                request.contractHireDate(),
                null);
        save(employee);
        return employee;
    }

    private void save(Employee employee) {
        store.put(employee.getUuid(), employee);
    }
}
