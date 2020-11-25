package edu.zjut.mobilr.authservice.service;



import edu.zjut.mobilr.authservice.model.User;

import edu.zjut.mobilr.authservice.model.UserRole;
import edu.zjut.mobilr.authservice.repo.UserRepository;
import edu.zjut.mobilr.authservice.repo.UserRoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.RollbackException;
import java.util.List;

@Service
@Slf4j
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);


        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在！");
        }
        log.info(user.toString());

        user.setRoles(userRepository.getOne(user.getId()).getRoles());
        return user;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public User updateUser(String oldpass,User user) {

        BCryptPasswordEncoder bce =new BCryptPasswordEncoder();
        if(bce.matches(oldpass, user.getPassword())){
            String encodePass = bce.encode(oldpass);
            return userRepository.save(User
                    .builder().
                            username(user.getUsername()).
                            telephone(user.getTelephone()).
                            email(user.getEmail()).
                            enabled(user.getEnabled()).
                            roles(user.getRoles()).

                            password(encodePass).
                            build());
        }
        return null;


    }


    //@Transactional
    public User addNewUser(User user) {

        // 默认给user权限
        // 已用触发器代替
        //userRoleRepository.save(UserRole.builder().r_id(1L).u_id(null).comment("new comer").build());
        //加密

        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }



}
