package com.tuhaibei.akvelonTest;

import com.tuhaibei.akvelonTest.config.QuartzConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import({ QuartzConfig.class })
@SpringBootApplication
public class AkvelonTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(AkvelonTestApplication.class, args);
	}

}
