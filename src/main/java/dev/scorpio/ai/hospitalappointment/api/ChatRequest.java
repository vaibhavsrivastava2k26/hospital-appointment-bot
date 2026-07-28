package dev.scorpio.ai.hospitalappointment.api;

import jakarta.validation.constraints.NotBlank;
public record ChatRequest(@NotBlank String message) { }
