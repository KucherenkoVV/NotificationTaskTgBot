package com.tgbot.notificationtaskbot.timer;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import com.tgbot.notificationtaskbot.repository.NotificationTaskRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

@Component
public class NotificationTaskTimer {

    private final NotificationTaskRepository notificationTaskRepository;
    private final TelegramBot telegramBot;

    public NotificationTaskTimer(NotificationTaskRepository notificationTaskRepository, TelegramBot telegramBot) {
        this.notificationTaskRepository = notificationTaskRepository;
        this.telegramBot = telegramBot;
    }

    @Scheduled(fixedDelay = 1, timeUnit = TimeUnit.MINUTES)
    public void task(){
        notificationTaskRepository.findAllByNotificationDateTime(LocalDateTime.now()
                .truncatedTo(ChronoUnit.MINUTES))
                .forEach(notificationTask -> {telegramBot.execute(
                        new SendMessage(notificationTask.getChatId(), "Вы просили напомнить: " + notificationTask.getMessage()));
                    notificationTaskRepository.delete(notificationTask);
                });
    }
}