package com.exadel.backendservice.model.usertype;

import com.exadel.backendservice.model.EventType;

public class EventTypeUserType extends PersistentEnumUserType<EventType> {

    @Override
    public Class<EventType> returnedClass() {
        return EventType.class;
    }

}