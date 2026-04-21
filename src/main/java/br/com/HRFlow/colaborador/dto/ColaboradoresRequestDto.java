package br.com.HRFlow.colaborador.dto;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;

public record ColaboradoresRequestDto(
        @NotBlank(message = "Campo obrigatório")
        String nome,
        @NotBlank(message = "Campo obrigatório")
        String email,
        String cargo,
        String departamento,
        LocalDate dataAdimissao,
        Long gestorId
) {
}
