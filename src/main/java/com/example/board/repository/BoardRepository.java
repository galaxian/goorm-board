package com.example.board.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.board.domain.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
	Page<Board> findById(Long boardId, Pageable pageable);
}
