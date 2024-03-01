package org.mvnsearch.service;

import org.mvnsearch.config.AppConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

@Service
@ConfigurationProperties
public class PigeonService {
    AppConfig.Pigeon pigeon;

    public void setPigeon(AppConfig.Pigeon pigeon) {
        this.pigeon = pigeon;
    }

    public PigeonService() {
    }

    public AppConfig.Pigeon getPigeon() {
        return pigeon;
    }
}
