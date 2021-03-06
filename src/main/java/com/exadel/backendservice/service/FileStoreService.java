package com.exadel.backendservice.service;

import org.springframework.scheduling.annotation.Async;

import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

public interface FileStoreService {
    void upload(String path,
                String fileName,
                Optional<Map<String, String>> optionalMetaData,
                InputStream inputStream);


    @Async
    byte[] download(String cvPath, String cv);

}
