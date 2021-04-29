package com.exadel.backendservice.mapper.interview;

import com.exadel.backendservice.dto.resp.InterviewRespDto;
import com.exadel.backendservice.entity.Candidate;
import com.exadel.backendservice.entity.Employee;
import com.exadel.backendservice.entity.Interview;
import com.exadel.backendservice.mapper.AbstractMapper;
import com.exadel.backendservice.repository.CandidateRepository;
import com.exadel.backendservice.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Component
public class InterviewRespMapper extends AbstractMapper<Interview, InterviewRespDto> {

    private final EmployeeRepository employeeRepository;
    private final CandidateRepository candidateRepository;

    @Autowired
    public InterviewRespMapper(EmployeeRepository employeeRepository, CandidateRepository candidateRepository) {
        super(Interview.class, InterviewRespDto.class);
        this.employeeRepository = employeeRepository;
        this.candidateRepository = candidateRepository;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Interview.class, InterviewRespDto.class)
                .addMappings(m -> m.skip(InterviewRespDto::setEmployee))
                .addMappings(m -> m.skip(InterviewRespDto::setCandidate))
                .setPostConverter(toDtoConverter());
    }

    @Override
    public void mapSpecificFields(Interview source, InterviewRespDto destination) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(source.getEmployee().getId());
        Employee employee = new Employee();
        if(optionalEmployee.isPresent()) {
            employee = optionalEmployee.get();
        }

        Optional<Candidate> optionalCandidate = candidateRepository.findById(source.getCandidate().getId());
        Candidate candidate = new Candidate();
        if(optionalCandidate.isPresent()) {
            candidate = optionalCandidate.get();
        }

        destination.setEmployee(employee.getFullName());
        destination.setCandidate(candidate.getFullName());
    }
}
