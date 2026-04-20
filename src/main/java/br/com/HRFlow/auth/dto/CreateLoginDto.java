package br.com.HRFlow.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CreateLoginDto(
    @NotBlank(message = "Campo obrigatório")
    String nome,
    @Email(message = "Campo obrigatório")
    String email,
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[\\W_]).{8,}$",
            message = "Senha deve ter no mínimo 8 caracteres, incluindo maiúscula, minúscula, número e símbolo"
    )
    String senha,
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[\\W_]).{8,}$",
            message = "Senha deve ter no mínimo 8 caracteres, incluindo maiúscula, minúscula, número e símbolo"
    )
    String confirmarSenha){
}
