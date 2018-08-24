package com.chandlertu.minion;

import java.net.InetAddress;

import lombok.extern.apachecommons.CommonsLog;

import org.springframework.stereotype.Component;

@CommonsLog
@Component
public class IpAddressService {

  public void ping() {
    byte[] addr = new byte[4];
    addr[0] = (byte) 192;
    addr[1] = (byte) 168;
    for (int i = 0; i < 256; i++) {
      addr[2] = (byte) i;
      for (int j = 0; j < 256; j++) {
        addr[3] = (byte) j;
        IpAddress ipAddress = ping(addr);
        if (ipAddress.isReachable()) {
          log.info(ipAddress.getHostAddress() + " true");
        } else {
          log.info(ipAddress.getHostAddress() + " false");
        }
      }
    }
  }

  public IpAddress ping(byte[] addr) {
    IpAddress ipAddress = new IpAddress();
    try {
      InetAddress inetAddress = InetAddress.getByAddress(addr);
      ipAddress.setHostAddress(inetAddress.getHostAddress());
      ipAddress.setReachable(inetAddress.isReachable(1000));
    } catch (Exception e) {
      log.error("", e);
    }
    return ipAddress;
  }

}
