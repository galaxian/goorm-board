package com.example.board.domain;

import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.Where;

import com.example.board.util.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "board")
@SQLDelete(sql = "update board set is_delete = true where board_id = ?")
@SQLRestriction("is_delete is false")
@Entity
public class Board extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "board_id")
	private Long id;

	private String title;

	private String content;

	private boolean isDelete = false;

	@OneToMany(mappedBy = "board")
	private List<Comment> comments;

	public Board(String title, String content) {
		this.title = title;
		this.content = content;
	}

	public void updateBoard(String title, String content) {
		this.title = title;
		this.content = content;
	}
}
