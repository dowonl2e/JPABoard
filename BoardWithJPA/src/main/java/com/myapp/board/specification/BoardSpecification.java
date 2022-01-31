package com.myapp.board.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;

import com.myapp.board.dto.SearchDTO;
import com.myapp.board.entity.Board;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BoardSpecification {

	
	public static Specification<Board> searchWith(SearchDTO params){
		return (Specification<Board>) ((root, query, builder) -> {
			List<Predicate> predicate = getPredicateWithSearchtitle(params, root, builder);
			/*
			 * 1. 여기서 or로 조건을 지정하면 다음에 오는 조건을 and로 묶어서 실행하려해도 or로 붙는다.
			 * 2. 여기서 and로 조건을 지정하면 다음에 오는 조건을 or로 묶어서 실행하려해도 and로 붙는다.
			 */
			return builder.and(predicate.toArray(new Predicate[0]));
		});
	}
	
	public static List<Predicate> getPredicateWithSearchtitle(SearchDTO params, Root<Board> root, CriteriaBuilder builder){
		List<Predicate> predicatelist = new ArrayList<Predicate>();
		
		predicatelist.add(builder.equal(root.get("deleteYn"), (ObjectUtils.isEmpty(params.getDeleteYn()) ? 'N' : params.getDeleteYn().charAt(0))));
				
		if(ObjectUtils.isEmpty(params.getKeyword()) == false) {
			if(ObjectUtils.isEmpty(params.getSearchType())) {
				predicatelist.add(builder.like(root.get("title"), "%"+params.getKeyword()+"%"));
				predicatelist.add(
					builder.or(
						builder.like(root.get("title"), "%"+params.getKeyword()+"%"),
						builder.like(root.get("content"), "%"+params.getKeyword()+"%"),
						builder.like(root.get("writer"), "%"+params.getKeyword()+"%")
					)
				);
			}
			else {
				if(params.getSearchType().equals("title"))
					predicatelist.add(builder.and(builder.like(root.get("title"), "%"+params.getKeyword()+"%")));
				else if(params.getSearchType().equals("content"))
					predicatelist.add(builder.and(builder.like(root.get("content"), "%"+params.getKeyword()+"%")));
				else if(params.getSearchType().equals("writer"))
					predicatelist.add(builder.and(builder.like(root.get("writer"), "%"+params.getKeyword()+"%")));
			}
		}
		
		return predicatelist;
	}
}
