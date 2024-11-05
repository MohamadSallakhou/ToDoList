package de.htwberlin.webtech.webtech_ws2425_backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class WebtechWs2425BackendApplicationTests {

	@Autowired
	private ApplicationContext applicationContext;

	@Test
	void checkIfBeansAreLoaded() {
		// Überprüfen, ob eine Bean vom Typ HalloWorldController im Spring Context vorhanden ist
		boolean beanExists = applicationContext.containsBeanDefinition("halloWorldController");
		assertThat(beanExists).isTrue().withFailMessage("HalloWorldController wurde nicht geladen");
	}
}
