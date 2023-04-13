package com.example.chatgpt;

import com.theokanning.openai.OpenAiHttpException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/chatgpt")
@RestController
@RequiredArgsConstructor
public class ChatGPTController {

	private final ChatGPTService service;
	@PostMapping
	public ResponseEntity<Object> iterationPost(@RequestBody ChatGPT chatDTO){
		ChatGPT chatGPT = service.iterate(chatDTO);
		return new ResponseEntity<>(chatGPT, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<Object> iterationGet(@RequestParam(value = "message", required = false, defaultValue = "") String message,
	                                           @RequestParam(value = "max-token", required = false, defaultValue = "") Integer maxTtoken) throws OpenAiHttpException {
		ChatGPT chatGPT = service.iterate(new ChatGPT(message), maxTtoken);
		return new ResponseEntity<>(chatGPT, HttpStatus.OK);
	}

}
