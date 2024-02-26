package com.example.board.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.board.dto.CreateBoardReqDto;
import com.example.board.service.BoardService;
import com.example.board.util.ApiResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BoardController {

	private final BoardService boardService;

	@PostMapping("/api/boards")
	public ApiResponse<Long> createBoard(@RequestBody CreateBoardReqDto reqDto) {
		Long boardId = boardService.createBoard(reqDto);
		return makeApiResponse(boardId);
	}

	private <T> ApiResponse<T> makeApiResponse(T responseDto) {
		return ApiResponse.successResponse(responseDto);
	}
}
