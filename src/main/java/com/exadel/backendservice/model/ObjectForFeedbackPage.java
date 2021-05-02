package com.exadel.backendservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ObjectForFeedbackPage {
    private String candidateName;
    private String candidateEmail;
    private String candidatePhone;
    private String candidateCountry;
    private String candidateCity;
    private String candidateTechnology;
    private String interviewDate;
    private String interviewTime;
    private String interviewerName;
}
