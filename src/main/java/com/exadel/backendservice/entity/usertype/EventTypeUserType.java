package com.exadel.backendservice.entity.usertype;

import com.exadel.backendservice.entity.EventType;

public class EventTypeUserType extends PersistentEnumUserType<EventType> {

    @Override
    public Class<EventType> returnedClass() {
        return EventType.class;
    }

}