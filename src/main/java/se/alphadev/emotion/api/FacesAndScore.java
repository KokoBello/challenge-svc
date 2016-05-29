package se.alphadev.emotion.api;

import javax.annotation.Generated;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "faceRectangle", "scores" })
public class FacesAndScore {

	@JsonProperty("faceRectangle")
	public FaceRectangle faceRectangle;
	@JsonProperty("scores")
	public Scores scores;

	public FacesAndScore withFaceRectangle(FaceRectangle faceRectangle) {
		this.faceRectangle = faceRectangle;
		return this;
	}

	public FacesAndScore withScores(Scores scores) {
		this.scores = scores;
		return this;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}