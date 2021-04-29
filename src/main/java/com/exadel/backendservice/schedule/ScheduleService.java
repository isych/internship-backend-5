package com.exadel.backendservice.schedule;

import com.exadel.backendservice.repository.DynamicInterviewLinkRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@PropertySource("classpath:application.properties")
@EnableScheduling
@RequiredArgsConstructor
public class ScheduleService {

    private final DynamicInterviewLinkRepository dynamicInterviewLinkRepository;

    @Value("${hash.lifetime.limit}")
    private String hashLifetimeLimit;

    @Scheduled(cron = "${cron.schedule.remove.old.hash}")
    private void removeOldHash() {
        log.info("Run schedule task for remove old hash");
        dynamicInterviewLinkRepository.removeOldHash(hashLifetimeLimit);
    }

}
