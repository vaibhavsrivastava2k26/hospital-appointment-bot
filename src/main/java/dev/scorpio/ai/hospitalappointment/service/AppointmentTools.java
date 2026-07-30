package dev.scorpio.ai.hospitalappointment.service;

import dev.scorpio.ai.hospitalappointment.api.AvailableSlot;
import dev.scorpio.ai.hospitalappointment.api.BookingResult;
import java.time.LocalDate;
import java.util.List;

import dev.scorpio.ai.hospitalappointment.domain.Doctor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

@Component
public class AppointmentTools {
    private final AppointmentService appointments;
    private final DoctorService doctorService;

    public AppointmentTools(AppointmentService appointments, DoctorService doctorService) {
        this.appointments = appointments;
        this.doctorService = doctorService;
    }

    @Tool(description = "Find available appointment slots for an exact medical specialty and date in ISO format (YYYY-MM-DD).")
    public List<AvailableSlot> findAvailableSlots(String specialty, LocalDate date) {
        return appointments.findAvailableSlots(specialty, date);
    }

    @Tool(description = "Book an available appointment slot by slot ID. Ask the user for their name before using this tool. Only use after the user explicitly confirms the slot.")
    public BookingResult bookAppointment(Long slotId, String patientName) {
        return appointments.book(slotId, patientName);
    }

    @Tool(description = "Find list of doctors of a particular specialty")
    public List<Doctor> findDoctorsOfSpecificSpecialty(String specialty){
        return doctorService.findDoctorsOfASpecialty(specialty);
    }
}
