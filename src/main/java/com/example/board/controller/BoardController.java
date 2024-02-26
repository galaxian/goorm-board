package com.example.board.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.board.dto.CreateBoardReqDto;
import com.example.board.dto.UpdateBoardReqDto;
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
		return ApiResponse.successCreateResponse(boardId);
	}

	@DeleteMapping("/api/boards/{boardId}")
	public ApiResponse<Void> deleteBoard(@PathVariable("boardId") Long boardId) {
		boardService.deleteBoard(boardId);
		return ApiResponse.successDeleteResponse();
	}

	@PutMapping("/api/boards/{boardId}")
	public ApiResponse<Long> updateBoard(@PathVariable("boardId") Long boardId, @RequestBody UpdateBoardReqDto reqDto) {
		Long id = boardService.updateBoard(boardId, reqDto);
		return ApiResponse.successResponse(id);
	}
}
