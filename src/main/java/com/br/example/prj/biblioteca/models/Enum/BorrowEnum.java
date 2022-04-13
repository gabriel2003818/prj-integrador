package com.br.example.prj.biblioteca.models.Enum;

import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonCreator;

import lombok.Getter;

@Getter	
public enum BorrowEnum {

	EMPRESTADO(1), ATRASADO(2), DISPONIVEL(3);

	private Integer code;

	private BorrowEnum(Integer code) {
		this.code = code;
	}

	@JsonCreator
	public static BorrowEnum decode(final Integer code) {
		return Stream.of(BorrowEnum.values()).filter(targetEnum -> targetEnum.code.equals(code)).findFirst()
				.orElse(null);
	}


}
