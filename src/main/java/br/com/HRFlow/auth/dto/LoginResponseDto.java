package br.com.HRFlow.auth.dto;

public record LoginResponseDto(
        String nome,
        String email,
        String token) {
}
