package dev.scorpio.ai.hospitalappointment.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class HospitalChatService {
    private final ChatClient chatClient;
    private final AppointmentTools tools;

    public HospitalChatService(ChatClient.Builder builder, AppointmentTools tools) {
        this.chatClient = builder.build();
        this.tools = tools;
    }

    public String reply(String message) {
        return chatClient.prompt()
                .system("""
                        You are a hospital appointment assistant. Only help with doctor availability and appointment bookings.
                        Never diagnose, give medical advice, or handle emergencies. If the user describes an emergency, tell them to call local emergency services.
                        Use tools for all availability and booking facts; never invent a doctor, slot, or booking confirmation.
                        Ask for an exact specialty and date before finding slots. Before booking, ask for a patient's name and explicit confirmation of a slot.
                        """)
                .user(message)
                .tools(tools)
                .call()
                .content();
    }
}
