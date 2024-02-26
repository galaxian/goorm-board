package com.example.board.util;

import lombok.Getter;

@Getter
public class ApiResponse<T> {

	private int code;
	private T data;
	private String message;

	public static <T> ApiResponse<T> successCreateResponse(T data) {
		return new ApiResponse<>(Status.CREATE, data, null);
	}

	public static ApiResponse<Void> successDeleteResponse() {
		return new ApiResponse<>(Status.OK, null, null);
	}

	private ApiResponse(Status status, T data, String message) {
		this.code = status.getStatusCode();
		this.data = data;
		this.message = message;
	}
}
