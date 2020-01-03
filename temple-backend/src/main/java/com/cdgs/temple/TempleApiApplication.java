package com.cdgs.temple;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.zaxxer.hikari.HikariDataSource;


@SpringBootApplication
public class TempleApiApplication implements CommandLineRunner {

	
	@Autowired
	DataSource dataSource;
	
	public static void main(String[] args) {
		SpringApplication.run(TempleApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Datasources: "+ dataSource);
		HikariDataSource dataSoxe = (HikariDataSource)dataSource;
		System.out.println("Datasource: "+ dataSoxe.getMaximumPoolSize());
	}

}
