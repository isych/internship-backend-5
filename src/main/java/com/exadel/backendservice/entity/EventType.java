package com.exadel.backendservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum EventType {
    MEETUP("Meet-up"), INTERNSHIP("Internship"), TRAINING("Training");
    @Getter
    private final String name;

    @Override
    public String toString() {
        return name;
    }
}
