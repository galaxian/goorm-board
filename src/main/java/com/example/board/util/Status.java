package com.example.board.util;

import lombok.Getter;

@Getter
public enum Status {
	CREATE(201),
	OK(200);

	private final int statusCode;

	Status(int statusCode) {
		this.statusCode = statusCode;
	}
}
