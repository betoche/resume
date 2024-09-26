package org.als.resume.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.cache.CacheManager;

import java.util.Collections;

public class ResumeDBCacheConfig {
    public static final String CACHE_NAME = "resume_templates";

    @Bean(destroyMethod = "shutdown")
    public RedissonClient redissonClient(){
        var config = new Config();

        config.useSingleServer()
                .setAddress("redis://127.0.0.1:6379")
                .setPassword("my_resume_password");

        return Redisson.create(config);
    }

    @Bean
    @Autowired
    public CacheManager cacheManager(RedissonClient redissonClient) {
        var config = Collections.singletonMap(CACHE_NAME, new CacheConfig());

        return new RedissonSpringCacheManager(redissonClient, config);
    }
}
