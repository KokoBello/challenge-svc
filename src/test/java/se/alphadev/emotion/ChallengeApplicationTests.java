package se.alphadev.emotion;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.collect.Lists;

//import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ChallengeApplication.class)
//@WebAppConfiguration
@WebIntegrationTest
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
	public void getImage() throws IOException, URISyntaxException {
		
//		BufferedImage reply = restTemplate
//				.getForObject("http://localhost:" + this.port + "/challenge/get-image", BufferedImage.class);
//		ResponseEntity<BufferedImage> reply = restTemplate
//				.getForEntity("http://localhost:" + this.port + "/challenge/get-image", BufferedImage.class);
		
		System.out.println( Lists.newArrayList( ImageIO.getReaderMIMETypes() ) );

		restTemplate.getMessageConverters().add(new BufferedImageHttpMessageConverter());
		
		RequestEntity<Void> request = RequestEntity.get(new URI("http://localhost:" + this.port + "/challenge/get-image"))
				.accept(MediaType.IMAGE_PNG).build();
		ResponseEntity<BufferedImage> reply = restTemplate
				.exchange(request, BufferedImage.class);
		
		ImageIO.write( reply.getBody() , "png", new File("./koko.png"));
	}
	
	@Test
	public void getImageText() {
		String reply = restTemplate
				.getForObject("http://localhost:" + this.port + "/challenge/get-image-text", String.class);
		System.out.println( reply );
	}
	
	@Test
	public void challenge() {
		
//		LinkedMultiValueMap<String,Object> linkedMultiValueMap = new LinkedMultiValueMap<String, Object>();
//		new RequestEntity<String>("body", linkedMultiValueMap, HttpMethod.GET, url)		
//		restTemplate.exchange(requestEntity, responseType)postForObject(url, responseType)
		
//		BufferedImage img = ImageIO.read( ResourceUtils.getFile("classpath:recognition4.jpg") );
//		
//		BufferedImage image = restTemplate
//				.postForObject("http://localhost:" + this.port + "/challenge/ANGRIEST", img, BufferedImage.class);

//		BufferedImage image2 = restTemplate
//				.postForObject("http://localhost:" + this.port + "/challenge/ANGRIEST", BufferedImage.class);
		
//		ResponseEntity<String> entity = restTemplate
//				.getForObject("http://localhost:" + this.port + "/challenge", BufferedImage.class);
//		
//		Assertions.assertThat (entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		
	}

	
}
