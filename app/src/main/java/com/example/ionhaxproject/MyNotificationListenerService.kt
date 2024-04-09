package com.example.ionhaxproject

import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification

class MyNotificationListenerService : NotificationListenerService() {

    companion object {
        lateinit var instance: MyNotificationListenerService
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    override fun onNotificationPosted(sbn: StatusBarNotification) {
        // This method is called when a new notification arrives
        // You can implement your logic here to handle the notification
        // For example, you can increase the count
        // For simplicity, let's just log the notification information
        val packageName = sbn.packageName
        val notificationId = sbn.id
        val notificationTag = sbn.tag
        val notification = sbn.notification
        val notificationText = notification?.tickerText?.toString() ?: ""
        val notificationTitle = notification?.extras?.getString("android.title") ?: ""
        val notificationContent = notification?.extras?.getString("android.text") ?: ""
        // Log the notification details
        // You can customize this according to your requirements
        // For example, increase the pullDownCount in the SignUpActivity
        // based on certain conditions related to the notification
        // (e.g., only if the app is in full-screen mode)
        // or implement your own logic.
        println("Notification: Package: $packageName, ID: $notificationId, Tag: $notificationTag, Text: $notificationText, Title: $notificationTitle, Content: $notificationContent")
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification) {
        // This method is called when a notification is removed
        // (e.g., when it is dismissed by the user)
    }
}
