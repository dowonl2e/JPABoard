package com.myapp.board.model;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.myapp.board.dto.BoardRequestDTO;
import com.myapp.board.dto.BoardResponseDTO;
import com.myapp.board.entity.Board;
import com.myapp.board.entity.BoardRepository;
import com.myapp.board.exception.CustomException;
import com.myapp.board.exception.ErrorCode;
import com.myapp.board.specification.BoardSpecification;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	
	private final BoardRepository boardRepository;
	
	/**
	 * 게시글 생성
	 */
	@Transactional
	public Long save(final BoardRequestDTO params) {
		Board entity = boardRepository.save(params.toEntity());
		return entity.getNum(); //생성된 num(PK)를 반환한다.
	}
	
	/**
	 * 게시글 리스트 조회
	 */
	public List<BoardResponseDTO> findAll(){
		//num desc, created_date desc를 의미한다.
		Sort sort = Sort.by(Direction.DESC, "num", "createdDate");
		List<Board> list = boardRepository.findAll(sort);
		
		/*
		 * JAVA Stream API를 사용하지 않고 리스트를 반
		 */
		/*
		List<BoardResponseDTO> boardList = new ArrayList<>();
		
		for(Board entity : list) {
			boardList.add(new BoardResponseDTO(entity));
		}
		return boardList;
		*/
		
		/*
		 * JAVA Stream API를 이용하여 리스트 반환
		 * list 변수에는 게시글 Entity가 담겨 있고, 각각의 Entity를 BoardResponseDTO로 변경(생성)해서 반환해야한다.
		 * Stream 
		 */
		return list.stream().map(BoardResponseDTO::new).collect(Collectors.toList());
	}
	
	/**
	 * 게시글 리스트 조회 -> 삭제 여부 기준
	 */
	public List<BoardResponseDTO> findAllByDeleteYn(final char deleteYn){
		Sort sort = Sort.by(Direction.DESC, "num", "createdDate");
		List<Board> list = boardRepository.findAllByDeleteYn(deleteYn, sort);
		return list.stream().map(BoardResponseDTO::new).collect(Collectors.toList());
	}
	
	/**
	 * 게시글 리스트 조회 -> 삭제 여부 + 페이징
	 */
	public List<BoardResponseDTO> findAllByDeleteYn(final char deleteYn, final Pageable pageable){
		List<Board> list = boardRepository.findAllByDeleteYn(deleteYn, pageable);
		return list.stream().map(BoardResponseDTO::new).collect(Collectors.toList());
	}
	
	/**
	 * 게시글 리스트 조회 -> 검색조건 + 페이징
	 */
	public List<BoardResponseDTO> findAll(final BoardRequestDTO params, final Pageable pageable){
		Page<Board> list = ObjectUtils.isEmpty(params.getOperator()) 
				? boardRepository.findAll(pageable) 
						: boardRepository.findAll(BoardSpecification.searchWith(params), pageable);
		return list.getContent().stream().map(BoardResponseDTO::new).collect(Collectors.toList());
		//return list.stream().map(BoardResponseDTO::new).collect(Collectors.toList());
	}
	
	/**
	 * 게시글 수정
	 */
	@Transactional
	public Long update(final Long num, final BoardRequestDTO params) {
		Board entity = boardRepository.findById(num).orElseThrow(() -> new CustomException(ErrorCode.POSTS_NOT_FOUND));

		/*
		 * 위 코드를 풀어서 나타내면 아래와 같음
		 */
		/*
		Board entity2 = boardRepository.findById(num).get();
		if(entity2 == null) {
			throw new CustomException(ErrorCode.POSTS_NOT_FOUND);
		}
		*/
		
		entity.update(params.getTitle(), params.getContent(), params.getWriter(), params.getNoticeYn(), params.getSecretYn());
		return num;
	}
	
	/**
	 * 게시글 조회수 증가
	 */
	@Transactional
	public BoardResponseDTO findbyId(Long num) {
		Board entity = boardRepository.findById(num).orElseThrow(() -> new CustomException(ErrorCode.POSTS_NOT_FOUND));
		entity.increaseHits();
		return new BoardResponseDTO(entity);
	}
	
	/**
	 * 게시물 삭제
	 */
	@Transactional
	public Long delete(Long num) {
		Board entity = boardRepository.findById(num).orElseThrow(() -> new CustomException(ErrorCode.POSTS_NOT_FOUND));
		entity.delete(num);
		return num;
	}
}
