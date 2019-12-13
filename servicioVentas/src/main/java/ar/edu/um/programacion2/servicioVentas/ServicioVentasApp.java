package ar.edu.um.programacion2.servicioVentas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ServicioVentasApp {

	@Bean
	public RestTemplate rest() {
		return new RestTemplate();
	}

	
	public static void main(String[] args) {
		SpringApplication.run(ServicioVentasApp.class, args);
	}
	

}
