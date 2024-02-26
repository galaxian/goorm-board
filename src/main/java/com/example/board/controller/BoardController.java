package com.example.board.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.board.dto.CreateBoardReqDto;
import com.example.board.dto.FindAllBoardResDto;
import com.example.board.dto.FindBoardResDto;
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

	@GetMapping("/api/boards")
	public ApiResponse<List<FindAllBoardResDto>> findAllBoard(
		@RequestParam(required = false, defaultValue = "0", value = "page") int page,
		@RequestParam(required = false, defaultValue = "2", value = "size") int size) {
		List<FindAllBoardResDto> data = boardService.findAllBoard(page, size);
		return ApiResponse.successResponse(data);
	}

	@GetMapping("/api/boards/{boardId}")
	public ApiResponse<FindBoardResDto> findBoard(@PathVariable("boardId") Long boardId) {
		FindBoardResDto data = boardService.findBoard(boardId);
		return ApiResponse.successResponse(data);
	}
}
