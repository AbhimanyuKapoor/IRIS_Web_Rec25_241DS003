package com.example.sports.services;

import java.util.UUID;

public interface NotificationService {

    void sendReminders();

    // Notification via Email
    void sendNotification(String to, String userId, String subject, String message);

    void runOnStartup();

    void loadUpcomingBookingsIntoCache();
}
