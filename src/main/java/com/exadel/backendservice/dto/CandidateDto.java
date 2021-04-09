package com.exadel.backendservice.dto;

import lombok.Data;

@Data
public class CandidateDto {
        String fullName;
        String email;
        String phone;
        String skype;
        String cv;
        Integer city;
        Integer eventStack;
}
