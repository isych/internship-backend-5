package com.exadel.backendservice.controllers;

import com.exadel.backendservice.dto.UserDtoWithId;
import com.exadel.backendservice.entity.User;
import com.exadel.backendservice.model.AuthRequest;
import com.exadel.backendservice.model.AuthResponse;
import com.exadel.backendservice.model.RegistrationRequest;
import com.exadel.backendservice.security.JwtProvider;
import com.exadel.backendservice.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/")
@Api(tags = "Контроллер для работы с пользователями")
public class UserController {

    private final UserService userService;
    private final JwtProvider jwtProvider;

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
        User user = userService.findByLoginAndPassword(request.getLogin(), request.getPassword());
        if (user != null) {
            String token = jwtProvider.generateToken(user.getLogin());
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
    @ApiOperation(value = "Метод для регистрации нового пользователя (варианты ролей: admin/tech/superadmin . Регистр не важен)")
    @PostMapping("register")
    public ResponseEntity<?> registerUser(@RequestBody @Valid RegistrationRequest registrationRequest) {
        return userService.saveUser(registrationRequest) ?
                new ResponseEntity<>("User created", HttpStatus.OK) :
                new ResponseEntity<>("User not created", HttpStatus.OK);
    }

    /**
     * Метод для получения всех зарегистрированных в системе пользоателей
     *
     * @return возвращает экземпляр объекта {@link List<UserDtoWithId>}
     * @author Dmitry Karachun
     */
    @ApiOperation(value = "Метод для получения всех зарегистрированных в системе пользователей")
    @GetMapping("getAllUsers")
    public List<UserDtoWithId> getAllUsers() {
        return userService.getAllUsers();
    }

    /**
     * Метод для получения всех администраторов, зарегистрированных в системе
     *
     * @return возвращает экземпляр объекта {@link List<UserDtoWithId>}
     * @author Dmitry Karachun
     */
    @ApiOperation(value = "Метод для получения всех зарегистрированных в системе администраторов")
    @GetMapping("getAllAdmins")
    public List<UserDtoWithId> getAllAdmins() {
        return userService.getAllAdmins();
    }

    /**
     * Метод для получения всех технических специалистов, зарегистрированных в системе
     *
     * @return возвращает экземпляр объекта {@link List<UserDtoWithId>}
     * @author Dmitry Karachun
     */
    @ApiOperation(value = "Метод для получения всех зарегистрированных в системе технических специалистов")
    @GetMapping("getAllTechSpec")
    public List<UserDtoWithId> getAllTechSpec() {
        return userService.getAllTechSpec();
    }

    /**
     * Метод для получения всех супер-администраторов, зарегистрированных в системе
     *
     * @return возвращает экземпляр объекта {@link List<UserDtoWithId>}
     * @author Dmitry Karachun
     */
    @ApiOperation(value = "Метод для получения всех зарегистрированных в системе супер-администраторов")
    @GetMapping("getAllSuperAdmins")
    public List<UserDtoWithId> getAllSuperAdmins() {
        return userService.getAllSuperAdmins();
    }

    /**
     * Метод для получения списка ролей
     *
     * @return возвращает экземпляр объекта {@link List<String>}
     * @author Dmitry Karachun
     */
    @ApiOperation(value = "Метод для получения списка ролей")
    @GetMapping("getAllRoles")
    public List<String> getAllRoles() {
        return userService.getListRoles();
    }


}