package com.project.test.parameta.empleadosoap;

import com.project.test.parameta.commons.configuration.ApplicationConfig;
import com.project.test.parameta.commons.configuration.SpringSecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@ComponentScan(
		basePackages = "com.project.test.parameta"
)
@Import({SpringSecurityConfig.class, ApplicationConfig.class})
public class EmpleadosoapApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmpleadosoapApplication.class, args);
	}

}
