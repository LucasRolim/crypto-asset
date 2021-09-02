package com.crypto.cryptoassets;

import java.util.concurrent.Executor;

import javax.websocket.RemoteEndpoint.Async;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
@EnableAsync
public class CryptoAssetsApplication implements AsyncConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(CryptoAssetsApplication.class, args);
	}

	@Bean(name = "serviceExecutor")
	public Executor ascyncExecutor() {
		final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(10);
		executor.setMaxPoolSize(10);
		executor.initialize();
		return executor;
	}
}
