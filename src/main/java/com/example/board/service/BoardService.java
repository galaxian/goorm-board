package com.example.board.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.board.domain.Board;
import com.example.board.dto.CreateBoardReqDto;
import com.example.board.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardService {

	private final BoardRepository boardRepository;

	@Transactional
	public Long createBoard(CreateBoardReqDto reqDto) {
		Board board = new Board(reqDto.title(), reqDto.content());
		Board saveBoard = boardRepository.save(board);
		return saveBoard.getId();
	}
}
