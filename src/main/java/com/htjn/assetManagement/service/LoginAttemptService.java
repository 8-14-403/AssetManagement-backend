package com.htjn.assetManagement.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by caojy on 2018/1/7.
 */
@Service
public class LoginAttemptService {


    private int MAX_ATTEMPT;

    private int AUTO_UNLOCK;

    private final LoadingCache<String, Integer> attemptsCache;

    public LoginAttemptService(@Value("${loginErrorTimes}") Integer max_attempt, @Value("${autoUnlock}") Integer auto_unlock) {
        super();
        this.MAX_ATTEMPT = max_attempt;
        this.AUTO_UNLOCK = auto_unlock;
        attemptsCache = CacheBuilder.newBuilder().
                expireAfterWrite(AUTO_UNLOCK, TimeUnit.MINUTES).build(new CacheLoader<String, Integer>() {
            public Integer load(String key) {
                return 0;
            }
        });
    }

    public void loginSucceeded(String key) {
        attemptsCache.invalidate(key);
    }

    public void loginFailed(String key) {
        int attempts = 0;
        try {
            attempts = attemptsCache.get(key);
        } catch (ExecutionException e) {
            attempts = 0;
        }
        attempts++;
        attemptsCache.put(key, attempts);
    }

    public boolean isLocked(String key) {
        try {
            return attemptsCache.get(key) >= MAX_ATTEMPT;
        } catch (ExecutionException e) {
            return false;
        }
    }
}
