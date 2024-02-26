package com.example.board.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.board.domain.Board;
import com.example.board.dto.CommentResDto;
import com.example.board.dto.CreateBoardReqDto;
import com.example.board.dto.FindAllBoardResDto;
import com.example.board.dto.FindBoardResDto;
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
	public List<FindAllBoardResDto> findAllBoard(int page, int size) {
		Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
		Page<Board> boardList = boardRepository.findAll(pageable);
		return boardList.stream()
			.map(b -> new FindAllBoardResDto(b.getId(), b.getTitle()))
			.collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public FindBoardResDto findBoard(Long boardId) {

		Board findBoard = boardRepository.findById(boardId).orElseThrow(
			() -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
		);

		return new FindBoardResDto(findBoard.getId(), findBoard.getTitle(), findBoard.getContent(),
			findBoard.getComments()
				.stream()
				.map(c -> new CommentResDto(c.getId(), c.getContent()))
				.collect(Collectors.toList()));
	}
}
