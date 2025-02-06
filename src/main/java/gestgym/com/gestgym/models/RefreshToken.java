package gestgym.com.gestgym.models;

import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class RefreshToken {
    private String refresh_token;
    private String token_type = "bearer";

}
