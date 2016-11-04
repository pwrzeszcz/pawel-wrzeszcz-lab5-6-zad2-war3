package com.lab;

import com.lab.storage.StorageProperties;
import com.lab.storage.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class TransferApplication implements CommandLineRunner
{
	private static final Logger log = LoggerFactory.getLogger(TransferApplication.class);
    private JdbcTemplate jdbcTemplate;

	@Autowired
    public TransferApplication(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    public static void main(String[] args) throws Exception
	{
		SpringApplication.run(TransferApplication.class, args);
	}

	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			storageService.deleteAll();
			storageService.init();
		};
	}

	@Override
	public void run(String... string) throws Exception
	{
		log.info("Creating tables");

		jdbcTemplate.execute("DROP TABLE transfer IF EXISTS");
		jdbcTemplate.execute("CREATE TABLE transfer(" +
				"id SERIAL, name VARCHAR(255), surname VARCHAR(255), transfer_title VARCHAR(255)," +
				"account_number VARCHAR(255))");
	}
}
