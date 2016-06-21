package se.alphadev.emotion.rest;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.imageio.ImageIO;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
	
	@POST
	@Path("/{challenge}")
	@Consumes({MediaType.MULTIPART_FORM_DATA})
	@Produces({"image/png","image/gif","image/jpeg"})
	public BufferedImage challenge(
			@PathParam("challenge") Challenge challenge,
			@FormDataParam("json") List<FaceAndScores> faceAndScores,
			@FormDataParam("file") InputStream stream,
			@FormDataParam("file") FormDataContentDisposition fileDisposition) throws IOException {

		FaceRectangle winner = challengeService.evaluate(challenge, faceAndScores);
		BufferedImage image = ImageIO.read( stream );
		image = imageDecoratorClient.decorate( winner, image, challenge );

		return image;
	}

}
