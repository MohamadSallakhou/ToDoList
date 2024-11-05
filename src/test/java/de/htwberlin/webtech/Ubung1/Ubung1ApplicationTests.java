package de.htwberlin.webtech.Ubung1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Ubung1ApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void contextLoads() {
		// Überprüft, ob der Spring Application Context erfolgreich geladen wird
	}

	@Test
	void helloWorldEndpointShouldReturnDefaultMessage() {
		ResponseEntity<String> response = restTemplate.getForEntity("/todo", String.class);
		assertThat(response.getBody()).contains("todo");
	}

}
