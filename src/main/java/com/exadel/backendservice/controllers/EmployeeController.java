package com.exadel.backendservice.controllers;

import com.exadel.backendservice.dto.resp.InterviewersByRoleDto;
import com.exadel.backendservice.dto.resp.RoleRespDto;
import com.exadel.backendservice.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
@Api(tags = "Контроллер для работы с интервюерами/админа")
public class EmployeeController {
    private final EmployeeService employeeService;


    @ApiOperation(value = "Метод для получения списка ролей только интервьюеров")
    @GetMapping("/interviewers/roles")
    public ResponseEntity<List<RoleRespDto>> getInterviewersRoles() {
        return new ResponseEntity<>(employeeService.getInterviewersRoles(), HttpStatus.OK);
    }

    @ApiOperation(value = "Метод для получения списка ролей только интервьюеров")
    @GetMapping("/interviewers/list/")
    public ResponseEntity<List<InterviewersByRoleDto>> getInterviewersByRole() {
        return new ResponseEntity<>(employeeService.getInterviewersForCandidate(), HttpStatus.OK);
    }


}
