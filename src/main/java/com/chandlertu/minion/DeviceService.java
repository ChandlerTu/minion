package com.chandlertu.minion;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Properties;

import lombok.extern.apachecommons.CommonsLog;

import org.springframework.stereotype.Component;

@CommonsLog
@Component
public class DeviceService {

  public Device getDevice() {
    String hostAddress = null;
    String hardwareAddress = null;
    try {
      InetAddress localHost = InetAddress.getLocalHost();
      hostAddress = localHost.getHostAddress();
      NetworkInterface networkInterface = NetworkInterface.getByInetAddress(localHost);
      byte[] bytes = networkInterface.getHardwareAddress();
      if (bytes != null) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
          sb.append(String.format("%02x", bytes[i]));
          if (i < bytes.length - 1) {
            sb.append("-");
          }
        }
        hardwareAddress = sb.toString().toUpperCase();
      }
    } catch (Exception e) {
      log.error("", e);
    }

    Properties properties = System.getProperties();
    String osName = properties.get("os.name").toString();
    String javaVersion = properties.get("java.version").toString();

    Device device = new Device();
    device.setHostAddress(hostAddress);
    device.setHardwareAddress(hardwareAddress);
    device.setOsName(osName);
    device.setJavaVersion(javaVersion);
    return device;
  }

}
