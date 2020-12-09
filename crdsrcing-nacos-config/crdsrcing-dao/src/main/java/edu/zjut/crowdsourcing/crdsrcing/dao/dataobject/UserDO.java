package edu.zjut.crowdsourcing.crdsrcing.dao.dataobject;

import lombok.Data;
import lombok.ToString;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
@Data
@ToString
//@RefreshScope
@Slf4j
@ConfigurationProperties(prefix="user")
public class UserDO implements InitializingBean, DisposableBean {
    private Long id;

    private String name;

    private Integer age;

    @Override
    public void destroy() throws Exception {
        System.out.println("[destroy()]" + toString());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("[afterPropertiesSet()]" + toString());
    }
}
