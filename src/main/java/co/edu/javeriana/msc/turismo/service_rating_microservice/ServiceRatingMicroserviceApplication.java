package co.edu.javeriana.msc.turismo.service_rating_microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ServiceRatingMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceRatingMicroserviceApplication.class, args);
	}

}
