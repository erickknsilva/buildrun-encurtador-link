package com.buildrun.encurtadorlinkfbr;

import com.buildrun.encurtadorlinkfbr.adapter.in.web.UserControllerAdapterIn;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({UserControllerAdapterIn.class})
public class EncurtadorLinkFbrApplication {
    public static void main(String[] args) {
        SpringApplication.run(EncurtadorLinkFbrApplication.class, args);
    }
}
