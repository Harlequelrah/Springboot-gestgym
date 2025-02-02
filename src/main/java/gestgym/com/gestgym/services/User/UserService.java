package gestgym.com.gestgym.services.User;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
    public User readOneUser(Long user_id) {
        if (isValidId(user_id)) {
            return userRepository.findById(user_id).orElse(null);
        } else {
            throw new UnsupportedOperationException("Error occured while reading user with id "+user_id);
        }
    }



    @Override
    public User updateUser(Long user_id, User user) {
        if (isValidId(user_id)) {
            return userRepository.save(user);
        } else {
            throw new UnsupportedOperationException("Error occurred while updating User with id : " + user_id);
        }

    }

    @Override
    public boolean isValidId(Long user_id) {
        if (user_id != null && userRepository.existsById(user_id)) {
            return true;
        } else {
            throw new UnsupportedOperationException("User with id " + user_id + " not found");
        }
    }

    @Override
    public void setUserState(Long user_id,boolean state) {
        if (isValidId(user_id)) {
            User user = readOneUser(user_id);
            user.setActive(state);
            userRepository.save(user);
        } else {
            throw new UnsupportedOperationException("Error occured while locking user with id"+user_id);
        }
    }



}
