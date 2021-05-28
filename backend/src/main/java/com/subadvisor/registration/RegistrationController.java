package com.subadvisor.registration;

import com.subadvisor.registration.dto.RegistrationRequest;
import com.subadvisor.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegistrationController {

    @Autowired
    private UserService userService;
    @Autowired
    private ConfirmationTokenService confirmationTokenService;

    @GetMapping("/authenticate/registrate/")
    String dies () { return "asdasdsadasd";}

    @PostMapping("/authenticate/registrate/")
    public HttpStatus signUp (@RequestBody RegistrationRequest registrationRequest) {
        userService.signUpUser(registrationRequest);
        return HttpStatus.OK;
    }
}
