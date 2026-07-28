package dev.scorpio.ai.hospitalappointment.config;

import dev.scorpio.ai.hospitalappointment.domain.AppointmentSlot;
import dev.scorpio.ai.hospitalappointment.domain.Doctor;
import dev.scorpio.ai.hospitalappointment.repository.AppointmentSlotRepository;
import dev.scorpio.ai.hospitalappointment.repository.DoctorRepository;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.stream.Stream;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DemoDataConfiguration {
    @Bean
    CommandLineRunner seedDemoData(DoctorRepository doctors, AppointmentSlotRepository slots) {
        return args -> {
            if (doctors.count() > 0) return;
            Doctor cardiologist = doctors.save(new Doctor("Dr. Anika Rao", "Cardiology"));
            Doctor dermatologist = doctors.save(new Doctor("Dr. Vikram Singh", "Dermatology"));
            Doctor generalPhysician = doctors.save(new Doctor("Dr. Nisha Patel", "General Medicine"));
            LocalDate tomorrow = LocalDate.now().plusDays(1);
            Stream.of(
                    new AppointmentSlot(cardiologist, tomorrow, LocalTime.of(10, 0)),
                    new AppointmentSlot(cardiologist, tomorrow, LocalTime.of(15, 30)),
                    new AppointmentSlot(dermatologist, tomorrow, LocalTime.of(9, 30)),
                    new AppointmentSlot(dermatologist, tomorrow, LocalTime.of(16, 0)),
                    new AppointmentSlot(generalPhysician, tomorrow, LocalTime.of(11, 0))
            ).forEach(slots::save);
        };
    }
}
