package gestgym.com.gestgym.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Tokens {
    private AccessToken AccessToken;
    private RefreshToken RefreshToken;
}
