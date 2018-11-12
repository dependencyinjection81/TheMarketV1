package com.market.service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/**
 * Adding clients IP adresses to a cached blacklist for further banning. 
 * @author Johannes Weiss
 *
 */
@Service
public class LoginAttemptService {

  /**
   * Maxium allowed login attempts.
   */
  // TODO replace by a property value.
  private static final int MAX_ATTEMPT = 10;

  /**
   * Cache where login attempts will be stored.
   */
  private LoadingCache<String, Integer> attemptsCache;

  /**
   * Ctor.
   */
  public LoginAttemptService() {
    super();
    attemptsCache = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.DAYS)
        .build(new CacheLoader<String, Integer>() {
          @Override
          public Integer load(final String key) {
            return 0;
          }
        });
  }

  /**
   * Once a login succeeded the corresponding value for the given key will be discarded.
   * 
   * @param key key
   */
  public void loginSucceeded(final String key) {
    attemptsCache.invalidate(key);
  }

  /**
   * Increase counter to a given IP adress for every failed login attemp.
   * @param key key.
   */
  public void loginFailed(final String key) {
    int attempts = 0;
    try {
      attempts = attemptsCache.get(key);
    } catch (final ExecutionException e) {
      attempts = 0;
    }
    attempts++;
    attemptsCache.put(key, attempts);
  }

  /**
   * Tells if a given IP adress is blocked yet.
   * @param key key.
   * @return
   */
  public boolean isBlocked(final String key) {
    try {
      return attemptsCache.get(key) >= MAX_ATTEMPT;
    } catch (final ExecutionException e) {
      return false;
    }
  }
}
