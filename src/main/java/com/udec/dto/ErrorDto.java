package com.udec.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ErrorDto  implements Serializable  {
	
	private static final long serialVersionUID = 1L;

private LocalDateTime timestamp;
	
	private String status;
	
	private int error;
	
	private String message;
	
	private String path;

	public ErrorDto(String status, int error, String message, String path) {
		super();
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
		this.timestamp = LocalDateTime.now();
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getError() {
		return error;
	}

	public void setError(int error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
