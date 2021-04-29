package com.exadel.backendservice.mapper.interview;

import com.exadel.backendservice.dto.req.CreateInterviewDto;
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
public class CreateInterviewMapper extends AbstractMapper<Interview, CreateInterviewDto> {

    private final CandidateRepository candidateRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public CreateInterviewMapper(CandidateRepository candidateRepository, EmployeeRepository employeeRepository) {
        super(Interview.class, CreateInterviewDto.class);
        this.candidateRepository = candidateRepository;
        this.employeeRepository = employeeRepository;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(CreateInterviewDto.class, Interview.class)
                .addMappings(m -> m.skip(Interview::setCandidate))
                .addMappings(m -> m.skip(Interview::setEmployee))
                .setPostConverter(toEntityConverter());
    }

    @Override
    public void mapSpecificFields(CreateInterviewDto source, Interview destination) {
        Optional<Candidate> optionalCandidate = candidateRepository.findById(source.getCandidate());
        Candidate candidate = new Candidate();
        if(optionalCandidate.isPresent()) {
            candidate = optionalCandidate.get();
        }

        Optional<Employee> optionalEmployee = employeeRepository.findById(source.getEmployee());
        Employee employee = new Employee();
        if(optionalEmployee.isPresent()) {
            employee = optionalEmployee.get();
        }

        destination.setCandidate(candidate);
        destination.setEmployee(employee);
    }
}
