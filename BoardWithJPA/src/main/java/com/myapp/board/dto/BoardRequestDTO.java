package com.myapp.board.dto;

import com.myapp.board.entity.Board;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 요청(Request) DTO 클래스
 * @author dowonlee
 *
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class BoardRequestDTO extends SearchDTO {
	
	private String title;
	private String content;
	private String writer;
	private char noticeYn;
	private char secretYn;
	private char deleteYn;
	
	public Board toEntity() {
		return Board.builder()
				.title(title)
				.content(content)
				.writer(writer)
				.noticeYn(noticeYn)
				.secretYn(secretYn)
				.deleteYn(deleteYn)
				.hits(0)
				.build();
	}
}
