package se.alphadev.emotion.api;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "anger", "contempt", "disgust", "fear", "happiness", "neutral", "sadness", "surprise" })
public class Scores {

	@JsonProperty("anger")
	public Double anger;
	@JsonProperty("contempt")
	public Double contempt;
	@JsonProperty("disgust")
	public Double disgust;
	@JsonProperty("fear")
	public Double fear;
	@JsonProperty("happiness")
	public Double happiness;
	@JsonProperty("neutral")
	public Double neutral;
	@JsonProperty("sadness")
	public Double sadness;
	@JsonProperty("surprise")
	public Double surprise;

	public Scores withAnger(Double anger) {
		this.anger = anger;
		return this;
	}

	public Scores withContempt(Double contempt) {
		this.contempt = contempt;
		return this;
	}

	public Scores withDisgust(Double disgust) {
		this.disgust = disgust;
		return this;
	}

	public Scores withFear(Double fear) {
		this.fear = fear;
		return this;
	}

	public Scores withHappiness(Double happiness) {
		this.happiness = happiness;
		return this;
	}

	public Scores withNeutral(Double neutral) {
		this.neutral = neutral;
		return this;
	}

	public Scores withSadness(Double sadness) {
		this.sadness = sadness;
		return this;
	}

	public Scores withSurprise(Double surprise) {
		this.surprise = surprise;
		return this;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}