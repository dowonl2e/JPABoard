package com.myapp.board.entity;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BoardRepository extends JpaRepository<Board, Long>, JpaSpecificationExecutor<Board> {
	
	/**
	 * 게시글 리스트 조회 -> 삭제 여부 기준
	 */
	List<Board> findAllByDeleteYn(final char deleteYn, final Sort sort);
	
	/**
	 * 게시글 리스트 조회 -> 삭제 여부 기준, 페이징
	 */
	List<Board> findAllByDeleteYn(final char deleteYn, final Pageable pageable);
}
