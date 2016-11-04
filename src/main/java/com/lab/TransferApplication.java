package com.lab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TransferApplication
{
	public static void main(String[] args) throws Exception
	{
		SpringApplication.run(TransferApplication.class, args);

		System.out.println("Hello world");
	}
}
