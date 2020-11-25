package edu.zjut.mobilr.authservice.service;

import edu.zjut.mobilr.authservice.client.HelloClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RestService {

        @Autowired
        HelloClient helloClient;


        String getHello(){
                log.info("Retrieving hello from other service using REST Template");

                return helloClient.getHello();
        }
}
