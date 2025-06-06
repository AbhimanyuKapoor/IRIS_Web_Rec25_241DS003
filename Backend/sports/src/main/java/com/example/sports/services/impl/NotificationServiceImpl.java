package com.example.sports.services.impl;

import com.example.sports.domain.entities.InfrastructureRequest;
import com.example.sports.domain.entities.RequestStatus;
import com.example.sports.mappers.InfrastructureRequestMapper;
import com.example.sports.repositories.InfrastructureRequestRepository;
import com.example.sports.services.InfrastructureRequestService;
import com.example.sports.services.NotificationService;
import jakarta.annotation.PostConstruct;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class NotificationServiceImpl implements NotificationService {

    private List<InfrastructureRequest> reminderCache = new ArrayList<>();
    private final InfrastructureRequestRepository infrastructureRequestRepository;
    private final InfrastructureRequestService infrastructureRequestService;
    private final JavaMailSender javaMailSender;
    private final SimpMessagingTemplate messagingTemplate;

    public NotificationServiceImpl(InfrastructureRequestRepository infrastructureRequestRepository, InfrastructureRequestService infrastructureRequestService, JavaMailSender javaMailSender, SimpMessagingTemplate messagingTemplate) {
        this.infrastructureRequestRepository = infrastructureRequestRepository;
        this.infrastructureRequestService = infrastructureRequestService;
        this.javaMailSender = javaMailSender;
        this.messagingTemplate = messagingTemplate;
    }

    // To prevent repetitive Querying of Database
    public void loadUpcomingBookingsIntoCache() {
        reminderCache = infrastructureRequestRepository.findByDateAndStatus(LocalDate.now(), RequestStatus.APPROVED);
    }

    @Override
    @Scheduled(fixedRate = 60000)
    public void sendReminders() {

        for(InfrastructureRequest request: reminderCache)
            if (!request.getReminderSent())
                if(!LocalTime.now().plusMinutes(30).isBefore(request.getRequestedFor()) && !LocalTime.now().isAfter(request.getRequestedFor())) {
                    sendNotification(request.getUser().getEmail(),
                            request.getUser().getId().toString(),
                            "Your Have an Upcoming Booking",
                            "Your have a Booking for: " + request.getInfrastructure().getName() + "\nOn: " + request.getRequestedFor());
                    request.setReminderSent(true);
                    infrastructureRequestService.updateInfrastructureRequest(request.getId(), InfrastructureRequestMapper.INSTANCE.toDto(request), false);
                }
    }

    @Override
    public void sendNotification(String to, String userId, String subject, String message) {

        // Live notification
        messagingTemplate.convertAndSendToUser(
                userId,
                "/queue/notifications",
                message
        );

        // Email notification
        SimpleMailMessage simpleMail = new SimpleMailMessage();
        simpleMail.setTo(to);
        simpleMail.setSubject(subject);
        simpleMail.setText(message);
        simpleMail.setFrom("passmanager50@gmail.com");

        javaMailSender.send(simpleMail);
    }

    @Override
    @PostConstruct // To run on startup of Spring Boot Application
    public void runOnStartup() {
        loadUpcomingBookingsIntoCache();
        sendReminders();
    }
}
