package se.alphadev.emotion;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import se.alphadev.emotion.api.Challenge;
import se.alphadev.emotion.api.FaceAndScores;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ChallengeApplication.class)
@WebIntegrationTest
public class ChallengeApplicationTests {
	

	private Logger log = LoggerFactory.getLogger( ChallengeApplication.class );

	private static final int port = 8080;
	private static final String BASE_URL = "http://localhost:"+ port;
	private static final String GET_IMAGE_PATH = "/challenge/get-image1";
		
	@Test
	public void challenge() throws IOException {
		RestTemplate restTemplate = new RestTemplate();
		
		restTemplate.getMessageConverters().add(new BufferedImageHttpMessageConverter());
		
		LinkedMultiValueMap<String,Object> multipartMap = new LinkedMultiValueMap<String, Object>();
		multipartMap.add("json", buildFaceAndScores() );
		multipartMap.add("file", new FileSystemResource( ResourceUtils.getFile("classpath:recognition4.jpg") ));
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		
		HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = 
				new HttpEntity<LinkedMultiValueMap<String, Object>>(multipartMap, headers);
		
		ResponseEntity<BufferedImage> response = restTemplate.exchange(
				BASE_URL + "/challenge/"+ Challenge.surprise , HttpMethod.POST, requestEntity,
                BufferedImage.class);
		BufferedImage bufferedImage = response.getBody();
		

		Assertions.assertThat (response.getStatusCode()).isEqualTo(HttpStatus.OK);
		Assertions.assertThat (bufferedImage).isNotNull();
		
		ImageIO.write( bufferedImage , "jpg", new File("winner.jpg"));
	}
	
	private List<FaceAndScores> buildFaceAndScores() throws IOException {
		return new ObjectMapper()
				.readValue(IOUtils.toString( ResourceUtils.getFile("classpath:recognition4.json").toURI() ),
						new TypeReference<List<FaceAndScores>>(){});
	}
}
