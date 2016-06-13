package se.alphadev.emotion;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import javax.imageio.ImageIO;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

//import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ChallengeApplication.class)
//@WebAppConfiguration
@WebIntegrationTest
public class ChallengeApplicationTests {
	
	Logger log = LoggerFactory.getLogger( ChallengeApplication.class );

//	@Value("${local.server.port}")
	private int port = 8080;
	private String baseUrl = "http://localhost:"+ port;

	@Test
	public void contextLoads() {		
		String reply = new RestTemplate()
				.getForEntity(baseUrl + "/challenge", String.class).getBody();
		Assertions.assertThat ( reply ).isEqualTo( "Challenge resource up and running!" );
	}
	
	@Test
	public void getImage_getForEntity_bufferedImage() throws Exception {
		log.debug("KOKO");
		ResponseEntity<BufferedImage> response = new RestTemplate()
				.getForEntity(baseUrl + "/challenge/get-image1", BufferedImage.class);

		ImageIO.write( response.getBody(), "jpg", Paths.get("koko1.png").toFile());
	}
	
	@Test
	public void getImage() throws IOException, URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();

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
	            baseUrl+"/challenge/get-image2",
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
