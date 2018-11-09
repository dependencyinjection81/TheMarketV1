package com.market.repositories;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class StaticBlackListOnRuntime {

  static Map<String, Integer> BLACKLIST = new HashMap<>();

  /**
   * Add blacklisted IP adress to a static Map.
   * @param ipAddress
   * @return true if the IP adress was new false if the IP adress has already been added
   */
  public boolean addIp(final String ipAddress) {
    if (BLACKLIST.containsKey(ipAddress)) {
      return false;
    } else {
      BLACKLIST.put(ipAddress, 0);
      return true;
    }   
  }

  /**
   * 
   * @param ipAddress
   * @return
   */
  public boolean increaseCounter(final String ipAddress) {

    if (BLACKLIST.containsKey(ipAddress)) {
      Integer listedIpAdressCounter = BLACKLIST.get(ipAddress);
      listedIpAdressCounter += 1;
      BLACKLIST.replace(ipAddress, listedIpAdressCounter);
    } else {
      return false;
    }
    return true;
  }

  /**
   * 
   * @param ipAddress
   * @return
   */
  public Integer getIpCounter(final String ipAddress) {
    return BLACKLIST.get(ipAddress);
  }

}
