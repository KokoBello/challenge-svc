package se.alphadev.emotion;

import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChallengeApplication {

	public static void main(String[] args) {
		SLF4JBridgeHandler.install();
		SpringApplication.run(ChallengeApplication.class, args);
	}
}
