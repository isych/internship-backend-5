package com.exadel.backendservice.service.utils;

import com.exadel.backendservice.model.RestResponseBody;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RestAnswer {
    public ResponseEntity<?> doResultAjax(Object model) {
        RestResponseBody result = new RestResponseBody();
        if (model == null)  result.setMsg("no result");
        else result.setMsg("success");
        result.setResult(model);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
        headers.add(HttpHeaders.CACHE_CONTROL, "no-cache");
        return ResponseEntity.ok().headers(headers).body(result);
    }
}
