package se.alphadev.emotion;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import se.alphadev.emotion.rest.ChallengeResource;

@Component
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		register( ChallengeResource.class );
		register( MultiPartFeature.class );
//		register( JacksonFeature.class );
	}
	
}
