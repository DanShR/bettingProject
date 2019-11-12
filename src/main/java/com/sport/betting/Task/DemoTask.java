package com.sport.betting.Task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DemoTask {

    @Scheduled(fixedRate = 5000)
    public void demoMethod() {
        System.out.println("It's demo Scheduled task");
    }
}
