package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.example.demo.config.property.LojaApiProperty;

@EnableConfigurationProperties(LojaApiProperty.class)
@SpringBootApplication
public class MvApplication {

	public static void main(String[] args) {
		SpringApplication.run(MvApplication.class, args);
	}

}
