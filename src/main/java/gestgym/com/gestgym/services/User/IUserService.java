package gestgym.com.gestgym.services.User;

import java.util.List;

import gestgym.com.gestgym.models.User;

public interface IUserService {

    public List<User> readAllUser();

    public User readOneUser(Long user_id);


    public User updateUser(Long user_id, User user);

    public boolean isValidId(Long user_id);

    public void setUserState(Long user_id,boolean state);




}
