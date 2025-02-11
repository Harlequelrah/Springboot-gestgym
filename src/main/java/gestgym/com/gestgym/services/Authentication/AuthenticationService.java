package gestgym.com.gestgym.services.authentication;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import gestgym.com.gestgym.models.AuthenticationResponse;
import gestgym.com.gestgym.models.User;
import gestgym.com.gestgym.repositories.UserRepository;
import gestgym.com.gestgym.services.jwt.JwtService;

@Service
public class AuthenticationService implements IAuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder,
            JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;

        this.authenticationManager = authenticationManager;
    }

    @Override
    public AuthenticationResponse register(User request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setLastName(request.getLastName());
        user.setFirstName(request.getFirstName());
        user.setActive(request.isActive());
        user.setRole(request.getRole());
        user = userRepository.save(user);
        String access_token = jwtService.generateAccessToken(user);
        String refresh_token = jwtService.generateRefreshToken(user);
        return new AuthenticationResponse(access_token, refresh_token);
    }


    @Override
    public AuthenticationResponse authenticate(User request) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow();

        String access_token = jwtService.generateAccessToken(user);
        String refresh_token = jwtService.generateRefreshToken(user);
        return new AuthenticationResponse(access_token, refresh_token);
    }

}
