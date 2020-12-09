package edu.zjut.crowdsourcing.crdsrcingdemo.service;

import edu.zjut.crowdsourcing.crdsrcingdemo.model.Location;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Slf4j
class LocationServiceTest {
    @Autowired
    private LocationService locationService;

    @Test
    void uploadLocation() {
        int x=-1;
        //Location(location_longtitude=116.397128, location_latitude=39.916527, direction=10.33, velocity=[30.1], timestamp=2020-12-12)
        Location location =Location.builder().
                location_latitude(new BigDecimal("39.916527")).
                location_longtitude(new BigDecimal("116.397128")).
                direction(10.33).
                velocity(Collections.singletonList(30.1)).
                timestamp("2020-12-12").build();
        try {
            x = locationService.uploadLocation(location);
        } catch (Exception e) {
            log.info("Something wrong with the redis {}", x);
        }


    }

    @Test
    void getLocations() {
        locationService.getLocation();
    }
}