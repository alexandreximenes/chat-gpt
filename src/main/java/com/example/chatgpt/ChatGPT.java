package com.example.chatgpt;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChatGPT implements Serializable {

	private String message;
	private String response;

	public ChatGPT(@NotNull String message) {
		this.message = message;
	}
}
