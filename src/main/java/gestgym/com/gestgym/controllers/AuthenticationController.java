package gestgym.com.gestgym.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import gestgym.com.gestgym.exceptions.RessourceNotFoundException;
import gestgym.com.gestgym.models.AccessToken;
import gestgym.com.gestgym.models.AuthenticationResponse;
import gestgym.com.gestgym.models.User;
import gestgym.com.gestgym.repositories.UserRepository;
import gestgym.com.gestgym.services.Authentication.AuthenticationService;
import gestgym.com.gestgym.services.Jwt.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@RestController
public class AuthenticationController {

    @Autowired
    private UserRepository userRepository;
    private final AuthenticationService authService;
    private final JwtService jwtService;

    public AuthenticationController(AuthenticationService authService, JwtService jwtService) {
        this.jwtService = jwtService;
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

    @PostMapping("refresh-token")
    public ResponseEntity<AccessToken> refreshToken (
    HttpServletRequest request,
    HttpServletResponse response
    ) throws RessourceNotFoundException
    {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return new ResponseEntity<AccessToken>(HttpStatus.UNAUTHORIZED);
        }
        String token = authHeader.substring(7);

        String username = jwtService.extractUsername(token);
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new RessourceNotFoundException("No User Found"));
        if (jwtService.isValid(token, user))
        {
            String access_token = jwtService.generateAccessToken((user));
            AccessToken accessToken = new AccessToken(access_token);
            return ResponseEntity.ok(accessToken);
        }
        return new ResponseEntity<AccessToken>(HttpStatus.UNAUTHORIZED);


    }

}
