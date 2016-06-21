package se.alphadev.emotion.api;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "left", "top", "width", "height" })
public class FaceRectangle {

	@JsonProperty("left")
	public Integer left;
	@JsonProperty("top")
	public Integer top;
	@JsonProperty("width")
	public Integer width;
	@JsonProperty("height")
	public Integer height;

	public FaceRectangle withLeft(Integer left) {
		this.left = left;
		return this;
	}

	public FaceRectangle withTop(Integer top) {
		this.top = top;
		return this;
	}

	public FaceRectangle withWidth(Integer width) {
		this.width = width;
		return this;
	}

	public FaceRectangle withHeight(Integer height) {
		this.height = height;
		return this;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}