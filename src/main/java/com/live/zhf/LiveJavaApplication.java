package com.live.zhf;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiveJavaApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(LiveJavaApplication.class);
        // 禁用Banner
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }

}
