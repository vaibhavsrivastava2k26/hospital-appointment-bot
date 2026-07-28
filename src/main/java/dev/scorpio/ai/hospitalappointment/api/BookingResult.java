package dev.scorpio.ai.hospitalappointment.api;

public record BookingResult(boolean confirmed, String message, Long slotId) { }
