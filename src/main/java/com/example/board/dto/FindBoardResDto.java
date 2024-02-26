package com.example.board.dto;

import java.util.List;

public record FindBoardResDto(Long boardId, String title, String content, List<CommentResDto> commentList) {
}
