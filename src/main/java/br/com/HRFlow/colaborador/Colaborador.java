package br.com.HRFlow.colaborador;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "colaboradores")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Colaborador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String cargo;
    private String departamento;
    @Column(name = "data_admissao")
    private LocalDate dataAdimissao;
    private boolean ativo;
    @ManyToOne(fetch = FetchType.LAZY)
    private Colaborador gestor;



    @PrePersist
    public void onCreate() {
        this.ativo = true;
    }

}
