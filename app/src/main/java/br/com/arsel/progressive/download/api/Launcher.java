package br.com.arsel.progressive.download.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"br.com.arsel.progressive.download.api.*", "br.com.arsel.commons.web.*"})
@EntityScan("br.com.arsel.progressive.download.api.infraestructure.entity")
@EnableJpaRepositories("br.com.arsel.progressive.download.api.infraestructure.repository")
public class Launcher {

	public static void main(String[] args) {
		SpringApplication.run(Launcher.class, args);
	}

}