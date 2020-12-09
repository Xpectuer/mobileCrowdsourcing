package edu.zjut.crowdsourcing.crdsrcingdemo.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import edu.zjut.crowdsourcing.crdsrcingdemo.model.Location;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnection;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.jedis.exceptions.JedisException;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;


/**
 * version 0.0.1
 */
@Service
@Slf4j
public class LocationService {

    private static final String KEY = "locations";
    @Autowired
    private ReactiveStringRedisTemplate reactiveStringRedisTemplate;

    @Autowired
    private JedisPool jedisPool;

    @Autowired
    private JedisPoolConfig jedisPoolConfig;

    @Bean
    @ConfigurationProperties("redis")
    public JedisPoolConfig jedisPoolConfig() {
        return new JedisPoolConfig();
    };

    @Bean
    public JedisPool jedisPool() {
        return new JedisPool();
    }

    public Map<String, JSONObject> getLocation() {
        // query redis data
        //USING JEDIS

        // TODO：返回一串给定路段的坐标/速度值

        Map<String, String> locations=null;
        Map<String, JSONObject> loc_maps = new HashMap<>();
        log.info(jedisPoolConfig.toString());
        try{
            Jedis jedis = jedisPool.getResource();
            locations = jedis.hgetAll(KEY);

            // process the String 2 OBJ
            locations.forEach( (k,v) ->{
                loc_maps.put(k,JSON.parseObject(v));
            });

            log.info("Locations: {}", locations);

        }catch(JedisConnectionException e) {
            e.printStackTrace();
            return null;
        }

        return loc_maps;

    }
    
    public Integer uploadLocation(Location location) {
        // using JEDIS
        // Redis PUT KEY

        log.info(jedisPoolConfig.toString());
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.hset(KEY,
                    location.getTimestamp(),
                    JSON.toJSONString(location)
            );
            log.info("UPLOAD SUCESSFULLY {}", JSON.toJSON(location));
            Map<String, String> locs = jedis.hgetAll(KEY);
            log.info("locs: {}", locs);

        }catch (JedisConnectionException e) {
            log.info(e.getMessage());
            return 0;
        }
        return 1;
    }
    // Redis PUSHD
}
//    ReactiveHashOperations<String, String, String> hashOps = redisTemplate.opsForHash();
//    CountDownLatch cdl = new CountDownLatch(1);
//    // no args
//    Location loc1 = Location.builder().
//            location_latitude(new BigDecimal("39.916527")).
//            location_longtitude(new BigDecimal("116.397128")).
//            direction(10.33f).timestamp("2000-3-22").velocity(30.1f).build();
//    Location loc2 = Location.builder().
//            location_latitude(new BigDecimal("39.916527")).
//            location_longtitude(new BigDecimal("116.397128")).
//            direction(10.33f).timestamp("2000-3-22").velocity(30.1f).build();
//
//    List<Location> list =  new ArrayList<>();
//        list.add(loc1);
//                list.add(loc2);
//                Flux.fromIterable(list)
//                .publishOn(Schedulers.single())
//                .doOnComplete(() -> log.info("list ok"))
//                .flatMap(c->{
//                log.info("try to put {}, {}", c.getLocation_latitude(), c.getLocation_longtitude());
//                return hashOps.put(KEY, c.getLocation_latitude().toString(),
//                c.getLocation_longtitude().toString());
//                })
//                .doOnComplete(() -> log.info("set ok"))
//                .concatWith(redisTemplate.expire(KEY, Duration.ofMinutes(1)))
//                .doOnComplete(() -> log.info("expire ok"))
//                .onErrorResume(e ->{
//                log.error("exception {}", e.getMessage());
//                return Mono.just(false);
//                });
//