package com.example.board.domain;

import java.util.List;

import com.example.board.util.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Table(name = "board")
@Entity
public class Board extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "board_id")
	private Long id;

	private String title;

	private String content;

	@OneToMany(mappedBy = "board")
	private List<Comment> comments;
}
