package com.github.ethanpang.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.util.Pool;

/**
 * Created by ethan on 2017/7/28.
 */
@Configuration
@ConfigurationProperties(prefix = "spring.redis")
public class RedisConfig {
    private static Logger logger= LoggerFactory.getLogger(RedisConfig.class);
    private String hostName;
    private int port;
    private String password;
    private int timeout;
    
    @Bean
    public JedisPoolConfig getRedisConfig(){
        JedisPoolConfig config =new JedisPoolConfig();
        return config;
    }

    @Bean
    public Pool<Jedis> getJedisPool(){
        JedisPoolConfig config =getRedisConfig();
        Pool<Jedis> pool =new JedisPool(config,hostName,port,timeout,null,0);
        logger.info("init JedisPool...");


        return pool;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        RedisConfig.logger = logger;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
}
