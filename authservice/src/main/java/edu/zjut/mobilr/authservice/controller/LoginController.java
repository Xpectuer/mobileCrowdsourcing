package edu.zjut.mobilr.authservice.controller;


import edu.zjut.mobilr.authservice.config.VerificationCode;
import edu.zjut.mobilr.authservice.model.User;
import edu.zjut.mobilr.authservice.model.bean.RespBean;
import edu.zjut.mobilr.authservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

@RestController
@RefreshScope
public class LoginController {

    @Autowired
    UserService userService;


    @GetMapping("/login")
    public RespBean  login(){return RespBean.error("尚未登陆，请登录");}

    @GetMapping("/verifyCode")
    public void verifyCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        VerificationCode code = new VerificationCode();
        BufferedImage image = code.getImage();
        String text = code.getText();
        HttpSession session = request.getSession(true);
        session.setAttribute("verify_code",text);
        VerificationCode.output(image, response.getOutputStream());
    }
     // for test
    //@GetMapping("/addUsers")
    public RespBean addUser(String username,String password,String telephone, String email) {
        try {
            userService.addNewUser(
                    User.builder()
                            .username(username)
                            .password(password)
                            .enabled(true)
                            .telephone(telephone)
                            .email(email).build());
        }catch (DataIntegrityViolationException e){
            return RespBean.error("Duplicated username!");
        }
        return RespBean.ok("register successfully!");
    }
    //@GetMapping("/findUsers")
    public List<User> findUser() {
        return userService.getAllUsers();
    }
}
