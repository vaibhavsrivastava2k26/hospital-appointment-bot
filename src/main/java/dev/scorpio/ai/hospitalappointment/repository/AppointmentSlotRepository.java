package dev.scorpio.ai.hospitalappointment.repository;

import dev.scorpio.ai.hospitalappointment.domain.AppointmentSlot;
import dev.scorpio.ai.hospitalappointment.domain.SlotStatus;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import jakarta.persistence.LockModeType;

public interface AppointmentSlotRepository extends JpaRepository<AppointmentSlot, Long> {
    List<AppointmentSlot> findByDoctorSpecialtyIgnoreCaseAndAppointmentDateAndStatusOrderByStartTime(
            String specialty, LocalDate appointmentDate, SlotStatus status);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<AppointmentSlot> findLockedById(Long id);
}
