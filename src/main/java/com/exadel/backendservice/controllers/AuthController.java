package com.exadel.backendservice.controllers;

import com.exadel.backendservice.dto.UserDtoWithId;
import com.exadel.backendservice.entity.UserEntity;
import com.exadel.backendservice.model.AuthRequest;
import com.exadel.backendservice.model.AuthResponse;
import com.exadel.backendservice.model.RegistrationRequest;
import com.exadel.backendservice.security.JwtProvider;
import com.exadel.backendservice.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users/")
@Api(tags = "Контроллер для работы с пользователями")
public class AuthController {

    private final UserService userService;
    private final JwtProvider jwtProvider;

    @Autowired
    public AuthController(UserService userService, JwtProvider jwtProvider) {
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }

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
        UserEntity userEntity = userService.findByLoginAndPassword(request.getLogin(), request.getPassword());
        if(userEntity != null) {
            String token = jwtProvider.generateToken(userEntity.getLogin());
            return new AuthResponse(token);
        }
        return new AuthResponse("null");
    }

    /**
     * Метод решистрации новых пользователей (администратовров/техничекских специалистов)
     *
     * @param registrationRequest - объект, передаваемый из формы аутентификации пользователей
     * @return возвращает экземпляр объекта {@link ResponseEntity}
     * @author Dmitry Karachun
     */
    @ApiOperation(value = "Метод для регистрации нового пользователя (требуются права администратора)")
    @PostMapping("register")
    public ResponseEntity<?> registerUser(@RequestBody @Valid RegistrationRequest registrationRequest) {
        return userService.saveUser(registrationRequest)?
                  new ResponseEntity<>("User created", HttpStatus.OK) :
                  new ResponseEntity<>("User not created", HttpStatus.OK);
    }

    /**
     * Метод для получения всех зарегистрированных в системе пользоателей
     *
     * @return возвращает экземпляр объекта {@link List<UserDtoWithId>}
     * @author Dmitry Karachun
     */
    @ApiOperation(value = "Метод для получения всех зарегистрированных в системе пользоателей")
    @GetMapping("getAllUsers")
    public List<UserDtoWithId> getAllUsers(){
        return userService.getAllUsers();
    }

}