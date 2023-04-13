package com.example.chatgpt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;

@Configuration
@Getter
@Setter
@ConfigurationProperties(prefix = "chatgpt")
public class ChatGPTProperties {

	private String token;
	private String model;
	private Integer maxtoken;

	public void setToken(String token) {
		Assert.notNull(token, "Informe seu token do Chat GPT nas properties");
		this.token = token;
	}
}
