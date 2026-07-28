package dev.scorpio.ai.hospitalappointment.service;

import dev.scorpio.ai.hospitalappointment.api.AvailableSlot;
import dev.scorpio.ai.hospitalappointment.api.BookingResult;
import dev.scorpio.ai.hospitalappointment.domain.AppointmentSlot;
import dev.scorpio.ai.hospitalappointment.domain.SlotStatus;
import dev.scorpio.ai.hospitalappointment.repository.AppointmentSlotRepository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AppointmentService {
    private final AppointmentSlotRepository slots;
    public AppointmentService(AppointmentSlotRepository slots) { this.slots = slots; }

    @Transactional(readOnly = true)
    public List<AvailableSlot> findAvailableSlots(String specialty, LocalDate date) {
        return slots.findByDoctorSpecialtyIgnoreCaseAndAppointmentDateAndStatusOrderByStartTime(specialty, date, SlotStatus.AVAILABLE)
                .stream().map(s -> new AvailableSlot(s.getId(), s.getDoctor().getName(), s.getDoctor().getSpecialty(), s.getAppointmentDate(), s.getStartTime())).toList();
    }

    @Transactional
    public BookingResult book(Long slotId, String patientName) {
        AppointmentSlot slot = slots.findLockedById(slotId).orElse(null);
        if (slot == null) return new BookingResult(false, "That appointment slot does not exist.", slotId);
        if (slot.getStatus() != SlotStatus.AVAILABLE) return new BookingResult(false, "That appointment slot is no longer available.", slotId);
        slot.bookFor(patientName);
        return new BookingResult(true, "Appointment confirmed with " + slot.getDoctor().getName() + " on " + slot.getAppointmentDate() + " at " + slot.getStartTime() + ".", slotId);
    }
}
