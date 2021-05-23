package com.subadvisor.registration;

import com.subadvisor.user.User;
import com.subadvisor.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class RegistrationController {

    private UserService userService;
    private ConfirmationTokenService confirmationTokenService;

    @GetMapping("/authenticate/registrate/")
    String dies () { return "asdasdsadasd";}

    @PostMapping("/authenticate/registrate/")
    String signUp (User user) {
        userService.signUpUser(user);
        return "redirect:/";
    }

    @GetMapping("/authenticate/confirm")
    String confirmMail(@RequestParam("token") String token) {

        Optional<ConfirmationToken> optionalConfirmationToken = Optional.ofNullable(confirmationTokenService.findConfirmationTokenByToken(token));

        optionalConfirmationToken.ifPresent(userService::confirmUser);

        return "/";
    }
}
