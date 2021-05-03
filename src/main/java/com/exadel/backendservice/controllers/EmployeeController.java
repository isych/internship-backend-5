package com.exadel.backendservice.controllers;

import com.exadel.backendservice.dto.resp.EmployeeDto;
import com.exadel.backendservice.dto.resp.InterviewersByRoleDto;
import com.exadel.backendservice.dto.resp.RoleRespDto;
import com.exadel.backendservice.entity.Employee;
import com.exadel.backendservice.model.AuthRequest;
import com.exadel.backendservice.model.AuthResponse;
import com.exadel.backendservice.security.JwtProvider;
import com.exadel.backendservice.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
@Api(tags = "Контроллер для работы с пользователями")
public class EmployeeController {

    private final JwtProvider jwtProvider;
    private final EmployeeService employeeService;

    /**
     * Метод получения JWT-токена для пользователя
     *
     * @param request - объект, передаваемый из формы аутентификации пользователей
     * @return возвращает экземпляр объекта {@link AuthResponse}
     * @author Dmitry Karachun
     */
    @ApiOperation(value = "Метод для авторизации и получения JWT-токена")
    @PostMapping("auth")
    public AuthResponse auth(@RequestBody AuthRequest request) {
        Employee employee = employeeService.findByEmailAndPassword(request.getEmail(), request.getPassword());
        if (employee != null) {
            String token = jwtProvider.generateToken(employee.getEmail(), employee.getFullName(), employee.getRole().getName());
            return new AuthResponse(token, employee.getRole().getName(), employee.getFullName());
        }
        return new AuthResponse(null, null, null);
    }

    /**
     * Метод для получения списка ролей
     *
     * @return возвращает экземпляр объекта {@link List<String>}
     * @author Dmitry Karachun
     */
    @ApiOperation(value = "Метод для получения списка ролей")
    @GetMapping("/roles")
    public List<String> getAllRoles() {
        return employeeService.getListRoles();
    }

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

    @ApiOperation(value = "Метод для удаления пользователя из системы по его id")
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> deleteEmployee(@PathVariable UUID id){
        return new ResponseEntity<>(employeeService.deleteEmployee(id) ? "Employee removed" : "Employee not removed", HttpStatus.OK);
    }

    @ApiOperation(value = "Метод для добавления пользователя в систему")
    @PostMapping("/add")
    public ResponseEntity<?> saveEmployee(@RequestBody EmployeeDto employeeDto){
        Employee employee = employeeService.saveEmployee(employeeDto);
        return new ResponseEntity<>(employee != null ? "Employee created" : "Employee not created", HttpStatus.OK);
    }

}
