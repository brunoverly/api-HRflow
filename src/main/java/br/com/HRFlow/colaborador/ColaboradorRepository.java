package br.com.HRFlow.colaborador;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ColaboradorRepository extends JpaRepository<Colaborador, Long> {
    boolean existsByEmail(String email);

    Optional<Colaborador> findByIdAndAtivoTrue(Long id);
}
