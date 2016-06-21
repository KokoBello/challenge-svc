package se.alphadev.emotion.core;

public class ChallengeServiceException extends RuntimeException {

	public ChallengeServiceException() {
		super();
	}

	public ChallengeServiceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ChallengeServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ChallengeServiceException(String message) {
		super(message);
	}

	public ChallengeServiceException(Throwable cause) {
		super(cause);
	}

}
