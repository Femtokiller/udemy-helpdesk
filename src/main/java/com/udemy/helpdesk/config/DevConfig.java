package com.udemy.helpdesk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.udemy.helpdesk.services.DBService;

@Configuration
@Profile("dev")
public class DevConfig 
{
	  @Autowired 
	  private DBService dbservice;
	  
	  //@Value("${spring.jpa.hibernate.ddl-auto}") 
	  private String value = "none";
	  
	  @Bean 
	  public void instanciaDB() 
	  { 
		  if(value.equals("create")) 
			  this.dbservice.instanciaDB(); 	  
	  }
	 
}
