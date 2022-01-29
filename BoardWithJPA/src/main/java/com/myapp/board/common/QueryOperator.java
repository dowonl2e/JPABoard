package com.myapp.board.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum QueryOperator {
	EQUALS("eq"),
	NOT_EQUALS("ne"),
	LIKE("LIKE"),
	NOT_LIKE("NOT_LIKE"),
	IN("IN"),
	NOT_IN("NOT_IN"),
	BETWEEN("BETWEEN"),
	GREATER("GREATER"),
	LESS("LESS");
	
	private final String oper;
	
}
