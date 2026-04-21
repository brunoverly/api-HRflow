package br.com.HRFlow.colaborador.dto;

import br.com.HRFlow.colaborador.Colaborador;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record ColaboradoresResponseDto(
        Long id,
        String nome,
        String email,
        String cargo,
        String departamento,
        LocalDate dataAdimissao,
        ColaboradoresResumoDto gestor
) {
}
