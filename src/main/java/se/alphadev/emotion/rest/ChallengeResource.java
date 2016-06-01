package se.alphadev.emotion.rest;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import se.alphadev.emotion.api.Challenge;
import se.alphadev.emotion.core.ChallengeService;

@Component
@Path("/challenge")
public class ChallengeResource {
	
	@Autowired
	private ChallengeService challengeService;

	@GET
	public String get() {
		return "Challenge resource up and running!";
	}
	
	@GET
	@Path("/{challenge}")
	public String evaluate(@PathParam("challenge") Challenge challenge) {
		return challenge.toString();

//		FaceRectangle winner = challengeService.evaluate(challenge, facesAndScore);
//		image = imgDecoratorService.process(winner, image, Faces.)
		//save to location
		//reply location;
	}
	
	
	
	@GET
	@Path("/get-image")
//	@Produces({"image/png","image/gif","image/jpg"})
	@Produces("image/png")
	public BufferedImage getImage() throws FileNotFoundException, IOException {
		return ImageIO.read( ResourceUtils.getFile("classpath:recognition4.jpg") );
	}

	@GET
	@Path("/get-image2")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
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
	
	@GET
	@Path("/get-image-text")
	@Produces({"image/png","image/gif","image/jpg"})
	public String getImageText() {
		return "Image text";
	}
}
