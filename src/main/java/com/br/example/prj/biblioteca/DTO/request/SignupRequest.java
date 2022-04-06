package com.br.example.prj.biblioteca.DTO.request;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequest {
        
    //criar as messages no properties
    @NotBlank
    @Size(min = 3, max = 20)
    private String rg;

    @NotBlank
    @Size(max = 50)
    @Email
    private String username;

    private Set<String> role;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

}
