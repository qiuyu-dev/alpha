package com.mysoft.alpha.config;

import org.springframework.cache.annotation.CachingConfigurerSupport;

/**
 * 暂时不用redis gll20200724
 */

//@EnableCaching
//@Configuration
public class RedisConfig extends CachingConfigurerSupport {
    public static final String REDIS_KEY_DATABASE="alpha";

//    @Bean
//    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
//        RedisSerializer<Object> serializer = redisSerializer();
//        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(redisConnectionFactory);
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.setValueSerializer(serializer);
//        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
//        redisTemplate.setHashValueSerializer(serializer);
//        redisTemplate.afterPropertiesSet();
//        return redisTemplate;
//    }
//
//    @Bean
//    public RedisSerializer<Object> redisSerializer() {
//        //创建JSON序列化器
//        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(Object.class);
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        serializer.setObjectMapper(objectMapper);
//        return serializer;
//    }
//
//    @Bean
//    public RedisCacheManager redisCacheManager(RedisConnectionFactory redisConnectionFactory) {
//        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory);
//        //设置Redis缓存有效期为1天
//        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
//                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer())).entryTtl(Duration.ofDays(1));
//        return new RedisCacheManager(redisCacheWriter, redisCacheConfiguration);
//    }
}
