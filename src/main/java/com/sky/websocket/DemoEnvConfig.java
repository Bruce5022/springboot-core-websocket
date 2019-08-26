package com.sky.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class DemoEnvConfig implements EnvironmentPostProcessor, CommandLineRunner {
    @Autowired
    Environment environment;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(environment.getProperty("rcm"));
    }

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        MutablePropertySources propertySources = environment.getPropertySources();
        Properties properties = new Properties();
        properties.setProperty("rcm","rcm/");
        propertySources.addLast(new PropertiesPropertySource("szwfile",properties));
    }
}

