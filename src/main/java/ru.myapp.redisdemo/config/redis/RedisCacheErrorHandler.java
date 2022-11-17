package ru.myapp.redisdemo.config.redis;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.interceptor.CacheErrorHandler;
@Slf4j
public class RedisCacheErrorHandler implements CacheErrorHandler {
    @Override
    public void handleCacheGetError(RuntimeException e, Cache cache, Object key) {
        handleTimeOutException(e);
        log.warn("Unable to get from cache " + cache.getName() + " : " + e.getMessage());
    }
    @Override
    public void handleCachePutError(RuntimeException e, Cache cache, Object key, Object value) {
        handleTimeOutException(e);
        log.warn("Unable to put into cache " + cache.getName() + " : " + e.getMessage());
    }
    @Override
    public void handleCacheEvictError(RuntimeException e, Cache cache, Object key) {
        handleTimeOutException(e);
        log.warn("Unable to evict from cache " + cache.getName() + " : " + e.getMessage());
    }
    @Override
    public void handleCacheClearError(RuntimeException e, Cache cache) {
        handleTimeOutException(e);
        log.warn("Unable to clean cache " + cache.getName() + " : " + e.getMessage());
    }
    /**
     * We handle redis connection timeout exception, if the exception is handled
     * then it is treated as a cache miss and gets the data from actual storage
     */
    private void handleTimeOutException(RuntimeException e) {
    }
}