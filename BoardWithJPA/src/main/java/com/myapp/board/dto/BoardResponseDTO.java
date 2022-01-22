package com.myapp.board.dto;

import java.time.LocalDateTime;

import com.myapp.board.entity.Board;

import lombok.Getter;

/**
 * 응답(Response) DTO 클래스
 * @author dowonlee
 *
 */
@Getter
public class BoardResponseDTO {
	
	private Long num;
	private String title;
	private String content;
	private String writer;
	private char noticeYn;
	private char secretYn;
	private char deleteYn;
	private int hits;
	private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;
	
	public BoardResponseDTO(Board entity) {
		this.num = entity.getNum();
		this.title = entity.getTitle();
		this.content = entity.getContent();
		this.writer = entity.getWriter();
		this.noticeYn = entity.getNoticeYn();
		this.secretYn = entity.getSecretYn();
		this.deleteYn = entity.getDeleteYn();
		this.hits = entity.getHits();
		this.createdDate = entity.getCreatedDate();
		this.modifiedDate = entity.getModifiedDate();
	}	
	
}
