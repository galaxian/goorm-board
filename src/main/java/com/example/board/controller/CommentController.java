package com.example.board.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.board.dto.CreateCommentReqDto;
import com.example.board.dto.UpdateCommentReqDto;
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

	@PutMapping("api/comments/{boardId}/{commentId}")
	public ApiResponse<Long> updateComment(@PathVariable("boardId") Long boardId,
		@PathVariable("commentId") Long commentId, @RequestBody
		UpdateCommentReqDto reqDto) {
		Long data = commentService.updateComment(boardId, commentId, reqDto);
		return ApiResponse.successResponse(data);
	}

	@DeleteMapping("api/comments/{boardId}/{commentId}")
	public ApiResponse<Void> deleteComment(@PathVariable("boardId") Long boardId,
		@PathVariable("commentId") Long commentId) {
		commentService.deleteComment(boardId, commentId);
		return ApiResponse.successDeleteResponse();
	}
}
