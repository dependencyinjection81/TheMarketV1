package com.market.repositories;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class StaticBlackListOnRuntime {

  static Map<String, Integer> BLACKLIST = new HashMap<>();

  public boolean addIp(final String ipAddress) {
    if (BLACKLIST.containsKey(ipAddress)) {
      return false;
    } else {
      BLACKLIST.put(ipAddress, 0);
      return true;
    }   
  }

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

  public Integer getIpCounter(final String ipAddress) {
    return BLACKLIST.get(ipAddress);
  }

}
