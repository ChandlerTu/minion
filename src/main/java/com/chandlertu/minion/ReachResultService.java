package com.chandlertu.minion;

import java.net.InetAddress;
import java.util.Date;

import lombok.extern.apachecommons.CommonsLog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@CommonsLog
@Component
public class ReachResultService {

  @Autowired
  private ReachResultRepository repository;

  public void reach() {
    byte[] addr = new byte[4];
    addr[0] = (byte) 192;
    addr[1] = (byte) 168;
    for (int i = 0; i < 256; i++) {
      addr[2] = (byte) i;
      for (int j = 0; j < 256; j++) {
        addr[3] = (byte) j;
        save(reach(addr));
      }
    }
  }

  private ReachResult reach(byte[] addr) {
    ReachResult result = new ReachResult();
    try {
      InetAddress inetAddress = InetAddress.getByAddress(addr);
      result.setHostAddress(inetAddress.getHostAddress());
      result.setReachable(inetAddress.isReachable(1000));
      result.setUpdateTime(new Date());
    } catch (Exception e) {
      log.error("", e);
    }
    return result;
  }

  private void save(ReachResult result) {
    ReachResult old = repository.findByHostAddress(result.getHostAddress());
    if (old != null) {
      result.setId(old.getId());
    }
    repository.save(result);
  }

}
