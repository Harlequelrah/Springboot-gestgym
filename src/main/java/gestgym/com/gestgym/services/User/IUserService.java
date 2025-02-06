package gestgym.com.gestgym.services.User;

import java.util.List;

import gestgym.com.gestgym.exceptions.RessourceNotFoundException;
import gestgym.com.gestgym.exceptions.RessourceUpdateException;
import gestgym.com.gestgym.models.User;

public interface IUserService {

    public List<User> readAllUser();

    public User readOneUser(Long user_id) throws RessourceNotFoundException;


    public User updateUser(Long user_id, User user) throws RessourceNotFoundException, RessourceUpdateException;

    public void deleteUser(Long user_id) throws RessourceNotFoundException;


    public void setUserState(Long user_id,boolean state) throws RessourceNotFoundException;




}
