package com.exadel.backendservice.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum PreferredCandidateTime {
    ANY(10, 18),
    FROM_TEN_TO_TWELVE(10, 12),
    FROM_TWELVE_TO_TWO(12, 14),
    FROM_TWO_TO_FOUR(14, 16),
    FROM_FOUR_TO_SIX(16, 18);

    @Getter
    private final Integer startTime;
    @Getter
    private final Integer endTime;

}
