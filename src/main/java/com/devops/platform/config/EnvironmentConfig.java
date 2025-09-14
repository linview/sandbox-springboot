package com.devops.platform.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import jakarta.annotation.PostConstruct;

@Configuration
public class EnvironmentConfig {

    @Value("${spring.profiles.active:dev}")
    private String activeProfile;

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${server.port}")
    private int serverPort;

    @Value("${spring.datasource.url}")
    private String datasourceUrl;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddlAuto;

    @PostConstruct
    public void printEnvironmentInfo() {
        System.out.println("=== 环境配置信息 ===");
        System.out.println("激活的Profile: " + activeProfile);
        System.out.println("应用名称: " + applicationName);
        System.out.println("服务器端口: " + serverPort);
        System.out.println("数据源URL: " + datasourceUrl);
        System.out.println("DDL策略: " + ddlAuto);
        System.out.println("==================");
    }

    // 环境特定的配置方法
    @Profile("dev")
    public static class DevConfig {
        @PostConstruct
        public void devSpecificConfig() {
            System.out.println("🔧 开发环境配置已加载");
        }
    }

    @Profile("test")
    public static class TestConfig {
        @PostConstruct
        public void testSpecificConfig() {
            System.out.println("🧪 测试环境配置已加载");
        }
    }

    @Profile("staging")
    public static class StagingConfig {
        @PostConstruct
        public void stagingSpecificConfig() {
            System.out.println("🎭 预发布环境配置已加载");
        }
    }

    @Profile("prod")
    public static class ProdConfig {
        @PostConstruct
        public void prodSpecificConfig() {
            System.out.println("🏭 生产环境配置已加载");
        }
    }
}
