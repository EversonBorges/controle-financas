package br.com.controlefinancas.api.documentation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SpringDocConfiguration {

	@Bean
	 public OpenAPI customOpenAPI() {
	   return new OpenAPI()
	          //.components(new Components()
	         // .addSecuritySchemes("bearer-key",
	        //		  new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
	          .info(new Info()
	        		  .title("Controle finanças")
	        		  .description("api para registro de compras realizadas em cartão de crédito e controle de finanças")
	        		  .contact(new Contact()
	        				  .name("Squad Backend")
	        				  .email("everson.cursos@gmail.com"))
	        		  .license(new License()
	        				  .name("Apache 2.0")
	        				  .url("http://controle_financas/api/licenca")));
	}
}
