package com.tgbot.notificationtaskbot.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name ="notification_task")
@Getter
@Setter
public class NotificationTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "message",nullable = false)
    private String message;

    @Column(name = "chat_id", nullable = false)
    private long chatId;

    @Column(name = "notification_date_time", nullable = false)
    private LocalDateTime notificationDateTime;

    public NotificationTask() {
    }

    public NotificationTask(long id, String message, long chatId, LocalDateTime notificationDateTime) {
        this.id = id;
        this.message = message;
        this.chatId = chatId;
        this.notificationDateTime = notificationDateTime;
    }

}
