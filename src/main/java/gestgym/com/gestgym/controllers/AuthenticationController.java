package gestgym.com.gestgym.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import gestgym.com.gestgym.models.AuthenticationResponse;
import gestgym.com.gestgym.models.User;
import gestgym.com.gestgym.services.Authentication.AuthenticationService;


@RestController
public class AuthenticationController {

    private final AuthenticationService authService;

    public AuthenticationController(AuthenticationService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody User request)
    {
        AuthenticationResponse userRegistred = authService.register(request);
        return ResponseEntity.ok(userRegistred);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody User user)
    {
        AuthenticationResponse userLoggingIn = authService.authenticate(user);
        return ResponseEntity.ok(userLoggingIn);
    }
}
