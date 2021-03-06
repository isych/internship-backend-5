package com.exadel.backendservice.controllers;

import com.exadel.backendservice.dto.resp.TimeslotPreferenceDto;
import com.exadel.backendservice.exception.ApiRequestException;
import com.exadel.backendservice.service.EmployeeTimeslotService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/api/timeslots")
@RequiredArgsConstructor
@Api(tags = "Контроллер для работы с таймслотами")
public class EmployeeTimeslotController {
    private final EmployeeTimeslotService employeeTimeslotService;

    /**
     * Метод для удаления таймслота
     *
     * @param id - идентификационный номер таймслота, который подлежит удалению
     */
    @ApiOperation(value = "Метод для удаления таймслота")
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Integer> deleteById(@PathVariable (value="id") UUID id){
        if(!employeeTimeslotService.deleteById(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Метод для получения списка таймслотов по id сотрудника
     *
     * @param id - идентификационный номер сотрудника, список таймслотов которого необходимо получить
     */
    @ApiOperation(value = "Метод для получения списка таймслотов по id сотрудника")
    @GetMapping("/employee/{id}")
    public ResponseEntity<?> getByEmployeeId(@PathVariable (value="id") UUID id){
        return new ResponseEntity<>(employeeTimeslotService.getListByEmployeeId(id), HttpStatus.OK);
    }

    @ApiOperation("Метод для добавления таймслота для сотрудника")
    @PostMapping("employee/{id}/add")
    public ResponseEntity<?> addToEmployee(@RequestBody @Valid TimeslotPreferenceDto timeslotPreferenceDto, @PathVariable("id") UUID id) {
        if(Objects.isNull(timeslotPreferenceDto)) {
            throw new ApiRequestException("Timeslot is null");
        }
        return new ResponseEntity<>(employeeTimeslotService.addToEmployee(timeslotPreferenceDto, id), HttpStatus.OK);
    }

}
