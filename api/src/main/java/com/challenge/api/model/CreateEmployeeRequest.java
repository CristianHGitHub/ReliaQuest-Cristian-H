package com.challenge.api.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.Instant;

public record CreateEmployeeRequest(
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotNull @Positive Integer salary,
        @NotNull @Min(16) @Max(100) Integer age,
        @NotBlank String jobTitle,
        @NotBlank @Email String email,
        @NotNull Instant contractHireDate) {}
