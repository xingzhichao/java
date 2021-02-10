package com.xzc.spring.testiocstarter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = {TulingService.class})
@ComponentScan(basePackages = "com.xzc.spring.testiocstarter")
public class MainConfig {
    @Bean(name = "tlDataSource")
    public TulingDataSource tulingDataSource() {
        return new TulingDataSource();
    }
}
