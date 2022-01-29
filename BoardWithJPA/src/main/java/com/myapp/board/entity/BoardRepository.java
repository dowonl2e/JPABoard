package com.myapp.board.entity;

import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.util.ObjectUtils;

import com.myapp.board.dto.BoardRequestDTO;
import com.myapp.board.specification.BoardSpecification;

public interface BoardRepository extends JpaRepository<Board, Long>, JpaSpecificationExecutor<Board> {
	
	/**
	 * 게시글 리스트 조회 -> 삭제 여부
	 */
	List<Board> findAllByDeleteYn(final char deleteYn, final Sort sort);
	
	/**
	 * 게시글 리스트 조회 -> 삭제 여부 + 페이징
	 */
	List<Board> findAllByDeleteYn(final char deleteYn, final Pageable pageable);
	
}
