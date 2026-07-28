package dev.scorpio.ai.hospitalappointment.service;

import dev.scorpio.ai.hospitalappointment.api.AvailableSlot;
import dev.scorpio.ai.hospitalappointment.api.BookingResult;
import java.time.LocalDate;
import java.util.List;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

@Component
public class AppointmentTools {
    private final AppointmentService appointments;
    public AppointmentTools(AppointmentService appointments) { this.appointments = appointments; }

    @Tool(description = "Find available appointment slots for an exact medical specialty and date in ISO format (YYYY-MM-DD).")
    public List<AvailableSlot> findAvailableSlots(String specialty, LocalDate date) {
        return appointments.findAvailableSlots(specialty, date);
    }

    @Tool(description = "Book an available appointment slot by slot ID. Ask the user for their name before using this tool. Only use after the user explicitly confirms the slot.")
    public BookingResult bookAppointment(Long slotId, String patientName) {
        return appointments.book(slotId, patientName);
    }
}
