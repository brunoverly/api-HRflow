package br.com.HRFlow.auth.dto;

public record LoginResonseDto(
        String nome,
        String email,
        String token) {
}
