package dgu.cse.newsee.service.news;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class NewsRedisServiceImpl implements NewsRedisService{

    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public void setKeyWithExpiry(String key, String value, Duration ttl) {
        redisTemplate.opsForValue().set(key, value, ttl);
    }

    @Override
    public String getKey(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    @Override
    public void deleteKey(String key) {
        redisTemplate.delete(key);
    }
}
