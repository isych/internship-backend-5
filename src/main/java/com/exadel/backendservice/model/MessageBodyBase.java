package com.exadel.backendservice.model;

public enum MessageBodyBase {
    CANDIDATE_REGISTERED(",\nyour registration completed successfully!"),
    APPOINTED_HR_INTERVIEW(",\nhr interview has been scheduled for you.\nTerm: "),
    APPOINTED_TECH_INTERVIEW(",\ntechnical interview has been scheduled for you.\nTerm: "),
    CANDIDATE_ACCEPTED(",\nyou have successfully enrolled in the course!"),
    CANDIDATE_REJECTED(",\nExadel cannot accept you on the current course.");

    private String bodyBase;

    MessageBodyBase(String bodyBase){
        this.bodyBase = bodyBase;
    }

    public String getBodyBase() {
        return bodyBase;
    }
}
