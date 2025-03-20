package com.example.sports.services;

public interface NotificationService {

    void sendReminders();

    void sendNotification(String to, String subject, String message);

    void runOnStartup();

    void loadUpcomingBookingsIntoCache();
}
