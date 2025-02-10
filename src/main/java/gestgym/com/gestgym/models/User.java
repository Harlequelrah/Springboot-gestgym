package gestgym.com.gestgym.models;


import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name="user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    @NotBlank(message="Firstname is mandatory")
    private String firstName;

    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    @NotBlank(message = "Lastname is mandatory")
    private String lastName;

    private boolean isActive;

    @Column(unique = true)
    @Size(min = 2, max = 50, message = "Username must be between 2 and 50 characters")
    @NotBlank(message = "Username is mandatory")
    private String username;

    // @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    // private List<Token> tokens;


    @NotBlank(message="Password is mandatory")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&]).{8,}$", message = "Password must contain at least one lowercase letter, one uppercase letter, one digit, one special character , and be at least 8 characters long.")
    // @Size(min = 8, max = 50, message = "Password must be between 8 and 50 characters")
    private String password;

    @Enumerated(value = EnumType.STRING)
    @NotNull(message="Role is mandatory")
    private Role role;

    @Override
    public boolean isEnabled()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;

    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));

    }
}
