package com.chandlertu.minion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MinionApplication {

  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication.run(MinionApplication.class, args);
    ReachResultService service = context.getBean(ReachResultService.class);
    boolean doit = true;
    while (doit) {
      service.reach();
    }
  }

}
