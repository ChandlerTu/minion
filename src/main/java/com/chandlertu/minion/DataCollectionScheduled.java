package com.chandlertu.minion;

import lombok.extern.apachecommons.CommonsLog;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@CommonsLog
@Component
public class DataCollectionScheduled {

  @Scheduled(cron = "0 * * * * *")
  public void collect() {
    log.info("collect");
  }

}
