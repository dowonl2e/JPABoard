package com.myapp.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/board")
public class BoardPageController {
	
	@GetMapping("/list")
	public String boardList() {
		return "board/list";
	}
	
	@GetMapping("/write")
	public String boardWrite() {
		return "board/write";
	}
	
	@GetMapping("/view/{num}")
	public String boardView(@PathVariable final Long num, Model model) {
		model.addAttribute("num", num);
		return "board/view";
	}
	
	@GetMapping("/modify")
	public String boardModify(@RequestParam(required = true) Long num, Model model) {
		model.addAttribute("num", num);
		return "board/modify";
	}
}
