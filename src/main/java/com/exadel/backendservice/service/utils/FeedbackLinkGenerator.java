package com.exadel.backendservice.service.utils;

import com.exadel.backendservice.entity.Candidate;
import com.exadel.backendservice.entity.DynamicInterviewLink;
import com.exadel.backendservice.entity.Interview;
import com.exadel.backendservice.exception.ApiResponseException;
import com.exadel.backendservice.model.InterviewProcess;
import com.exadel.backendservice.repository.DynamicInterviewLinkRepository;
import com.exadel.backendservice.repository.InterviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class FeedbackLinkGenerator {

    private final DynamicInterviewLinkRepository dynamicInterviewLinkRepository;
    private final MailSender mailSender;
    private final InterviewRepository interviewRepository;

    private String hash = null;

    public String generateLink(HttpServletRequest request) {
        String link =  request.getScheme() + "://" + request.getServerName() + ":" +
                request.getServerPort() + "/api/interviews/feedback/" +
                generateKey();
        return "<a href=" + link + ">CLICK ME</a>";
    }

    private String generateKey() {
        String key = gener();
        Pattern pattern = Pattern.compile("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{7,13}");
        boolean res = false;
        while (!res) {
            Matcher matcher = pattern.matcher(key);
            if (matcher.find()) res = true;
            else key = gener();
        }
        hash = key;
        return key;
    }

    private static String gener() {
        StringBuilder key = new StringBuilder();
        Random r = new Random();
        int cntchars = 25 + r.nextInt(1);
        for (int i = 0; i < cntchars; ++i) {
            char next = 0;
            int range = 10;
            switch (r.nextInt(3)) {
                case 0: {
                    next = '0';
                    range = 10;
                }
                break;
                case 1: {
                    next = 'a';
                    range = 26;
                }
                break;
                case 2: {
                    next = 'A';
                    range = 26;
                }
                break;
            }
            key.append((char) ((r.nextInt(range)) + next));
        }
        return key.toString();
    }

//    public void sendMessageWithLinkForFeedback(Candidate candidate, InterviewProcess awaitingHr, HttpServletRequest request) {
//        String link = generateLink(request);
//        String message = new StringBuilder()
//                .append("Please write feedback on the results of the interview with the candidate is ")
//                .append(candidate.getFullName()).append(".<br>")
//                .append("To continue, follow the link: ")
//                .append(link)
//                .toString();
//        try {
//            Interview interview = getInterview(awaitingHr, candidate);
//            if (interview != null) {
//                saveHashToDb(hash, interview.getId());
//
//
//
//                mailSender.sendLetterWithLink(interview.getEmployee().getEmail(), "Feedback on the results of the interview", message);
//            }
//        } catch (Exception ex) {
//            throw new ApiResponseException("Internal error: mail can't be send.");
//        }
//    }
//
//    private Interview getInterview(InterviewProcess awaitingHr, Candidate candidate) {
//        List<Interview> interviewList = interviewRepository.findAllByCandidate_Id(candidate.getId());
//        Interview interview = null;
//        try {
//            if (awaitingHr.equals(InterviewProcess.AWAITING_TS)) {
//                interview = interviewList.stream()
//                        .filter(elem -> elem.getEmployee().getRole().getName().equals("ROLE_ADMIN"))
//                        .findFirst()
//                        .get();
//            }
//            if (awaitingHr.equals(InterviewProcess.WAITING_DECISION)) {
//                interview = interviewList.stream()
//                        .filter(elem -> elem.getEmployee().getRole().getName().equals("ROLE_TECH"))
//                        .findFirst()
//                        .get();
//            }
//        }catch (Exception e){
//            return null;
//        }
//        return interview;
//    }

//    private void saveHashToDb(String key, Integer idInterview) {
//        DynamicInterviewLink link = new DynamicInterviewLink();
//        link.setCode(key);
//        link.setCreatedTime(LocalDateTime.now());
//        link.setInterviewId(idInterview);
//        dynamicInterviewLinkRepository.save(link);
//    }

    public void removeHashFromDb(String key) {
        dynamicInterviewLinkRepository.delete(dynamicInterviewLinkRepository.findByCode(key));
    }
}
