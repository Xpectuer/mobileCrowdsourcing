package edu.zjut.crowdsourcing.crdsrcing;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import edu.zjut.crowdsourcing.crdsrcing.dao.dataobject.UserDO;

@Slf4j
@SpringBootApplication
//@RefreshScope
//@RestController
//@EnableConfigurationProperties(UserDO.class)
@EnableDiscoveryClient
public class CrdsrcingApplication {
//    @Value("${user.name}")
//    private String userName;
//
//    @Value("${user.age}")
//    private Integer userAge;

//    @Autowired
//    private UserDO userDO;
//
//    @RequestMapping("/user")
//    public String user() {
//        return "[HTTP] " + userDO;
//    }

//    @PreDestroy
//    public void destroy() {
//        log.info("[destroy] username: {}, age: {}\n", userName, userAge);
//    }
//
//    @PostConstruct
//    public void init() {
//        log.info("[init] user name: {} , age :{}", userName, userAge);
//    }

//    @Override
//
//    public void run(ApplicationArguments args) throws Exception {
//        log.info("[init] user name: {} , age : \n", userName);
//
//    }

    public static void main(String[] args) {
        SpringApplication.run(CrdsrcingApplication.class, args);
    }

}
