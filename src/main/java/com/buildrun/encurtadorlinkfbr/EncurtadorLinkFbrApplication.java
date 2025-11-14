package com.buildrun.encurtadorlinkfbr;

import com.buildrun.encurtadorlinkfbr.controller.PingController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({PingController.class})
public class EncurtadorLinkFbrApplication {
    public static void main(String[] args) {
        SpringApplication.run(EncurtadorLinkFbrApplication.class, args);
    }
}
