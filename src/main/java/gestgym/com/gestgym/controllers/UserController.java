package gestgym.com.gestgym.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gestgym.com.gestgym.exceptions.RessourceDeletionException;
import gestgym.com.gestgym.exceptions.RessourceNotFoundException;
import gestgym.com.gestgym.exceptions.RessourceUpdateException;
import gestgym.com.gestgym.models.User;
import gestgym.com.gestgym.services.user.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public ResponseEntity<List<User>> readAllUser() {
        List<User> users = userService.readAllUser();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<User> readOneUser(@PathVariable Long user_id) throws RessourceNotFoundException {
        User user = userService.readOneUser(user_id);
        return ResponseEntity.ok(user);
    }



    @PutMapping("/{user_id}")
    public ResponseEntity<User> updateUser(@PathVariable Long user_id, @RequestBody User user) throws RessourceNotFoundException, RessourceUpdateException {
        User updatedUser = userService.updateUser(user_id, user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{user_id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long user_id) throws RessourceNotFoundException, RessourceDeletionException {
        userService.deleteUser(user_id);
        return ResponseEntity.noContent().build();
    }
}
