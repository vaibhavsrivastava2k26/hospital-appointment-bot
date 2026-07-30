package dev.scorpio.ai.hospitalappointment.service;

import dev.scorpio.ai.hospitalappointment.domain.Doctor;
import dev.scorpio.ai.hospitalappointment.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public List<Doctor> findDoctorsOfASpecialty(String specialty){
        return doctorRepository.findBySpecialty(specialty);
    }

}
