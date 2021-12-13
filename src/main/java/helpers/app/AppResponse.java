package helpers.app;

import java.io.Serializable;

public class AppResponse<T> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private T payload;
	private boolean isError;

	public AppResponse(T payload, boolean isError) {
		this.payload = payload;
		this.isError = isError;
	}

	public T getPayload() {
		return payload;
	}

	public boolean hasError() {
		return isError;
	}
}
