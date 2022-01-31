package com.myapp.board.controller;

import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.data.web.SortDefault.SortDefaults;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.board.dto.BoardRequestDTO;
import com.myapp.board.dto.BoardResponseDTO;
import com.myapp.board.dto.SearchDTO;
import com.myapp.board.exception.CustomException;
import com.myapp.board.exception.ErrorCode;
import com.myapp.board.model.BoardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardApiController {
	
	private final BoardService boardService;
	
	//전역 예외처리 핸들링 테스트
	@GetMapping("/test")
	public String test() {
        throw new CustomException(ErrorCode.METHOD_NOT_ALLOWED);
	}
	
	@PostMapping("/boards")
	public Long save(@RequestBody final BoardRequestDTO params) {
		return boardService.save(params);
	}
	
	/**
	 * Pageable 인터페이스를 이용한 리스트 페이징 
	 * @param deleteYn
	 * @param pageable
	 * @return
	 */
	/*
	@GetMapping("/boards")
	public List<BoardResponseDTO> findAll(@RequestParam final char deleteYn
			, @PageableDefault(page = 1, size = 20) @SortDefaults({
				@SortDefault(sort = "num", direction = Direction.DESC),
				@SortDefault(sort = "createdDate", direction = Direction.DESC)
			}) final Pageable pageable){
		//삭제 키 있는 케이스의 경우 페이저블 처리
		//return boardService.findAllByDeleteYn(deleteYn, pageable)
	}
	*/
	/**
	 * 게시물 리스트 조회 -> 검색조건 + 페이징
	 * @param params
	 * @param pageable
	 * @return
	 */
	@GetMapping("/boards")
	public Map<String, Object> findAll(final SearchDTO params
			, @PageableDefault(page = 0, size = 10) @SortDefaults({
				@SortDefault(sort = "num", direction = Direction.DESC),
				@SortDefault(sort = "createdDate", direction = Direction.DESC)
			}) final Pageable pageable){
		return boardService.findAll(params, pageable);
	}
	
	@GetMapping("/boards/{num}")
	public BoardResponseDTO findById(@PathVariable final Long num) {
		return boardService.findbyId(num);
	}

	
	@PatchMapping("/boards/{num}")
	public Long update(@PathVariable final Long num, @RequestBody final BoardRequestDTO params) {
		return boardService.update(num, params);
	}
	
	@DeleteMapping("/boards/{num}")
	public Long delete(@PathVariable final Long num) {
		return boardService.delete(num);
	}
}
