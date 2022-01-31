package com.myapp.board.dto;

import com.myapp.board.common.QueryOperator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchDTO {
	
	private String deleteYn;
	private String keyword;
	private String searchType;
	private QueryOperator operator;
	
	private long totalCount;
	private long totalPage;
	private long pageNumber;
	private long size;
	private long numberOfElements;
	private boolean hasPrevPage;
	private boolean hasNextPage;
	
}
