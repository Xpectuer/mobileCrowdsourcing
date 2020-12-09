package edu.zjut.crdsrcing.crdsringconsumer.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("crdsrcing-nacos-config")
public interface EchoService {
    @GetMapping("/echo/{message}")
    String echo(@PathVariable("message")String message);
}
