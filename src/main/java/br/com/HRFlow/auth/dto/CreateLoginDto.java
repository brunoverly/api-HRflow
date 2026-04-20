package br.com.HRFlow.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CreateLoginDto(
    @NotBlank(message = "Campo obrigatório")
    String nome,
    @NotBlank(message = "Campo obrigatório")
    @Email(message = "Campo no formato de email")
    String email,
    @NotBlank(message = "Campo obrigatório")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[\\W_]).{8,}$",
            message = "Senha deve ter no mínimo 8 caracteres, incluindo maiúscula, minúscula, número e símbolo"
    )
    String senha,
    @NotBlank(message = "Campo obrigatório")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[\\W_]).{8,}$",
            message = "Senha deve ter no mínimo 8 caracteres, incluindo maiúscula, minúscula, número e símbolo"
    )
    String confirmarSenha){
}
