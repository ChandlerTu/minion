package com.chandlertu.minion;

import lombok.extern.apachecommons.CommonsLog;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@CommonsLog
@RunWith(SpringRunner.class)
@SpringBootTest
public class DeviceServiceTests {

  @Autowired
  private DeviceService deviceService;

  @Test
  public void getDevice() {
    Device device = deviceService.getDevice();
    log.info(device.getHostAddress());
    log.info(device.getHardwareAddress());
    log.info(device.getOsName());
    log.info(device.getJavaVersion());
  }

}
