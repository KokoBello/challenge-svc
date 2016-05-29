package se.alphadev.emotion.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.springframework.stereotype.Component;

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
}
