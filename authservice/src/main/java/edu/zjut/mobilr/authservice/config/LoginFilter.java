package edu.zjut.mobilr.authservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.zjut.mobilr.authservice.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    @Autowired
    SessionRegistry sessionRegistry;

    @SuppressWarnings("deprecation")
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        //Post filter
        if(!request.getMethod().equals("POST")){
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());

        }

        //String verify_code = (String) request.getSession().getAttribute("verify_code");
        //verify if is JSON
        if(request.getContentType().contains(MediaType.APPLICATION_JSON_VALUE) || request.getContentType().contains(MediaType.APPLICATION_JSON_UTF8_VALUE)){
            Map<String,String> loginData = new HashMap<>();
            try{
                loginData = new ObjectMapper().readValue(request.getInputStream(), Map.class);

            }catch (IOException e){
                log.info("Caught input data exception!");

                e.printStackTrace();
            }finally {
                //加入验证码
                //String code = loginData.get("code");
                //checkCode(response, code, verify_code);

            }
            String username = loginData.get(getUsernameParameter());
            String password = loginData.get(getUsernameParameter());

            if(username == null){
                username = "";
            }
            if(password == null){
                password = "";
            }
            //去掉空格
            username = username.trim();
            //计算token
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
            setDetails(request, authRequest);

            User user = new User();

            user.setUsername(username);
            sessionRegistry.registerNewSession(request.getSession(true).getId(),user);
            return this.getAuthenticationManager().authenticate(authRequest);


        }else {
            // checkCode(response, request.getParameter("code"), verify_code);
            return super.attemptAuthentication(request,response);
        }




    }
    @Deprecated
    public void checkCode(HttpServletResponse resp, String code, String verify_code) {
        if (code == null || verify_code == null || "".equals(code) || !verify_code.toLowerCase().equals(code.toLowerCase())) {
            //验证码不正确
            log.info(code);

            throw new AuthenticationServiceException("验证码不正确");
        }
    }
}

