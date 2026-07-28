package dev.scorpio.ai.hospitalappointment.api;

import java.time.LocalDate;
import java.time.LocalTime;

public record AvailableSlot(Long slotId, String doctor, String specialty, LocalDate date, LocalTime time) { }
