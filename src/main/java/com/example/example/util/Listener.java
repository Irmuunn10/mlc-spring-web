package com.example.example.util;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.RequestHandledEvent;

import java.time.LocalDateTime;

@Component
public class Listener {
    @EventListener(RequestHandledEvent.class)
    public void eventListener() {
        System.out.println("Hello World!");
    }
    @Scheduled(cron = "3 * * * * *")
    public void cron() {
        System.out.println("cron: " + LocalDateTime.now().toString());
    }
    @Scheduled(fixedRate = 50000)
    public void fixedRate() {
        System.out.println("fixedRate: " + LocalDateTime.now().toString());
    }
}
