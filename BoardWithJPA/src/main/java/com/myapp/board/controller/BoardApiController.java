package com.myapp.board.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.board.dto.BoardRequestDTO;
import com.myapp.board.dto.BoardResponseDTO;
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
	
	@GetMapping("/boards")
	public List<BoardResponseDTO> findAll(){
		return boardService.findAll();
	}
	
	@PatchMapping("/boards/{num}")
	public Long save(@PathVariable final Long num, @RequestBody final BoardRequestDTO params) {
		return boardService.update(num, params);
	}
}
