package com.team13.WaitDoc.Batch.app.base.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Getter
    private static String apiUrl_1;

    @Value("${custom.API.url_1}")
    public void setApiUrl_1(String apiUrl) {
        AppConfig.apiUrl_1 = apiUrl;
    }

    @Getter
    private static String apiUrl_2;

    @Value("${custom.API.url_2}")
    public void setApiUrl_2(String apiUrl) {
        AppConfig.apiUrl_2 = apiUrl;
    }

    @Getter
    private static String serviceKey_1;

    @Value("${custom.API.service_key_1}")
    public void setServiceKey_1(String serviceKey_1) {
        AppConfig.serviceKey_1 = serviceKey_1;
    }

    @Getter
    private static String serviceKey_2;

    @Value("${custom.API.service_key_2}")
    public void setServiceKey_2(String serviceKey_2) {
        AppConfig.serviceKey_2 = serviceKey_2;
    }

    @Getter
    private static String serviceKey_3;

    @Value("${custom.API.service_key_3}")
    public void setServiceKey_3(String serviceKey_3) {
        AppConfig.serviceKey_3 = serviceKey_3;
    }

    @Getter
    private static String serviceKey_4;

    @Value("${custom.API.service_key_4}")
    public void setServiceKey_4(String serviceKey_4) {
        AppConfig.serviceKey_4 = serviceKey_4;
    }
}
