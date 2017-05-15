package org.sid.entities;

public class Error {

	private String title;
	private Long status;
	private Long timestamp;
	private String url;
	private String message;

	public Error() {
		super();
	}

	public Error(String title, Long status, Long timestamp, String url, String message) {
		super();
		this.title = title;
		this.status = status;
		this.timestamp = timestamp;
		this.url = url;
		this.message = message;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
