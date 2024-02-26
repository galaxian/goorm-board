package com.example.board.service;

import org.springframework.stereotype.Service;

import com.example.board.domain.Board;
import com.example.board.domain.Comment;
import com.example.board.dto.CreateCommentReqDto;
import com.example.board.repository.BoardRepository;
import com.example.board.repository.CommentRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommentService {

	private final CommentRepository commentRepository;
	private final BoardRepository boardRepository;

	public Long createComment(Long boardId, CreateCommentReqDto reqDto) {
		Board findBoard = boardRepository.findById(boardId).orElseThrow(
			() -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
		);
		Comment comment = new Comment(reqDto.content(), findBoard);
		Comment saveComment = commentRepository.save(comment);
		return saveComment.getId();
	}
}
