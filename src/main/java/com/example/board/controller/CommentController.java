package com.example.board.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.board.dto.CreateCommentReqDto;
import com.example.board.service.CommentService;
import com.example.board.util.ApiResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class CommentController {

	private final CommentService commentService;

	@PostMapping("api/comments/{boardId}")
	public ApiResponse<Long> createComment(@PathVariable("boardId") Long boardId,
		@RequestBody CreateCommentReqDto reqDto) {
		Long data = commentService.createComment(boardId, reqDto);
		return ApiResponse.successCreateResponse(data);
	}
}
