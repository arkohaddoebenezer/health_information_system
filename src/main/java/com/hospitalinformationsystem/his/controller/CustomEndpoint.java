package com.hospitalinformationsystem.his.controller;

import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.boot.actuate.metrics.MetricsEndpoint;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Endpoint(id = "importantInfo")
public class CustomEndpoint {

    private final HealthEndpoint healthEndpoint;
    private final MetricsEndpoint metricsEndpoint;
    private final Environment environment;

    public CustomEndpoint(HealthEndpoint healthEndpoint, MetricsEndpoint metricsEndpoint, Environment environment) {
        this.healthEndpoint = healthEndpoint;
        this.metricsEndpoint = metricsEndpoint;
        this.environment = environment;
    }

    @ReadOperation
    public Map<String, Object> getImportantInfo() {
        Map<String, Object> info = new HashMap<>();

        info.put("health", healthEndpoint.health());
        info.put("memoryUsage", metricsEndpoint.metric("jvm.memory.used", null));
        info.put("activeProfiles", environment.getActiveProfiles());
        info.put("javaVersion", environment.getProperty("java.version"));
        info.put("osName", environment.getProperty("os.name"));

        return info;
    }
}
