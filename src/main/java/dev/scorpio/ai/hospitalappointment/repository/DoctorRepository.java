package dev.scorpio.ai.hospitalappointment.repository;

import dev.scorpio.ai.hospitalappointment.domain.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> { }
