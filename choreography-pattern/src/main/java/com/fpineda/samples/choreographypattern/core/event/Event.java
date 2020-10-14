package com.fpineda.samples.choreographypattern.core.event;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class Event {

    private final LocalDateTime dateTime;

    public Event() {
        this.dateTime = LocalDateTime.now();
    }
}
