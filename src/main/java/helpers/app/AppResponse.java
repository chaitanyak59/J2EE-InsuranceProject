package helpers.app;

public class AppResponse<T> {
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
