package com.dh.catalogservice;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/*@EnableFeignClients -> para poder llamar desde catálogo a los MS movie y service */
/*@EnableEurekaClient -> Habilita a éste MS a para que se registre en el servidor de eureka */
/*@EnableDiscoveryClient -> Para que el balanceador de carga obtenga de Eureka la información de las instancias en ejecución de cada MS*/

@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
@EnableDiscoveryClient
@EnableRabbit
public class CatalogServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatalogServiceApplication.class, args);
	}

}
