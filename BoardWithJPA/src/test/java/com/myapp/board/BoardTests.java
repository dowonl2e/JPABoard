package com.myapp.board;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;

import com.myapp.board.common.QueryOperator;
import com.myapp.board.dto.SearchDTO;
import com.myapp.board.entity.Board;
import com.myapp.board.entity.BoardRepository;
import com.myapp.board.model.BoardService;

@SpringBootTest
public class BoardTests {

	@Autowired
	BoardRepository boardRepository;
	
	@Autowired
	BoardService boardService;
	
	@Test
	void save() {
		
		/*
		 * 게시글 파라미터 설정
		 * -> Entity 객체를 인자로 전달하여 게시글을 생성하는 방식
		 * -> Entity 클래스는 요청(Request)에 사용되어서는 안됨!
		 */
		/*
		Board params = Board.builder()
				.title("JPA 3번 제목")
				.content("JPA 3번 내용")
				.writer("JPA 게시자")
				.hits(0)
				.noticeYn('N')
				.secretYn('Y')
				.deleteYn('N')
				.build();
		//게시글 저장
		boardRepository.save(params);
		*/
		
		//게시글 조회
		Board entity = boardRepository.findById((long)3).get();
		assertThat(entity.getTitle()).isEqualTo("JPA 2번 제목");
		assertThat(entity.getContent()).isEqualTo("JPA 2번 내용");
		assertThat(entity.getWriter()).isEqualTo("JPA 게시자");
	}
	
	@Test
	void findAll() {
		
		//게시글 전체 수 조회
		long boardsCount = boardRepository.count();
		
		//게시글 전체 조회
		List<Board> boards = boardRepository.findAll();
	}
	
	@Test
	void findAllWithSearch() {
		//게시물 조건 파라미터 값 추가
		final SearchDTO params = new SearchDTO();
		params.setOperator(QueryOperator.LIKE);
		params.setKeyword("테스터");
		
		//페이지 사이즈
		Pageable pageable = Pageable.ofSize(20);
		
		//게시글 전체 조회
		Map<String, Object> response = boardService.findAll(params, pageable);
	}
	
	
	@Test
	void delete() {
		
		//게시글 조회
		Board entity = boardRepository.findById((long)1).get();
		
		boardRepository.delete(entity);
		
	}
}
