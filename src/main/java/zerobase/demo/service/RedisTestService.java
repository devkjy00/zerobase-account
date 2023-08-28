package zerobase.demo.service;

import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisTestService {

    private final RedissonClient redissonClient;

    public String getLock() {
        RLock lock = redissonClient.getLock("sampleLock");

        try {
            boolean isLocked = lock.tryLock(1, 3, TimeUnit.SECONDS);
            if (!isLocked) {
                log.error("Redis lock failed");
                return "Redis lock failed";
            }
        }catch (Exception e) {
            log.error("Redis lock failed");
        }
        return "Redis lock success";
    }

}
