package com.Notification.Notification.Services;

import com.Notification.Notification.Entity.Notification;

import java.util.List;
import java.util.Map;

public interface NotificationService {


    void sendNotificationToEmail(String recipientEmail, String message);
}
