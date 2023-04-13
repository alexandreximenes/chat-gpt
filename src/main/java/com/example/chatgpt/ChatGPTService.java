package com.example.chatgpt;

import com.theokanning.openai.OpenAiHttpException;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;

@Service
@Slf4j
public class ChatGPTService {

	private final ChatGPTProperties properties;

	public ChatGPTService(ChatGPTProperties properties) {
		this.properties = properties;
	}

	public ChatGPT iterate(ChatGPT chatDTO) throws OpenAiHttpException {
		return iterate(chatDTO, properties.getMaxtoken());
	}

	public ChatGPT iterate(ChatGPT chatDTO, Integer maxtoken) throws OpenAiHttpException {
		String text = getResponseChatGPT(chatDTO, maxtoken);
		chatDTO.setResponse(text);
		log.info(text);
		return chatDTO;
	}

	private String getResponseChatGPT(ChatGPT chatDTO, Integer maxtoken) {
		OpenAiService service = new OpenAiService(properties.getToken());
		Integer maxToken = nonNull(maxtoken) ? maxtoken : properties.getMaxtoken();
		CompletionRequest completionRequest = CompletionRequest.builder()
			  .prompt(chatDTO.getMessage())
			  .model(properties.getModel())
			  .maxTokens(maxToken)
			  .temperature(0.7)
			  .echo(true)
			  .frequencyPenalty(0.1)
			  .build();

		return service.createCompletion(completionRequest).getChoices().get(0).getText();
	}
}
