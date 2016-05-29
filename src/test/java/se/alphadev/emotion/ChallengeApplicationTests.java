package se.alphadev.emotion;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.ResourceUtils;

//import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ChallengeApplication.class)
@WebAppConfiguration
public class ChallengeApplicationTests {

//	@Value("${local.server.port}")
	private int port = 8080;

	private TestRestTemplate restTemplate = new TestRestTemplate();
	
	@Test
	public void contextLoads() {		
		String reply = restTemplate
				.getForObject("http://localhost:" + this.port + "/challenge", String.class);
		
		Assertions.assertThat ( reply ).isEqualTo( "Challenge resource up and running!" );
	}
	
	@Test
	public void challenge() {
		
		BufferedImage img = ImageIO.read( ResourceUtils.getFile("classpath:recognition4.jpg") );
		
		BufferedImage image = restTemplate
				.postForObject("http://localhost:" + this.port + "/challenge/ANGRIEST", img, BufferedImage.class);

//		BufferedImage image2 = restTemplate
//				.postForObject("http://localhost:" + this.port + "/challenge/ANGRIEST", BufferedImage.class);
		
//		ResponseEntity<String> entity = restTemplate
//				.getForObject("http://localhost:" + this.port + "/challenge", BufferedImage.class);
//		
//		Assertions.assertThat (entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		
	}

}
