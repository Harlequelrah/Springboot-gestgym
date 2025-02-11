package gestgym.com.gestgym.services.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gestgym.com.gestgym.exceptions.RessourceNotFoundException;
import gestgym.com.gestgym.exceptions.RessourceUpdateException;
import gestgym.com.gestgym.models.User;
import gestgym.com.gestgym.repositories.UserRepository;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> readAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User readOneUser(Long user_id) throws RessourceNotFoundException {
        return userRepository.findById(user_id)
                .orElseThrow(() -> new RessourceNotFoundException("User with id " + user_id + " not found"));
    }

    @Override
    public User updateUser(Long user_id, User userDetails) throws RessourceNotFoundException, RessourceUpdateException {
        User user = userRepository.findById(user_id)
                .orElseThrow(() -> new RessourceNotFoundException("User with id " + user_id + " not found"));

        try {
            user.setFirstName(userDetails.getFirstName());
            user.setLastName(userDetails.getLastName());
            user.setRole(userDetails.getRole());
            user.setActive(userDetails.isActive());
            user.setUsername(userDetails.getUsername());

            return userRepository.save(user);
        } catch (Exception e) {
            throw new RessourceUpdateException("Failed to update user with id " + user_id, e);
        }
    }

    @Override
    public void deleteUser(Long user_id) throws RessourceNotFoundException {
        User user = userRepository.findById(user_id)
                .orElseThrow(() -> new RessourceNotFoundException("User with id " + user_id + " not found"));

        userRepository.delete(user);
    }

    @Override
    public void setUserState(Long user_id, boolean state) throws RessourceNotFoundException {
        User user = readOneUser(user_id);
        user.setActive(state);
        userRepository.save(user);
    }

}
