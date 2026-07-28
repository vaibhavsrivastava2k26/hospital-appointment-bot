package dev.scorpio.ai.hospitalappointment.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class AppointmentSlot {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false)
    private Doctor doctor;
    private LocalDate appointmentDate;
    private LocalTime startTime;
    @Enumerated(EnumType.STRING)
    private SlotStatus status;
    private String patientName;

    protected AppointmentSlot() { }
    public AppointmentSlot(Doctor doctor, LocalDate appointmentDate, LocalTime startTime) {
        this.doctor = doctor; this.appointmentDate = appointmentDate; this.startTime = startTime; this.status = SlotStatus.AVAILABLE;
    }
    public Long getId() { return id; }
    public Doctor getDoctor() { return doctor; }
    public LocalDate getAppointmentDate() { return appointmentDate; }
    public LocalTime getStartTime() { return startTime; }
    public SlotStatus getStatus() { return status; }
    public String getPatientName() { return patientName; }
    public void bookFor(String patientName) { this.patientName = patientName; this.status = SlotStatus.BOOKED; }
}
