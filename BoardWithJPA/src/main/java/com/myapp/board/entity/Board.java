package com.myapp.board.entity;


import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Board Entity 클래스
 * @author dowonlee
 *
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) //기본 생성자 생성
@Entity //클래스와 테이블간의 매핑 JPA 엔티티
public class Board {
	
	@Id //해당 엔티티의 PK을 의미
	@GeneratedValue(strategy = GenerationType.IDENTITY) //PK 생성 전략 설정
	private Long num; //번호 
	
	private String title; //제목 
	
	private String content; //내용 
	
	private String writer; //작성
	
	private int hits; //조회수 
	
	private char noticeYn; //공지 여부 
	
	private char secretYn; //비밀글 여부
	
	private char deleteYn; //삭제 여부 

	private LocalDateTime createdDate = LocalDateTime.now(); //등록일 

	private LocalDateTime modifiedDate; //수정일 
	
	/**
	 * Lombok 제공 빌더
	 * @param title - 게시물 제목
	 * @param content - 게시물 내용
	 * @param writer - 게시물 작성자
	 * @param visitCnt - 게시물 조회수
	 * @param deleteYn - 게시물 삭제여부
	 */
	@Builder
	public Board(String title, String content, String writer, int hits, char noticeYn, char secretYn, char deleteYn) {
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.hits = hits;
		this.noticeYn = noticeYn;
		this.secretYn = secretYn;
		this.deleteYn = deleteYn;
	}
	
	/*
	 * 게시글 수정용 메서드
	 */
	public void update(String title, String content, String writer, char noticeYn, char secretYn) {
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.noticeYn = noticeYn;
		this.secretYn = secretYn;
	}
	
}
