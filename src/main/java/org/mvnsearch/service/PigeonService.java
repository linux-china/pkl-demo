package org.mvnsearch.service;

import org.mvnsearch.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

@Service
public class PigeonService {
  @Autowired
  AppConfig.Pigeon pigeon;

  public AppConfig.Pigeon getPigeon() {
    return pigeon;
  }
}
