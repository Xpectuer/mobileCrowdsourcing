package edu.zjut.crowdsourcing.crdsrcing.api;


import edu.zjut.crowdsourcing.crdsrcing.api.model.UserModel;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
public interface UserService {

    String getUserName(Long id);

    UserModel addUser(UserModel user);
}
