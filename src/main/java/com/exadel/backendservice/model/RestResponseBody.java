package com.exadel.backendservice.model;

import lombok.Data;

@Data
public class RestResponseBody {
    private String msg;
    private Object result;
}
