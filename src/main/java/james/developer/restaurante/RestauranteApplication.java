package james.developer.restaurante;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EntityScan(basePackages = {"james.developer.restaurante.model"})
@ComponentScan(basePackages = {"james.*"})
@EnableJpaRepositories(basePackages = {"james.developer.restaurante.repository"})
@EnableTransactionManagement
@EnableWebMvc
@RestController
@EnableAutoConfiguration
@EnableCaching
public class RestauranteApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(RestauranteApplication.class, args);
		System.out.println(new BCryptPasswordEncoder().encode("123"));
		
		
		
	}
		
	    /*Mapeamento Global que refletem em todo o sistema*/
		@Override
		
		public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/home/**")
		.allowedMethods("*")
		.allowedOrigins("*");
		
		
		registry.addMapping("/contato/**")
		.allowedMethods("*")
		.allowedOrigins("*");
		
		
		registry.addMapping("/adm/**")
		.allowedMethods("*")
		.allowedOrigins("*");
		
		registry.addMapping("/menu/**")
		.allowedMethods("*")
		.allowedOrigins("*");
		
		

		
		
		
	}

}
