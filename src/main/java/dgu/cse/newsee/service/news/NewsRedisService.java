package dgu.cse.newsee.service.news;

import java.time.Duration;

public interface NewsRedisService {

    void setKeyWithExpiry(String key, String value, Duration ttl);

    String getKey(String key);

    boolean hasKey(String key);

    void deleteKey(String key);
}
