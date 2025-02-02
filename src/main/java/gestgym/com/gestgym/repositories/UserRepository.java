package gestgym.com.gestgym.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import gestgym.com.gestgym.models.User;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User>  findByUsername(String username);


}
