package se.alphadev.emotion.rest;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.imageio.ImageIO;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import se.alphadev.emotion.api.Challenge;
import se.alphadev.emotion.api.FaceAndScores;
import se.alphadev.emotion.api.FaceRectangle;
import se.alphadev.emotion.core.ChallengeService;
import se.alphadev.emotion.core.ImageDecoratorClient;

@Component
@Path("/challenge")
public class ChallengeResource {
	
	@Autowired
	private ChallengeService challengeService;
	@Autowired
	private ImageDecoratorClient imageDecoratorClient;

	@GET
	public String get() {
		System.out.println("get");
		return "Challenge resource up and running!";
	}
	
	@POST
	@Path("/{challenge}")
	@Consumes({MediaType.MULTIPART_FORM_DATA})
//	@Consumes({MediaType.MULTIPART_FORM_DATA, MediaType.APPLICATION_JSON})
	@Produces({"image/png","image/gif","image/jpeg"})
	public BufferedImage challenge(
			@PathParam("challenge") Challenge challenge,
			@FormDataParam("json") List<FaceAndScores> faceAndScores,
//			@FormDataParam("file") BufferedReader image 
			@FormDataParam("file") InputStream stream,
			@FormDataParam("file") FormDataContentDisposition fileDisposition) throws IOException {

		FaceRectangle winner = challengeService.evaluate(challenge, faceAndScores);
		BufferedImage image = ImageIO.read( stream );
		image = imageDecoratorClient.decorate( winner, image, challenge );
		
		//save to location
		
		//reply location;
		
		return image;
	}
	
	
	//https://www.kerstner.at/en/2013/08/serving-files-using-jersey-web-service-jax-rs/
	@GET
	@Path("/get-image1")
	@Produces({"image/png","image/gif","image/jpeg"})
	public BufferedImage getImage() throws FileNotFoundException, IOException {
		System.out.println("getImage");
		return ImageIO.read( ResourceUtils.getFile("classpath:recognition4.jpg") );
	}

	@GET
	@Path("/get-image2")
	@Produces("image/jpeg")
//	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response getImage2() {
		
	    StreamingOutput stream = new StreamingOutput() {
	      @Override
	      public void write(OutputStream output) throws IOException {
	        try {
	        	BufferedImage image = ImageIO.read( ResourceUtils.getFile("classpath:recognition4.jpg") );
	        	ImageIO.write( image, "jpg", output );
	        } catch (IOException e) {
	           e.printStackTrace();
	        }
	      }
	    };

	    return Response.ok(stream, "image/jpg")
//	            .header("content-disposition", "attachment; filename = "+ fileName)
	            .build();
    }

}
