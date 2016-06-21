package se.alphadev.emotion.api;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "faceRectangle", "scores" })
public class FaceAndScores {

	@JsonProperty("faceRectangle")
	public FaceRectangle faceRectangle;
	@JsonProperty("scores")
	public Scores scores;

	public FaceAndScores withFaceRectangle(FaceRectangle faceRectangle) {
		this.faceRectangle = faceRectangle;
		return this;
	}

	public FaceAndScores withScores(Scores scores) {
		this.scores = scores;
		return this;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}