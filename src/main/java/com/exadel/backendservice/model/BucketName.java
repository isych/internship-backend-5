package com.exadel.backendservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum BucketName {
    CV("exadel-cv-storage"), EVENT_PIC("exadel-event-pic-storage");

    @Getter
    private final String bucketName;
}
