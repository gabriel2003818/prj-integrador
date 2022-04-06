package com.br.example.prj.biblioteca.DTO.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ResponseCreateBookDto {

	@JsonInclude(Include.NON_NULL)
	private String title;
	@JsonInclude(Include.NON_NULL)
	private String status;
	@JsonInclude(Include.NON_NULL)
	private String category;
	@JsonInclude(Include.NON_NULL)
	private Integer quantity;
}
