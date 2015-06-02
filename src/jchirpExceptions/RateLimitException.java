package jchirpExceptions;

public class RateLimitException extends Throwable{
	
	private static final long serialVersionUID = -7326694561535932381L;

	public RateLimitException() {
		super();
		System.out.println("thrown");
	}

	public RateLimitException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public RateLimitException(String message, Throwable cause) {
		super(message, cause);
	}

	public RateLimitException(String message) {
		super(message);
	}

	public RateLimitException(Throwable cause) {
		super(cause);
	}
}
