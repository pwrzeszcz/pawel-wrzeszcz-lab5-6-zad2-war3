package com.lab;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class TransferApplication implements CommandLineRunner
{
	private static final Logger log = LoggerFactory.getLogger(TransferApplication.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public static void main(String[] args) throws Exception
	{
		SpringApplication.run(TransferApplication.class, args);
	}

	@Override
	public void run(String... string) throws Exception
	{
		log.info("Creating tables");

		jdbcTemplate.execute("DROP TABLE trasnfer IF EXISTS");
		jdbcTemplate.execute("CREATE TABLE transfer(" +
				"id SERIAL, name VARCHAR(255), surname VARCHAR(255), transferTitle VARCHAR(255)," +
				"accountNumber VARCHAR(255))");
	}
}
