package se.alphadev.emotion;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

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
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
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

//import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ChallengeApplication.class)
//@WebAppConfiguration
@WebIntegrationTest
public class ChallengeApplicationTests {
	

	Logger log = LoggerFactory.getLogger( ChallengeApplication.class );

//	@Value("${local.server.port}")
	private static final int port = 8080;
	private static final String BASE_URL = "http://localhost:"+ port;
	private static final String GET_IMAGE_PATH = "/challenge/get-image1";

	@Test
	public void contextLoads() {		
		String reply = new RestTemplate()
				.getForEntity(BASE_URL + "/challenge", String.class).getBody();
		Assertions.assertThat ( reply ).isEqualTo( "Challenge resource up and running!" );
	}
	
	@Test
	public void getImage_getForEntity_bufferedImage() throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new BufferedImageHttpMessageConverter());
		
		ResponseEntity<BufferedImage> response = restTemplate
				.getForEntity(BASE_URL + GET_IMAGE_PATH, BufferedImage.class);

		ImageIO.write( response.getBody(), "jpg", Paths.get("koko1.jpg").toFile());
	}
	
	@Test
	public void getImage_exchange_byteArray() throws IOException, URISyntaxException {
		
		RestTemplate restTemplate = new RestTemplate();
		
	    HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.IMAGE_JPEG));
	    HttpEntity<String> entity = new HttpEntity<String>(headers);

		ResponseEntity<byte[]> response = restTemplate.exchange(
	            BASE_URL+GET_IMAGE_PATH,
	            HttpMethod.GET, entity, byte[].class);
		
	    Files.write( Paths.get("koko2.jpg"), response.getBody() );
		
	}
	
	@Test
	public void getImage_() throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		
		restTemplate.getMessageConverters().add(new BufferedImageHttpMessageConverter());

		RequestEntity<Void> request = RequestEntity.get(new URI(BASE_URL + GET_IMAGE_PATH))
				.accept(MediaType.IMAGE_JPEG).build();
		
		ResponseEntity<BufferedImage> response = restTemplate
				.exchange(request, BufferedImage.class);
		ImageIO.write( response.getBody() , "jpg", new File("koko2.jpg"));
	}
		
	@Test
	public void challenge() throws IOException {
		RestTemplate restTemplate = new RestTemplate();
		
		restTemplate.getMessageConverters().add(new BufferedImageHttpMessageConverter());

//		FormHttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
//		formHttpMessageConverter.addPartConverter(new BufferedImageHttpMessageConverter());
//		restTemplate.getMessageConverters().add( formHttpMessageConverter );
		
		LinkedMultiValueMap<String,Object> multipartMap = new LinkedMultiValueMap<String, Object>();
		multipartMap.add("json", buildFaceAndScores() );
//		multipartMap.add("file", ResourceUtils.getFile("classpath:recognition4.jpg") );
		multipartMap.add("file", new FileSystemResource( ResourceUtils.getFile("classpath:recognition4.jpg") ));
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		
		HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = 
				new HttpEntity<LinkedMultiValueMap<String, Object>>(multipartMap, headers);
		
		ResponseEntity<BufferedImage> response = restTemplate.exchange(
				BASE_URL + "/challenge/"+ Challenge.surprise , HttpMethod.POST, requestEntity,
                BufferedImage.class);
		BufferedImage bufferedImage = response.getBody();

//		BufferedImage bufferedImage = 
//				restTemplate.postForObject(BASE_URL + "/challenge/anger", requestEntity, BufferedImage.class);
		
		ImageIO.write( bufferedImage , "jpg", new File("challenge.jpg"));

//		Assertions.assertThat (response.getStatusCode()).isEqualTo(HttpStatus.OK);
		
	}
	
	private List<FaceAndScores> buildFaceAndScores() throws IOException {
//		Lists.newArrayList(
//				new FaceAndScores()
//					.withFaceRectangle(
//							new FaceRectangle().withLeft(479).withTop(190).withWidth(158).withHeight(158))
//					.withScores(
//							new Scores()
//								.withAnger(0.00001619889)
//								.withContempt(0.000121588469)
//								.withDisgust(0.0000216889184)
//								.withFear(fear)
//							),
//				new FaceAndScores().withFaceRectangle(new FaceRectangle().withHeight(10).withWidth(10))
//			);
		List<FaceAndScores> faceAndScore = new ObjectMapper().readValue(json, new TypeReference<List<FaceAndScores>>(){});
		System.out.println( faceAndScore );
		return faceAndScore;
	}

	private String json = "[\n" + 
			"  {\n" + 
			"    \"faceRectangle\": {\n" + 
			"      \"left\": 479,\n" + 
			"      \"top\": 190,\n" + 
			"      \"width\": 158,\n" + 
			"      \"height\": 158\n" + 
			"    },\n" + 
			"    \"scores\": {\n" + 
			"      \"anger\": 0.00001619889,\n" + 
			"      \"contempt\": 0.000121588469,\n" + 
			"      \"disgust\": 0.0000216889184,\n" + 
			"      \"fear\": 0.00138592813,\n" + 
			"      \"happiness\": 0.00001577913,\n" + 
			"      \"neutral\": 0.002224847,\n" + 
			"      \"sadness\": 0.00000300440252,\n" + 
			"      \"surprise\": 0.996211\n" + 
			"    }\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"faceRectangle\": {\n" + 
			"      \"left\": 289,\n" + 
			"      \"top\": 209,\n" + 
			"      \"width\": 117,\n" + 
			"      \"height\": 117\n" + 
			"    },\n" + 
			"    \"scores\": {\n" + 
			"      \"anger\": 0.0003364322,\n" + 
			"      \"contempt\": 0.008513732,\n" + 
			"      \"disgust\": 0.000237169676,\n" + 
			"      \"fear\": 0.001399079,\n" + 
			"      \"happiness\": 0.03209325,\n" + 
			"      \"neutral\": 0.1274486,\n" + 
			"      \"sadness\": 0.000152969456,\n" + 
			"      \"surprise\": 0.8298188\n" + 
			"    }\n" + 
			"  }\n" + 
			"]";
	
}
