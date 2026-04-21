package br.com.HRFlow.notificacao;

import br.com.HRFlow.colaborador.Colaborador;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "notificacoes")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Notificacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String destinatario;
    @Column(nullable = false)
    private String assunto;
    @Enumerated(EnumType.STRING)
    private TipoNotificacao tipo;
    @Enumerated(EnumType.STRING)
    private StatusNotificacao status;
    @Column(nullable = false)
    private LocalDateTime dataEnvio;
    @ManyToOne(fetch = FetchType.LAZY)
    private Colaborador colaborador;


    @PrePersist
    public void onCreate() {
        this.dataEnvio = LocalDateTime.now();
        this.tipo = TipoNotificacao.CRIADO;
        this.status = StatusNotificacao.FALHOU;
    }
}
