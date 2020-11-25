package edu.zjut.mobilr.authservice;


import edu.zjut.mobilr.authservice.model.Role;
import edu.zjut.mobilr.authservice.model.User;
import edu.zjut.mobilr.authservice.repo.RoleRepository;
import edu.zjut.mobilr.authservice.repo.UserRepository;
import edu.zjut.mobilr.authservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
@Transactional
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;


    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserService userService;

    @Before
    public void init(){

    }
    @Value("${spring.datasource.password}")
    String password;
    @Test
    public void checkProperties(){
       log.info(password);
    }

    @Test
    public void findAll(){

        List<User> users =userRepository.findAll();

        log.info(String.valueOf(users.size()));


    }
    @Test
    public void encrypt(){
        BCryptPasswordEncoder bce =new BCryptPasswordEncoder();
        log.info(bce.encode("123"));
    }

    @Test
    public void addUser(){

        userService.addNewUser(
                User.builder()
                        .username("alex")
                        .password("123")
                        .enabled(true)
                        .telephone("123213")
                        .email("zhengjiaye@126.com").build()
        );

    }

//    @Test
//    public  void findAll(){
//
//        userService.getAllUsers();
//    }







}
