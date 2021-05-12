package com.exadel.backendservice.model;

public enum MessageSubject {
    REGISTRATION("Registration for Exadel event"),
    INTERVIEW("Exadel has scheduled an interview for you"),
    RESULT("Exadel communicates the result of the interview to you");

    private String subject;
    private MessageSubject(String subject){
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }
}
