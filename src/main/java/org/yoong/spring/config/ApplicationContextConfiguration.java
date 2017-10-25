package org.yoong.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan({"org.yoong.spring"})
@Import(value={SecurityConfig.class})

public class ApplicationContextConfiguration {

}
