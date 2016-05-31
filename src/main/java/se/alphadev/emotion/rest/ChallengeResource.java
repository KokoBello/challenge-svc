package se.alphadev.emotion.rest;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import se.alphadev.emotion.api.Challenge;

@Component
@Path("/challenge")
public class ChallengeResource {

	@GET
	public String get() {
		return "Challenge resource up and running!";
	}
	
	@GET
	@Path("/{challenge}")
	public String evaluate(@PathParam("challenge") Challenge challenge ) {
		return challenge.toString();
	}
	
	@GET
	@Path("/get-image")
	@Produces({"image/png","image/gif","image/jpg"})
	public BufferedImage getImage() throws FileNotFoundException, IOException {
		return ImageIO.read( ResourceUtils.getFile("classpath:recognition4.jpg") );
	}
	
	@GET
	@Path("/get-image-text")
	@Produces({"image/png","image/gif","image/jpg"})
	public String getImageText() {
		return "Image text";
	}
}
