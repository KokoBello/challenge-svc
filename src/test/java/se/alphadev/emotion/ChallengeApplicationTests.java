package se.alphadev.emotion;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ChallengeApplication.class)
//@WebAppConfiguration
@WebIntegrationTest
public class ChallengeApplicationTests {

//	@Value("${local.server.port}")
	private int port = 8080;

	@Test
	public void contextLoads() {		
		String reply = new TestRestTemplate()
				.getForObject("http://localhost:" + this.port + "/challenge", String.class);
		Assertions.assertThat ( reply ).isEqualTo( "Challenge resource up and running!" );
	}
	
	@Test
	public void getImage() throws IOException, URISyntaxException {
		
//		BufferedImage reply = restTemplate
//				.getForObject("http://localhost:" + this.port + "/challenge/get-image", BufferedImage.class);
//		ResponseEntity<BufferedImage> reply = restTemplate
//				.getForEntity("http://localhost:" + this.port + "/challenge/get-image", BufferedImage.class);
		
//		System.out.println( Lists.newArrayList( ImageIO.getReaderMIMETypes() ) );

		TestRestTemplate restTemplate = new TestRestTemplate();

		
//		restTemplate.getMessageConverters().add(new BufferedImageHttpMessageConverter());

//		RequestEntity<Void> request = RequestEntity.get(new URI("http://localhost:" + this.port + "/challenge/get-image2"))
//				.accept(MediaType.IMAGE_JPEG).build();
//		
//		ResponseEntity<BufferedImage> response = restTemplate
//				.exchange(request, BufferedImage.class);
//		ImageIO.write( response.getBody() , "jpg", new File("koko2jpg"));

		
	    HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.IMAGE_JPEG));
	    HttpEntity<String> entity = new HttpEntity<String>(headers);

	    ResponseEntity<byte[]> response = restTemplate.exchange(
	            "http://localhost:" + this.port + "/challenge/get-image2",
	            HttpMethod.GET, entity, byte[].class);
		
	    Files.write( Paths.get("koko1.png"), response.getBody() );
		
	}
	
	@Test
	public void getImageText() {
		String reply = new TestRestTemplate()
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
