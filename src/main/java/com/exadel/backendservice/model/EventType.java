package com.exadel.backendservice.model;

import com.exadel.backendservice.model.usertype.PersistentEnum;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum EventType implements PersistentEnum {
    MEETUP(1),
    INTERNSHIP(2),
    TRAINING(3);

    private final int id;

    @Override
    public int getId() {
        return id;
    }
}
