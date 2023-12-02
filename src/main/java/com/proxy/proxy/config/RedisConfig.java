package com.proxy.proxy.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableCaching
//@EnableRedisRepositories(basePackages = "com.proxy.proxy.repositories")
public class RedisConfig {
	 	@Bean
	 	 public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
	         RedisTemplate<String, Object> template = new RedisTemplate<>();
	         template.setConnectionFactory(factory);

	         template.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
	         template.setKeySerializer(new StringRedisSerializer());

	         return template;
	     }
}
