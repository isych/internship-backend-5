package com.exadel.backendservice.controllers;

import com.exadel.backendservice.service.EmployeeTimeslotService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/timeslots")
@RequiredArgsConstructor
@Api(tags = "Контроллер для работы с таймслотами")
public class EmployeeTimeslotController {

    private final EmployeeTimeslotService employeeTimeslotService;

    /**
     * Метод для удаления таймслота
     *
     * @param id - идентификационный номер таймслота, который подлежит удалению
     */
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Integer> deleteById(@PathVariable (value="id") Integer id){
        if(!employeeTimeslotService.deleteById(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
