package dev.scorpio.ai.hospitalappointment.api;

import dev.scorpio.ai.hospitalappointment.service.AppointmentService;
import dev.scorpio.ai.hospitalappointment.service.HospitalChatService;
import java.time.LocalDate;
import java.util.List;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // Replace with your frontend domain before production.
public class ChatController {
    private final HospitalChatService chat;
    private final AppointmentService appointments;
    public ChatController(HospitalChatService chat, AppointmentService appointments) { this.chat = chat; this.appointments = appointments; }

    @PostMapping("/chat")
    public ChatResponse chat(@Valid @RequestBody ChatRequest request) { return new ChatResponse(chat.reply(request.message())); }

    @GetMapping("/slots")
    public List<AvailableSlot> slots(@RequestParam String specialty, @RequestParam LocalDate date) {
        return appointments.findAvailableSlots(specialty, date);
    }
}
