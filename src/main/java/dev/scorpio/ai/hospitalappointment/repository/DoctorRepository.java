package dev.scorpio.ai.hospitalappointment.repository;

import dev.scorpio.ai.hospitalappointment.domain.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    List<Doctor> findBySpecialty(String specialty);
}
