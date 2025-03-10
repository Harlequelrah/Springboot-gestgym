package gestgym.com.gestgym.services.authentication;

import gestgym.com.gestgym.models.AuthenticationResponse;
import gestgym.com.gestgym.models.User;

public interface IAuthenticationService {

    public AuthenticationResponse register(User request);

    public AuthenticationResponse authenticate(User request);


}
