package edu.zjut.crowdsourcing.crdsrcingdemo.controller;

import com.alibaba.fastjson.JSONObject;
import edu.zjut.crowdsourcing.crdsrcingdemo.model.Location;
import edu.zjut.crowdsourcing.crdsrcingdemo.model.RespBean;
import edu.zjut.crowdsourcing.crdsrcingdemo.service.LocationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.Map;


@RestController
@RequestMapping("/api")
@Slf4j
public class LocationController {
    /**
     *
     */
    private static final String KEY = "LOCATION_LIST";

    @Autowired
    private LocationService locationService;

    @Autowired
    private ReactiveStringRedisTemplate redisTemplate;


    @GetMapping("/range/{longtitude}/{latitude}")
    public RespBean getLocationByRange(@PathVariable("longtitude") BigDecimal longtitude, @PathVariable("latitude") BigDecimal latitude) {
        return null;
    }

    @GetMapping("/location")
    public RespBean getLocation(){
        Map<String, JSONObject> ret = locationService.getLocation();

        return ret==null?RespBean.error("Error while querying!")
                :RespBean.ok("Successfully get the locations !",ret);
    }

    @PostMapping("/location")
    public RespBean uploadLocation(Location location) {
        if(location.getVelocity()==null||
                location.getLocation_latitude()==null ||
            location.getLocation_longtitude()==null)
            return RespBean.error("parameter error!");
        log.info(location.toString());

        return locationService.uploadLocation(location)==1?
                RespBean.ok("successfully uploaded!"):
                RespBean.error("Error while uploading!");
    }


}
