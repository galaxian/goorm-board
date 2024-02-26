package com.example.board.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.board.domain.Board;
import com.example.board.dto.CreateBoardReqDto;
import com.example.board.dto.FindAllBoardReqDto;
import com.example.board.dto.UpdateBoardReqDto;
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

	@Transactional
	public void deleteBoard(Long boardId) {
		boardRepository.deleteById(boardId);
	}

	@Transactional
	public Long updateBoard(Long boardId, UpdateBoardReqDto reqDto) {
		Board findBoard = boardRepository.findById(boardId).orElseThrow(
			() -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
		);

		findBoard.updateBoard(reqDto.title(), reqDto.content());

		return findBoard.getId();
	}

	@Transactional(readOnly = true)
	public List<FindAllBoardReqDto> findAllBoard() {
		List<Board> boardList = boardRepository.findAll();
		return boardList.stream()
			.map(b -> new FindAllBoardReqDto(b.getId(), b.getTitle(), b.getContent()))
			.collect(Collectors.toList());
	}
}
