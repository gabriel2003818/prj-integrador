package com.br.example.prj.biblioteca.models.Enum;

import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum CategoryEnum {

	INFANTO_JUVENIL_ATUAL(1), BIOGRAFIA(2), CURIOSIDADES(3);

	private Integer code;

	private CategoryEnum(Integer code) {
		this.code = code;
	}

	@JsonCreator
	public static CategoryEnum decode(final Integer code) {
		return Stream.of(CategoryEnum.values()).filter(targetEnum -> targetEnum.code.equals(code)).findFirst()
				.orElse(null);
	}

	@JsonValue
	public Integer getCode() {
		return code;
	}

}
